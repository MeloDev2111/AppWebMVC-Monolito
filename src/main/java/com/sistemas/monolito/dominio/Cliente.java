package com.sistemas.monolito.dominio;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 60)
    @NotBlank(message = "El nombre no debe estar en blanco")
    private String nombre;

    @Column(length = 60)
    @NotBlank(message = "El apellido no debe estar en blanco")
    private String apellido;

    @Column(length = 8)
    @Size(min = 8,max = 8, message = "El DNI debe tener 8 digitos")
    private String dni;

    @Column(length = 9)
    @Size(min = 9,max = 9, message = "El celular debe tener 9 digitos")
    private String celular;

    @Column(length = 80)
    @Email(message = "Debe ingresar un email válido")
    private String email;

    @Temporal(TemporalType.DATE)
    @Past(message = "La fecha de nacimiento debe ser anterior a la actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Orden> ordenes;

}
