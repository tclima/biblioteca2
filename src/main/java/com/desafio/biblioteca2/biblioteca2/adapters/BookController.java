package com.desafio.biblioteca2.biblioteca2.adapters;

import com.desafio.biblioteca2.biblioteca2.entities.Book;
import com.desafio.biblioteca2.biblioteca2.entities.dtos.BookRequestDTO;
import com.desafio.biblioteca2.biblioteca2.entities.dtos.BookResponseDTO;
import com.desafio.biblioteca2.biblioteca2.usecases.BookUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookUseCase bookUseCase;

    @GetMapping
    public List<BookResponseDTO> getAllBooks() {

        return bookUseCase.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable UUID id) {

        return bookUseCase.getBookById(id);
    }

    @PostMapping
    public BookResponseDTO createBook(@RequestBody BookRequestDTO book) {
        return bookUseCase.createBook(book);
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable UUID id, @RequestBody BookRequestDTO book) {
        return bookUseCase.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookUseCase.deleteBook(id);
    }

    //search?text=cherry&limit=5

    @GetMapping("/search")
    public List<BookResponseDTO> searchBookTitle(@RequestParam String query) {

        return bookUseCase.searchBookTitle(query);
    }

    @GetMapping("/search2")
    public List<BookResponseDTO> searchBookTitleStream(@RequestParam String query) {

        return bookUseCase.searchBookTitleStream(query);
    }
}
