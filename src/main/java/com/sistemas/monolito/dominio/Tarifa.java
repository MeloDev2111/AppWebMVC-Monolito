package com.sistemas.monolito.dominio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    @NotBlank(message = "Debe ingresar una descripcion")
    private String descripcion;

    @Min(value = 0)
    private Double areaMinima;

    @NotNull
    @Min(value=0)
    private Double precio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarifa")
    private List<Orden> ordenes;
}
