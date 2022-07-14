package com.microservices.bookservice.service;

import com.microservices.bookservice.model.Book;
import com.microservices.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final Environment environment;

    public Book getBookById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found!"));
        String port = this.environment.getProperty("local.server.port");
        book.setEnvironment(port);
        return book;
    }
}
