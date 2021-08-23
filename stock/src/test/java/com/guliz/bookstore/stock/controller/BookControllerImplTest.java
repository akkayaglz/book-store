package com.guliz.bookstore.stock.controller;

import com.guliz.bookstore.stock.controller.model.BookRequest;
import com.guliz.bookstore.stock.controller.model.BookResponse;
import com.guliz.bookstore.stock.mapper.StockMapper;
import com.guliz.bookstore.stock.service.StockService;
import com.guliz.bookstore.stock.service.model.StockDto;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerImplTest {

    @Mock
    private StockService stockService;
    @InjectMocks
    private BookControllerImpl bookControllerImpl;

    private static final StockMapper mapper = StockMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private BookRequest bookRequest;
    private StockDto stockDto;

    @Before
    public void setup() {
        bookRequest = generator.nextObject(BookRequest.class);
        stockDto = mapper.toStockDto(bookRequest);
    }

    @Test
    public void testNewBook() {
        when(stockService.newStock(any())).thenReturn(stockDto);

        BookResponse response = bookControllerImpl.newBook(bookRequest).getBody();

        assertEquals("new book stock has been added...", response.getMessage());
    }

    @Test
    public void testUpdateStock() {
        when(stockService.updateStock(any())).thenReturn(stockDto);

        BookResponse response = bookControllerImpl.updateStock(bookRequest).getBody();

        assertEquals("existed stock has been updated...", response.getMessage());
    }
}