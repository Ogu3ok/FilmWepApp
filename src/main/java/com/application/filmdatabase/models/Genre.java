package com.application.filmdatabase.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "films")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    private int genreID;
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "genres")
    private List<Film> films = new ArrayList<>();
}
