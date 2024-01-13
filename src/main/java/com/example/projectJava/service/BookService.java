package com.example.projectJava.service;

import com.example.projectJava.dto.BookDto;
import com.example.projectJava.mapper.BookMapper;
import com.example.projectJava.model.Book;
import com.example.projectJava.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.mapListToBookDto(books);
    }

    public BookDto getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(bookMapper::mapToBookDto).orElse(null);
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            // Update only the fields that are present in the bookDto
            if (bookDto.getTitle() != null) {
                existingBook.setTitle(bookDto.getTitle());
            }
            if (bookDto.getAuthor() != null) {
                existingBook.setAuthor(bookDto.getAuthor());
            }
            if (bookDto.getGenre() != null) {
                existingBook.setGenre(bookDto.getGenre());
            }
            if (bookDto.getPublishedYear() != 0) {
                existingBook.setPublishedYear(bookDto.getPublishedYear());
            }
            if (bookDto.getQuantityAvailable() != 0) {
                existingBook.setQuantityAvailable(bookDto.getQuantityAvailable());
            }

            // Update other fields as needed

            Book updatedBook = bookRepository.save(existingBook);
            return bookMapper.mapToBookDto(updatedBook);
        } else {
            return null; // Book not found
        }
    }



    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            return false; // Book not found
        }
    }
}
