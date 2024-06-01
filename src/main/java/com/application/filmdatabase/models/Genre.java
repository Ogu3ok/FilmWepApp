package com.application.filmdatabase.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "Genres")
public class Genre {
    @Id
    private int genreID;
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "genres")
    private List<Film> films = new ArrayList<>();
}
