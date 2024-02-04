package com.example.gestorpracticasempresa.domain.Alumno;


//import com.example.gestorpracticasempresa.persistence.DAO;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.example.gestorpracticasempresa.domain.DAO;


import java.util.ArrayList;
import java.util.Objects;

public class AlumnoDAO implements DAO<Alumno> {

    public ArrayList<Alumno> getAll () {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String sql_query = "SELECT Alumno FROM Alumno Alumno ";
            var query = session.createQuery(sql_query, Alumno.class);
            alumnos = (ArrayList<Alumno>) query.list();
        }

        return alumnos;
    }

    public Alumno getByDni(String dni) {
        Alumno alumno = new Alumno();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String sql_query = "SELECT Alumno FROM Alumno Alumno WHERE dniAlum = :NId ";
            var query = session.createQuery(sql_query, Alumno.class);
            query.setParameter("NId", dni);
            alumno = (Alumno) query.getSingleResult();
        }
        return alumno;
    }

    public Alumno getByEmailAndPassword(String email, String contraseña) {
        Alumno alumno = new Alumno();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String sql_query = "SELECT Alumno FROM Alumno Alumno WHERE contraAlum=:contra AND emailAlum=:email";
            var query = session.createQuery(sql_query, Alumno.class);
            query.setParameter("contra", contraseña);
            query.setParameter("email", email);
            alumno = (Alumno) query.getSingleResult();
        }
        return alumno;
    }

    @Override
    public Alumno get(Long dni) {

        return null;
    }

    @Override
    public Alumno save(Alumno data) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tst = session.beginTransaction();
            session.persist(data);
            tst.commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Alumno data) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tst = session.beginTransaction();
            // Obtiene el objeto de la base de datos
            if (Objects.isNull(session.find(Alumno.class, data.getDniAlum()))) {
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
    public void delete(Alumno data) {

    }

    @Override
    public boolean remove(Alumno alumno) throws Exception {
        boolean eliminado = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tst = session.beginTransaction();
            session.remove(alumno);
            tst.commit();
            try {
                getByDni(alumno.getDniAlum());
            }catch (Exception e) {
                eliminado = true;
            }

        } catch (HibernateException e) {
            throw new Exception(e);
        }
        return eliminado;
    }
}
