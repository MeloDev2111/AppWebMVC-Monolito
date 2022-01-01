package com.sistemas.monolito.dominio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fase")
    private List<Asignacion> asignaciones;
}
