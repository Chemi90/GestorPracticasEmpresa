package com.example.gestorpracticasempresa.domain.EntradaAlumno;

import com.example.gestorpracticasempresa.domain.DAO;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EntradaAlumnoDAO implements DAO<EntradaAlumno> {

    private SessionFactory sessionFactory;

    public EntradaAlumnoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<EntradaAlumno> obtenerEntradasPorAlumno(String dniAlum) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Nota: Usamos el nombre de la entidad y atributos, no nombres de tabla y columnas
            String hql = "SELECT ea FROM EntradaAlumno ea WHERE ea.alumno.dniAlum = :dniAlum";
            Query<EntradaAlumno> query = session.createQuery(hql, EntradaAlumno.class);
            query.setParameter("dniAlum", dniAlum);
            return query.getResultList();
        }
    }

    @Override
    public ArrayList<EntradaAlumno> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return (ArrayList<EntradaAlumno>) session.createNativeQuery("SELECT * FROM EntradaAlumno", EntradaAlumno.class).list();
        }
    }

    @Override
    public EntradaAlumno get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EntradaAlumno.class, id);
        }
    }

    @Override
    public EntradaAlumno save(EntradaAlumno data) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(data);
        session.getTransaction().commit();
        session.close();
        return data;
    }

    @Override
    public void update(EntradaAlumno data) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(data);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(EntradaAlumno data) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(data);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean remove(EntradaAlumno entradaAlumno) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entradaAlumno);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
