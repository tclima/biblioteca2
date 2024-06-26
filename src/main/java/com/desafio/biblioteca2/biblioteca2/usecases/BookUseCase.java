package com.desafio.biblioteca2.biblioteca2.usecases;

import com.desafio.biblioteca2.biblioteca2.adapters.BookRepository;
import com.desafio.biblioteca2.biblioteca2.entities.Book;
import com.desafio.biblioteca2.biblioteca2.entities.dtos.BookRequestDTO;
import com.desafio.biblioteca2.biblioteca2.entities.dtos.BookResponseDTO;
import com.desafio.biblioteca2.biblioteca2.entities.dtos.BookMapper;
import org.mapstruct.factory.Mappers;
import com.desafio.biblioteca2.biblioteca2.frameworks.exceptions.BadBookException;
import com.desafio.biblioteca2.biblioteca2.frameworks.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BookUseCase {
    @Autowired
    BookRepository repository;
    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    public BookResponseDTO getBookById(UUID id) {

        return bookMapper.bookModelToDto(repository.findById(id).orElseThrow(() -> new NotFoundException("Livro não encontrado")));
    }


    public BookResponseDTO createBook(BookRequestDTO bookRequest) {

           if (!bookIsValid(bookRequest)) {
                throw new BadBookException("Informações incompletas");
            }
           //var bookModel = mapperDTOtoModel(bookRequest);
           //return mapperModelToDto(repository.save(bookModel));

        var bookModel = bookMapper.bookDtoToModel(bookRequest);
        return  bookMapper.bookModelToDto(repository.save(bookModel));

    }



    private boolean bookIsValid(BookRequestDTO book) {
        if(book.getTitle() == null || book.getTitle().isEmpty() || book.getTitle().isBlank() || book.getTitle().length() > 100){
            return false;
        }else if(book.getAuthor() == null || book.getAuthor().isEmpty() || book.getAuthor().isBlank() || book.getAuthor().length() > 100){
            return false;
        }else if( book.getType() == null || book.getType().isEmpty() || book.getType().isBlank() || book.getType().length() > 100){
            return false;
        }else if(book.getCategory() == null || book.getCategory().isEmpty() || book.getCategory().isBlank() || book.getCategory().length() > 100){
            return false;
        }else {
            return true;
        }
    }

    public BookResponseDTO updateBook(UUID id, BookRequestDTO updatedBook) {
        if (repository.existsById(id)) {
            var bookModel = bookMapper.bookDtoToModel(updatedBook);
            bookModel.setId(id);
            if (!bookIsValid(updatedBook)) {
                throw new BadBookException("Informações incompletas");
            }
            return bookMapper.bookModelToDto(repository.save(bookModel));
        } else {
            throw new NotFoundException("Livro não encontrado");
            //return null;
        }
    }

    public void deleteBook(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }else {
            throw new NotFoundException("Livro não encontrado");
        }
    }

    public List<BookResponseDTO> getAllBooks() {
        //return (List<BookResponseDTO>) mapperModelToDto(repository.findAll()); //converter a lista
        return bookMapper.getBooks(repository.findAll());

    }

    public List<BookResponseDTO> searchBookTitle(String query) {
        //return repository.searchBookTitle(query);

        return bookMapper.getBooks(repository.searchBookTitle(query));
    }

    public List<BookResponseDTO> searchBookTitleStream(String query) {

        //return repository.findAll().stream().filter(element -> element.getTitle().startsWith(query)).collect(Collectors.toList());

        return bookMapper.getBooks(repository.findAll().stream().filter(element -> element.getTitle().startsWith(query)).collect(Collectors.toList()));
    }

    private BookResponseDTO mapperModelToDto(Book book) {

        return BookResponseDTO.builder().
                title(book.getTitle()).
                author(book.getAuthor()).
                type(book.getType()).
                category(book.getCategory()).
                build();
    }
    private Book mapperDTOtoModel(BookRequestDTO book) {

        return Book.builder().
                title(book.getTitle()).
                author(book.getAuthor()).
                type(book.getType()).
                category(book.getCategory()).
                build();
    }
}
