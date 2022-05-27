package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.dto.TarjetaDTO;
import com.ibm.academia.apirest.entities.Tarjeta;
import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.mapper.TarjetaMapper;
import com.ibm.academia.apirest.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarjetaDAOImpl extends GenericoDAOImpl<Tarjeta, TarjetaRepository> implements TarjetaDAO {

    @Autowired
    public TarjetaDAOImpl(TarjetaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarjeta> verTodasTarjetas() {
        return (List<Tarjeta>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<TarjetaDTO> obtenerRecomendacionTarjetas(String pasion, Integer edad, Double sueldo) {

        if (pasion.isBlank())
            throw new BadRequestException("Pasion no puede ser vacio");
        if (edad == null)
            throw new BadRequestException("Edad no puede ser vacio");
        if (sueldo == null)
            throw new BadRequestException("Sueldo no puede ser vacio");
        if (edad < 18 || edad > 75)
            throw new BadRequestException("El rango de Edad permitido es de 18-75 años");
        if (sueldo < 7000.0)
            throw new BadRequestException("El sueldo minimo es de 7,000.0");
        if (!(pasion.contains("shopping") || pasion.contains("mystyle") || pasion.contains("mybusiness")
                || pasion.contains("travels") || pasion.contains("sports") || pasion.contains("help")))
            throw new BadRequestException("Ingrese una pasion valida");

        List<TarjetaDTO> tarjetaDTOS = null;
        List<Tarjeta> tarjetas = (List<Tarjeta>) repository.obtenerRecomendacionTarjetas(pasion.toLowerCase(), edad,
                sueldo);

        if (tarjetas.isEmpty())
            throw new NotFoundException("No fue posible recomendar tarjetas");

        tarjetaDTOS = tarjetas.stream().map(TarjetaMapper::mapTarjeta).collect(Collectors.toList());

        return tarjetaDTOS;

    }
}
