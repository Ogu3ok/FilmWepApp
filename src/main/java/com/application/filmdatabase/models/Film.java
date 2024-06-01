package com.application.filmdatabase.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"genres", "actors", "directors"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Films")
public class Film {
    @Id
    private Integer filmID;
    private String title;
    private Year year;
    @Column(name = "Runtime")
    private Integer runtime;
    private String poster;
    @Column(name = "IMDB_Rating")
    private Float imdbRating;
    @Column(name = "IMDB_ID")
    private String imdbID;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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