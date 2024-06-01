package com.applicaton.filmdatabase.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "Directors")
public class Director {
    @Id
    private int directorID;
    private String name;

    @ManyToMany(mappedBy = "actors")
    private Set<Film> films;
}
