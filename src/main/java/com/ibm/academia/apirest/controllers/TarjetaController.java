package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.dto.TarjetaDTO;
import com.ibm.academia.apirest.entities.Tarjeta;
import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.mapper.TarjetaMapper;
import com.ibm.academia.apirest.services.TarjetaDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {

    private final static Logger logger = LoggerFactory.getLogger(TarjetaController.class);

    @Autowired
    private TarjetaDAO tarjetaDAO;

    @Autowired
    private Environment environment;

    //@Value("${server.port}")
    //private Integer puerto;

    /**
     * Endpoint para obtener todas las tarjetas registradas en la BD
     * @return Lista de tarjetas que se encuentran en la bd
     */
    @GetMapping("/listar")
    public ResponseEntity<?> obtenerTarjetas() {
        List<Tarjeta> tarjetas = tarjetaDAO.verTodasTarjetas()
                .stream()
                .map(tarjeta -> {
                    tarjeta.setPuerto(Integer.parseInt(environment.getProperty("local.server.port")));
                    //tarjeta.setPuerto(puerto);
                    return tarjeta;
                }).collect(Collectors.toList());



        if(tarjetas.isEmpty())
            throw new BadRequestException("No existen tarjetas cargadas en la BD");

        return new ResponseEntity<>(tarjetas, HttpStatus.OK);
    }

    /**
     * Endpoint para realizar recomendacion de tarjeta
     * @param pasion
     * @param edad
     * @param sueldo
     * @return Lista de tarjetas recomendadas al usuario
     */
    @PostMapping("/obtener-recomendacion")
    public ResponseEntity<?> obtenerRecomendacionTarjetas(@RequestParam(name = "pasion") String pasion,
                                                          @RequestParam(required = false, name = "edad") Integer edad,
                                                          @RequestParam(required = false, name = "sueldo") Double sueldo) {

        List<TarjetaDTO> tarjetaDTOS = null;

        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {

            tarjetaDTOS = (List<TarjetaDTO>) tarjetaDAO.obtenerRecomendacionTarjetas(pasion, edad, sueldo);
            tarjetaDTOS.stream().map(tarjeta -> {
                tarjeta.setPuerto(Integer.parseInt(environment.getProperty("local.server.port")));
                //tarjeta.setPuerto(puerto);
                return tarjeta;
            }).collect(Collectors.toList());


            return new ResponseEntity<List<TarjetaDTO>>(tarjetaDTOS, HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.info(e.getMessage());
            respuesta.put("Error", "No se pudo realizar una recomendacion");
        }
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);

    }

}
