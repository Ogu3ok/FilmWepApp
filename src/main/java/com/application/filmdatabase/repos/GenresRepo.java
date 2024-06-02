package com.application.filmdatabase.repos;

import com.application.filmdatabase.models.Genre;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class GenresRepo {
    private GenresRepo() {
    }

    public static List<String> getGenres() {
        List<String> genres = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            genres = session.createQuery("select name from Genre", String.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genres;
    }
}
