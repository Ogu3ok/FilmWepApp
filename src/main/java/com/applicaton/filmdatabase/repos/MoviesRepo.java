package com.applicaton.filmdatabase.repos;

import com.applicaton.filmdatabase.models.Movie;
import com.applicaton.filmdatabase.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviesRepo {

    private final static String GET_MOVIES = "SELECT * FROM movies";
    private MoviesRepo() {
    }

    public static List<Movie> getMovies(String title, String scenarist, int minDuration, int maxDuration, int rating)  {
            StringBuilder query = new StringBuilder(GET_MOVIES);

            query.append(" WHERE watch_time BETWEEN ").append(minDuration).append(" AND ").append(maxDuration);

            // Add conditions for title and scenarist if they are not null
            if (title != null && !title.isEmpty()) {
                query.append(" AND name LIKE '%").append(title).append("%'");
            }
//            if (scenarist != null && !scenarist.isEmpty()) {
//                query.append(" AND scenarist LIKE ?");
//            }
            if (rating > 0) {
                query.append(" AND rating >= ").append(rating);
            }
        try (Connection connection = ConnectionManager.open();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query.toString())){
            List<Movie> movies = new ArrayList<>();
            while(resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getString("name"),
                        resultSet.getString("year_of_release"),
                        resultSet.getInt("watch_time"),
                        resultSet.getFloat("rating"),
                        resultSet.getInt("metascore"),
                        resultSet.getInt("votes"),
                        resultSet.getString("gross"),
                        resultSet.getString("description")
                ));
            }
            return movies;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
