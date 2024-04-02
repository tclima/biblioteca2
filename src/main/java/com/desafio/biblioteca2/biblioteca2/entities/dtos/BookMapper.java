package com.desafio.biblioteca2.biblioteca2.entities.dtos;

import com.desafio.biblioteca2.biblioteca2.entities.dtos.BookRequestDTO;
import com.desafio.biblioteca2.biblioteca2.entities.dtos.BookResponseDTO;
import com.desafio.biblioteca2.biblioteca2.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    //BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookDtoToModel(BookRequestDTO bookDto);

    BookResponseDTO bookModelToDto(Book book);

   List<BookResponseDTO> getBooks(List<Book> books);
}