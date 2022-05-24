package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.dto.TarjetaDTO;
import com.ibm.academia.apirest.entities.Tarjeta;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.mapper.TarjetaMapper;
import com.ibm.academia.apirest.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarjetaDAOImpl extends GenericoDAOImpl<Tarjeta, TarjetaRepository> implements TarjetaDAO{

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

        List<TarjetaDTO> tarjetaDTOS = null;
        List<Tarjeta> tarjetas = (List<Tarjeta>) repository.obtenerRecomendacionTarjetas(pasion, edad, sueldo);

        if(tarjetas.isEmpty())
            throw new NotFoundException("No fue posible recomendar tarjetas");

        tarjetaDTOS = tarjetas.stream().map(TarjetaMapper::mapTarjeta).collect(Collectors.toList());

        return tarjetaDTOS;

    }
}
