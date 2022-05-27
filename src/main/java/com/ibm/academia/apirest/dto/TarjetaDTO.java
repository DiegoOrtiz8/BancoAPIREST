package com.ibm.academia.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaDTO {
    private Integer id;
    private String nombre;
    private Integer puerto;
}
