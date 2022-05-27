package com.ibm.academia.apirest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recomendacion {
    private String pasion;
    private Integer edad;
    private Double sueldo;
}
