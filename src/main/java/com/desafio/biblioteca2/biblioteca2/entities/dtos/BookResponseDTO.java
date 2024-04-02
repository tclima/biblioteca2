package com.desafio.biblioteca2.biblioteca2.entities.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDTO {
    private UUID id;
    private String title;
    private String author;
    private String type;
    private String category;


}