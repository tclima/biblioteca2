package com.desafio.biblioteca2.biblioteca2.entities.dtos;

import com.desafio.biblioteca2.biblioteca2.entities.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    Book bookDtoToModel(BookRequestDTO bookDto);

    BookResponseDTO bookModelToDto(Book book);

   List<BookResponseDTO> getBooks(List<Book> books);
}