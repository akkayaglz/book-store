package com.guliz.bookstore.stock.service;

import com.guliz.bookstore.stock.service.model.StockDto;

public interface StockService {

    StockDto order(String stockId, int requestedQuantity);

}
