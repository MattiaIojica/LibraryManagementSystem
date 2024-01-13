package com.example.projectJava.repository;

import com.example.projectJava.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);
    // Add more custom queries as needed
}
