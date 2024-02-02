package com.example.gestorpracticasempresa.domain.Alumno;

import com.example.gestorpracticasempresa.persistence.DAO;
import com.example.gestorpracticasempresa.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Objects;

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

    public AlumnoEntity getByDni(String dni) {
        AlumnoEntity alumno = new AlumnoEntity();
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            String sql_query = "SELECT Alumno FROM AlumnoEntity Alumno WHERE dniAlum = :NId ";
            var query = session.createQuery(sql_query, AlumnoEntity.class);
            query.setParameter("NId", dni);
            alumno = (AlumnoEntity) query.getSingleResult();
        }
        return alumno;
    }

    @Override
    public AlumnoEntity get(Long dni) {

        return null;
    }

    @Override
    public AlumnoEntity save(AlumnoEntity data) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tst = session.beginTransaction();
            session.persist(data);
            tst.commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(AlumnoEntity data) throws Exception {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tst = session.beginTransaction();
            // Obtiene el objeto de la base de datos
            if (Objects.isNull(session.find(AlumnoEntity.class, data.getDniAlum()))) {
                // Si no existe, lo sube a la base de datos
                session.persist(data);
            } else {
                //Si existia, lo actualiza con los nuevos datos
                session.merge(data);
            }
            tst.commit();

        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void delete(AlumnoEntity data) {

    }

    @Override
    public boolean remove(AlumnoEntity alumno) throws Exception {
        boolean eliminado = false;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction tst = session.beginTransaction();
            session.remove(alumno);
            tst.commit();
            
            if (Objects.isNull(getByDni(alumno.getDniAlum()))) {
                eliminado = true;
            }

        } catch (HibernateException e) {
            throw new Exception(e);
        }
        return eliminado;
    }
}
