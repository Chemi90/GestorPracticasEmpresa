package com.example.gestorpracticasempresa.domain.Profesor;

import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import com.example.gestorpracticasempresa.domain.Empresa.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private int idProfesor;

    @Column(name = "nom_prof", nullable = false)
    private String nomProf;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "email_prof", nullable = false)
    private String emailProf;

    @OneToMany(mappedBy = "tutor")
    private Set<Alumno> alumnos;

    @OneToMany(mappedBy = "profesor")
    private Set<Empresa> empresas;

}
