package com.desafio.biblioteca2.biblioteca2.adapters;
import com.desafio.biblioteca2.biblioteca2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    //Streamable<Book> findByTypeContaining(String type);

    @Query("SELECT p FROM Book p WHERE " +
            "p.title LIKE CONCAT(:query, '%')")
    List<Book> searchBookTitle(String query);

    @Transactional
    @Query("SELECT p FROM Book p WHERE " +
            "p.title LIKE CONCAT(:query, '%')")
    Stream<Book> searchBookTitleStream(String query);
}