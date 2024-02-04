package com.example.gestorpracticasempresa.domain.EntradaAlumno;

import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "EntradaPractica")
public class EntradaAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada") // Asegúrate de que el nombre de la columna aquí coincida con el de tu tabla
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "tipo_practica")
    private String tipoPractica;

    @Column(name = "total_horas")
    private int totalHoras;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_alum", referencedColumnName = "dni_alum")
    private Alumno alumno;

    // Getters y setters
}
