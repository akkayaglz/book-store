package com.guliz.bookstore.stock.controller;

import com.guliz.bookstore.stock.controller.model.BookRequest;
import com.guliz.bookstore.stock.controller.model.BookResponse;
import com.guliz.bookstore.stock.service.model.StockDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/stock/v1")
public interface BookController {

    @PostMapping(value = "/new-book")
    ResponseEntity<BookResponse> newBook(BookRequest bookRequest);

    @PutMapping(value = "/update-book")
    ResponseEntity<BookResponse> updateStock(BookRequest bookRequest);

    @GetMapping(value = "/order-stock")
    ResponseEntity<StockDto> orderStock(String stockId, int quantity);


}
