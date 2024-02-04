package com.example.gestorpracticasempresa.domain.Empresa;

import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import com.example.gestorpracticasempresa.domain.Profesor.Profesor;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Empresa")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private int idEmpresa;

    @Column(name = "nom_empresa", nullable = false, unique = true)
    private String nomEmpresa;

    @Column(name = "telef_empresa", nullable = false)
    private String telefEmpresa;

    @Column(name = "responsable", nullable = false)
    private String responsable;

    @Column(name = "obser_empresa")
    private String obserEmpresa;

    @OneToOne(mappedBy = "empresa")
    @ToString.Exclude
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_prof")
    @ToString.Exclude
    private Profesor profesor;

}
