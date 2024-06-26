package com.application.filmdatabase.repos;

import com.application.filmdatabase.models.Film;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilmsRepo {

    private FilmsRepo() {
    }

    public static List<Film> getFilms(Map<String, String[]> params, int page) {
        List<Film> films = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure();
        StringBuilder buildQuery = new StringBuilder("Select f from Film f join f.directors d");
        if (!params.isEmpty()) {
            buildQuery.append(" where ");
        }
        var iterator = params.entrySet().iterator();
        while(iterator.hasNext()){
            var entry = iterator.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            switch (key) {
                case "title" -> buildQuery.append("f.title like '%").append(value[0]).append("%'");
                case "director" -> buildQuery.append("d.name like '%").append(value[0]).append("%'");
                case "minDuration" -> buildQuery.append("f.runtime >= ").append(value[0]);
                case "maxDuration" -> buildQuery.append("f.runtime <= ").append(value[0]);
                case "rating" -> buildQuery.append("f.imdbRating >= ").append(value[0]);
                case "genre" ->{
                    for (int i = 0; i < value.length; i++) {
                        buildQuery.append("exists (select g from f.genres g where g.name = '").append(value[i]).append("')");
                        if (i != value.length - 1) {
                            buildQuery.append(" and ");
                        }
                    }
                }
            }
            if (iterator.hasNext()) {
                buildQuery.append(" and ");
            }
        }
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {

            films = session.createQuery(buildQuery.toString(), Film.class)
                    .setFirstResult(page * 15)
                    .setMaxResults(15)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }
}
