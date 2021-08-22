package com.guliz.bookstore.stock.controller;

import com.guliz.bookstore.stock.controller.model.BookRequest;
import com.guliz.bookstore.stock.controller.model.BookResponse;
import com.guliz.bookstore.stock.mapper.StockMapper;
import com.guliz.bookstore.stock.service.StockService;
import com.guliz.bookstore.stock.service.model.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookControllerImpl implements BookController {

    private static final StockMapper stockMapper = StockMapper.INSTANCE;

    private StockService stockService;

    @Autowired
    public BookControllerImpl(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public ResponseEntity<BookResponse> newBook(@RequestBody BookRequest bookRequest) {
        StockDto stockDto = stockService.newStock(stockMapper.toStockDto(bookRequest));
        BookResponse bookResponse = stockMapper.toBookResponse(stockDto);
        bookResponse.setMessage("new book stock has been added...");
        return ResponseEntity.ok(bookResponse);
    }

    @Override
    public ResponseEntity<BookResponse> updateStock(BookRequest bookRequest) {
        StockDto stockDto = stockService.updateStock(stockMapper.toStockDto(bookRequest));
        BookResponse bookResponse = stockMapper.toBookResponse(stockDto);
        bookResponse.setMessage("existed stock has been updated...");
        return ResponseEntity.ok(bookResponse);
    }

}
