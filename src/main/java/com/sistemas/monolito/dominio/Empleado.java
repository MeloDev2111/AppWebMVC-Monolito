package com.sistemas.monolito.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 60)
    @NotBlank(message = "El apellido no debe estar en blanco")
    String apellido;

    @Column(length = 60)
    @NotBlank(message = "El nombre no debe estar en blanco")
    String nombre;

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

    @Temporal(TemporalType.DATE)
    @Past(message = "La fecha de ingreso debe ser anterior a la actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<Asignacion> asignaciones;

}
