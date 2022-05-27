package com.ibm.academia.apirest.entities;

import com.ibm.academia.apirest.enums.TipoTarjeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjetas", schema = "datos")
public class Tarjeta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Column(name = "pasion", nullable = false)
    private String pasion;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Column(name = "edad_minima", nullable = false)
    private Integer edadMinima;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Column(name = "edad_maxima", nullable = false)
    private Integer edadMaxima;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Column(name = "sueldo_minimo", nullable = false)
    private Double sueldoMinimo;

    @Column(name = "sueldo_maximo")
    private Double sueldoMaximo;

    @Transient
    private Integer puerto;

}
