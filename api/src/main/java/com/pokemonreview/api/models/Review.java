package com.pokemonreview.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // unique id creation
    private int id;
    private String title;
    private String content;
    private int start;
}
