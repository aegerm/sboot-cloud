package com.microservices.bookservice.controller;

import com.microservices.bookservice.integration.Cambio;
import com.microservices.bookservice.model.Book;
import com.microservices.bookservice.proxy.ICambioProxy;
import com.microservices.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/book-service")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final ICambioProxy cambioProxy;

    @GetMapping("/{id}/{currency}")
    public ResponseEntity<Book> findBook(@PathVariable Long id, @PathVariable String currency) {
        Book book = this.bookService.getBookById(id);

        Cambio cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        book.setPrice(cambio.getConvertedValue());

        return ResponseEntity.ok(book);
    }
}
