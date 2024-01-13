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
    public BookService(BookRepository bookRepository,
                       BookMapper bookMapper) {
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

    public BookDto updateBook(Long id,
                              BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            existingBook.setTitle(bookDto.getTitle());
            existingBook.setAuthor(bookDto.getAuthor());
            existingBook.setGenre(bookDto.getGenre());
            existingBook.setPublishedYear(bookDto.getPublishedYear());
            existingBook.setQuantityAvailable(bookDto.getQuantityAvailable());

            Book updatedBook = bookRepository.save(existingBook);
            return bookMapper.mapToBookDto(updatedBook);
        } else {
            return null; // Book not found
        }
    }

    public Book findById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
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
