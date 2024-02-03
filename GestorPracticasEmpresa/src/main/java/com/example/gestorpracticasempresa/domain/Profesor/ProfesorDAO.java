package com.example.gestorpracticasempresa.domain.Profesor;

import com.example.gestorpracticasempresa.domain.DAO;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO implements DAO<Profesor> {

    @Override
    public ArrayList<Profesor> getAll() {
        List<Profesor> profesores = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            profesores = session.createQuery("FROM Profesor", Profesor.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Profesor>) profesores;
    }

    @Override
    public Profesor get(Long id) {
        Profesor profesor = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            profesor = session.get(Profesor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profesor;
    }

    @Override
    public Profesor save(Profesor data) {
        Profesor salida = null;
        Transaction t = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            t = s.beginTransaction();
            s.saveOrUpdate(data);
            t.commit();
            salida = data;
        } catch (Exception e){
            if (t != null) t.rollback();
            e.printStackTrace();
        }
        return salida;
    }

    @Override
    public void update(Profesor data) {
        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            s.update(data);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Profesor data) {
        Transaction t = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            s.delete(data);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        }
    }

    public Profesor getByEmailAndPassword(String email, String contraseña) {
        Profesor profesor = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Utiliza SQL nativo
            Query<Profesor> query = session.createNativeQuery("SELECT * FROM Profesor WHERE email_prof = :email AND contraseña = :contraseña", Profesor.class);
            query.setParameter("email", email);
            query.setParameter("contraseña", contraseña);
            profesor = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profesor;
    }

    @Override
    public boolean remove(Profesor profesor) {
        try {
            delete(profesor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
