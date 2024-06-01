package com.applicaton.filmdatabase.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    private float imdbRating;
    private String imdbID;

    @ManyToMany
    @JoinTable(
            name = "Film_Genres",
            joinColumns = @JoinColumn(name = "FilmID"),
            inverseJoinColumns = @JoinColumn(name = "GenreID")
    )
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "Film_Actors",
            joinColumns = @JoinColumn(name = "FilmID"),
            inverseJoinColumns = @JoinColumn(name = "ActorID")
    )
    private Set<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "Film_Directors",
            joinColumns = @JoinColumn(name = "FilmID"),
            inverseJoinColumns = @JoinColumn(name = "DirectorID")
    )
    private Set<Director> directors;
}