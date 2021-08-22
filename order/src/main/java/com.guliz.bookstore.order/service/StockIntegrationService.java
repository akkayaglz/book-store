package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.service.model.OrderStockDto;

public interface StockIntegrationService {

    OrderStockDto orderStock(String stockId, int quantity);
}
