package com.application.filmdatabase.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Films")
public class Film {
    @Id
    private int filmID;
    private String title;
    private int year;
    private int runtime;
    private String poster;
    @Column(name = "IMDB_Rating")
    private float imdbRating;
    @Column(name = "IMDB_ID")
    private String imdbID;

    @ManyToMany
    @JoinTable(
            name = "Film_Genres",
            joinColumns = @JoinColumn(name = "FilmID"),
            inverseJoinColumns = @JoinColumn(name = "GenreID")
    )
    @Builder.Default
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Film_Actors",
            joinColumns = @JoinColumn(name = "FilmID"),
            inverseJoinColumns = @JoinColumn(name = "ActorID")
    )
    @Builder.Default
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Film_Directors",
            joinColumns = @JoinColumn(name = "FilmID"),
            inverseJoinColumns = @JoinColumn(name = "DirectorID")
    )
    @Builder.Default
    private List<Director> directors = new ArrayList<>();
}