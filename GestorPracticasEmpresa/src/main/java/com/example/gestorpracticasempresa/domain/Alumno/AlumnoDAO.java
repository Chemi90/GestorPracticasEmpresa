package com.example.gestorpracticasempresa.domain.Alumno;

import com.example.gestorpracticasempresa.persistence.DAO;
import com.example.gestorpracticasempresa.utils.HibernateUtils;
import org.hibernate.Session;
import java.util.ArrayList;

public class AlumnoDAO implements DAO<AlumnoEntity> {

    public ArrayList<AlumnoEntity> getAll () {
        ArrayList<AlumnoEntity> alumnos = new ArrayList<>();
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            String sql_query = "SELECT Alumno FROM AlumnoEntity Alumno ";
            var query = session.createQuery(sql_query, AlumnoEntity.class);
            alumnos = (ArrayList<AlumnoEntity>) query.list();
        }

        return alumnos;
    }

    @Override
    public AlumnoEntity get(Long id) {
        return null;
    }

    @Override
    public AlumnoEntity save(AlumnoEntity data) {
        return null;
    }

    @Override
    public void update(AlumnoEntity data) {

    }

    @Override
    public void delete(AlumnoEntity data) {

    }

    @Override
    public boolean remove(AlumnoEntity alumno) {
        return false;
    }
}
