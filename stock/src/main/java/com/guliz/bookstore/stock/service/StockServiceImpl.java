package com.guliz.bookstore.stock.service;


import com.guliz.bookstore.stock.data.StockEntity;
import com.guliz.bookstore.stock.data.StockRepository;
import com.guliz.bookstore.stock.mapper.StockMapper;
import com.guliz.bookstore.stock.service.exception.StockServiceException;
import com.guliz.bookstore.stock.service.model.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static java.util.Calendar.*;

@Service
public class StockServiceImpl implements StockService {

    private static final StockMapper stockMapper = StockMapper.INSTANCE;

    private StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public StockDto order(String bookId, int requestedQuantity) {
        StockEntity stock = retrieveStock(bookId);

        checkStock(stock, requestedQuantity);

        updateStock(stock, requestedQuantity);

        return stockMapper.toStockDto(stock);
    }

    private void checkStock(StockEntity stock, int requestedQuantity) {
        if (stock.getStockCount() < requestedQuantity) {
            throw new StockServiceException("There is not enough stock...");
        }
    }

    private void updateStock(StockEntity stockEntity, int requestedQuantity) {
        stockEntity.setStockCount(stockEntity.getStockCount() - requestedQuantity);
        stockEntity.setUpdatedAt(getInstance().getTime());
        stockRepository.save(stockEntity);
    }

    private StockEntity retrieveStock(String bookId) {
        return stockRepository.findById(bookId)
                .orElseThrow(() -> new StockServiceException("There is no book with this book id"));
    }
}
