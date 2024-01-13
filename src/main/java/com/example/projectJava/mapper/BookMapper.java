package com.example.projectJava.mapper;

import com.example.projectJava.dto.BookDto;
import com.example.projectJava.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDto mapToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setGenre(book.getGenre());
        bookDto.setPublishedYear(book.getPublishedYear());
        bookDto.setQuantityAvailable(book.getQuantityAvailable());
        return bookDto;
    }

    public Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setPublishedYear(bookDto.getPublishedYear());
        book.setQuantityAvailable(bookDto.getQuantityAvailable());
        return book;
    }

    public List<BookDto> mapListToBookDto(List<Book> books) {
        return books.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
