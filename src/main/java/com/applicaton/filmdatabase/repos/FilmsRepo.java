package com.applicaton.filmdatabase.repos;

import com.applicaton.filmdatabase.models.Film;
import com.applicaton.filmdatabase.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmsRepo {

    private final static String GET_MOVIES = "SELECT * FROM Films";
    private FilmsRepo() {
    }

    public static List<Film> getFilms(String title,  int minDuration, int maxDuration, int rating)  {
        StringBuilder query = new StringBuilder(GET_MOVIES);

        query.append(" WHERE Runtime BETWEEN ").append(minDuration).append(" AND ").append(maxDuration);

        // Add conditions for title if it is not null
        if (title != null && !title.isEmpty()) {
            query.append(" AND Title LIKE '%").append(title).append("%'");
        }
        if (rating > 0) {
            query.append(" AND IMDB_Rating >= ").append(rating);
        }
        try (Connection connection = ConnectionManager.open();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query.toString())){
            List<Film> films = new ArrayList<>();
            while(resultSet.next()) {
                films.add(new Film(
                        resultSet.getInt("FilmID"),
                        resultSet.getString("Title"),
                        resultSet.getInt("Year"),
                        resultSet.getInt("Runtime"),
                        resultSet.getString("Poster"),
                        resultSet.getFloat("IMDB_Rating"),
                        resultSet.getString("IMDB_ID")
                ));
            }
            return films;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
