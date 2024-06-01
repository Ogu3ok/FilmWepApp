package com.application.filmdatabase.repos;

import com.application.filmdatabase.models.Film;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class FilmsRepo {

    private FilmsRepo() {
    }

    public static List<Film> getFilms(String title,  int minDuration, int maxDuration, int rating)  {
        List<Film> films = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                var session = sessionFactory.openSession()) {
                films = session.createQuery("FROM Film WHERE title LIKE :title AND runtime BETWEEN :minDuration AND :maxDuration AND imdbRating >= :rating", Film.class)
                        .setParameter("title", "%" + title + "%")
                        .setParameter("minDuration", minDuration)
                        .setParameter("maxDuration", maxDuration)
                        .setParameter("rating", rating)
                        .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }
}
