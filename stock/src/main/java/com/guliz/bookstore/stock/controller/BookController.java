package com.guliz.bookstore.stock.controller;

import com.guliz.bookstore.stock.controller.model.BookRequest;
import com.guliz.bookstore.stock.controller.model.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/customer/v1")
public interface BookController {

    @PostMapping(value = "/new-book")
    ResponseEntity<BookResponse> newBook(BookRequest bookRequest);

    @PutMapping(value = "/update-book")
    ResponseEntity<BookResponse> updateStock(BookRequest bookRequest);


}
