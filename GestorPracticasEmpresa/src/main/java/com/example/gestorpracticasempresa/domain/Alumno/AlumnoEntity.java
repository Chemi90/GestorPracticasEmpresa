package com.example.gestorpracticasempresa.domain.Alumno;

import com.example.gestorpracticasempresa.domain.Empresa.Empresa;
import com.example.gestorpracticasempresa.domain.Profesor.ProfesorEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Alumno")
public class AlumnoEntity {
    @Id
    @Column(name = "dni_alum")
    private String dniAlum;

    @Column(name = "nom_alum", nullable = false)
    private String nom_alum;

    @Column(name = "fecha_nac", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    @Column(name = "contra_alum", nullable = false)
    private String contraAlum;

    @Column(name = "email_alum", nullable = false)
    private String emailAlum;

    @Column(name = "telef_alum", nullable = false)
    private String telefAlum;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @Column(name = "horas_act")
    private Integer horasAct;

    @Column(name = "horas_tot")
    private Integer horasTot;

    @Column(name = "obser_alum")
    private String obserAlum;

    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private ProfesorEntity tutor;

    @Column(name = "tipo_alum")
    @Enumerated(EnumType.STRING)
    private TipoAlum tipoAlum;



    public AlumnoEntity (String dniAlum, String nom_alum, Date fechaNac, String contraAlum, String emailAlum, String telefAlum, Empresa empresa, Integer horasAct, Integer horasTot, String obserAlum, ProfesorEntity tutor, String tipoAlum) {
        this.dniAlum = dniAlum;
        this.nom_alum = nom_alum;
        this.fechaNac = fechaNac;
        this.contraAlum = contraAlum;
        this.emailAlum = emailAlum;
        this.telefAlum = telefAlum;
        this.empresa = empresa;
        this.horasAct = horasAct;
        this.horasTot = horasTot;
        this.obserAlum = obserAlum;
        this.tutor = tutor;
        this.tipoAlum = TipoAlum.valueOf(tipoAlum);
    }
}

enum TipoAlum {
    dual, fct, clases
}



