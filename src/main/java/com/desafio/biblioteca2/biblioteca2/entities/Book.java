package com.desafio.biblioteca2.biblioteca2.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class Book {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    private String title;
    private String author;
    private String type;
    private String category;

    // Getters and setters
    // Constructors
    public Book() {
    }

    public Book(UUID id, String title, String author,String type, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.category = category;

    }

}