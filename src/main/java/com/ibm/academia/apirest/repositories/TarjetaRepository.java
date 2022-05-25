package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.entities.Tarjeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TarjetaRepository extends PagingAndSortingRepository<Tarjeta, Integer> {

    @Query("select t from Tarjeta t where t.pasion = ?1 and (?2 between t.edadMinima and t.edadMaxima) " +
            "and t.sueldoMinimo <= ?3 and t.sueldoMaximo >= ?3")
    public Iterable<Tarjeta> obtenerRecomendacionTarjetas(String pasion, Integer edad, Double sueldo);
}
