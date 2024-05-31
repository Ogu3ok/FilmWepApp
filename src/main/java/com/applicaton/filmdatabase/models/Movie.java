package com.applicaton.filmdatabase.models;

import java.sql.Date;
import java.util.Calendar;

public class Movie {
    private String name;
    private String year_of_release;
    private int watch_time;
    private float rating;
    private int metascore;
    private int votes;
    private String gross;
    private String description;

    public Movie(String name, String year_of_release, int watch_time, float rating, int metascore, int votes, String gross, String description) {
        this.name = name;
        this.year_of_release = year_of_release;
        this.watch_time = watch_time;
        this.rating = rating;
        this.metascore = metascore;
        this.votes = votes;
        this.gross = gross;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear_of_release() {
        return year_of_release;
    }

    public void setYear_of_release(String year_of_release) {
        this.year_of_release = year_of_release;
    }

    public int getWatch_time() {
        return watch_time;
    }

    public void setWatch_time(int watch_time) {
        this.watch_time = watch_time;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
