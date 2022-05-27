package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.dto.TarjetaDTO;
import com.ibm.academia.apirest.entities.Tarjeta;
import org.springframework.stereotype.Component;

@Component
public class TarjetaMapper {
    public static TarjetaDTO mapTarjeta(Tarjeta tarjeta) {
        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        tarjetaDTO.setId(tarjeta.getId());
        tarjetaDTO.setNombre(tarjeta.getNombre());
        tarjetaDTO.setPuerto(tarjeta.getPuerto());

        return tarjetaDTO;
    }
}
