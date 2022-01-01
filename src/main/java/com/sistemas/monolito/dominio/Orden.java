package com.sistemas.monolito.dominio;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cliente_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_cliente_orden")
            )
    private Cliente cliente;

    @Column(length = 60)
    @NotBlank(message = "La descripcion no debe estar en blanco")
    private String descripcion;

    @Positive
    @Max(value = 50)
    private Double largo;

    @Positive
    @Max(value = 20)
    private Double ancho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name="tarifa_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tarifa_orden")
              )
    private Tarifa tarifa;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaOrden;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaEntrega;

    @Positive
    private Double costo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    public List<Asignacion> asignaciones;

}
