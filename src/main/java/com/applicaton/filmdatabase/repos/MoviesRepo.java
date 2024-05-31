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

    public static List<Movie> getMovies()  {
        try (Connection connection = ConnectionManager.open();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(GET_MOVIES);){
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
