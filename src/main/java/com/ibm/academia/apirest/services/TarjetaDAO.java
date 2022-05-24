package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.dto.TarjetaDTO;
import com.ibm.academia.apirest.entities.Tarjeta;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TarjetaDAO {
    public List<Tarjeta> verTodasTarjetas();
    public Iterable<TarjetaDTO> obtenerRecomendacionTarjetas(String pasion, Integer edad, Double sueldo);
}
