package com.example.gestorpracticasempresa.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration();
            config.configure();

            sessionFactory = config.buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}