package com.example.gestorpracticasempresa.controllers;

import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoInfo extends Alumno {
    private String dniAlum;
    private String nomAlum;
    private String obserAlum;
    private String nomEmpresa;

    // Constructor, getters y setters aquí
}
