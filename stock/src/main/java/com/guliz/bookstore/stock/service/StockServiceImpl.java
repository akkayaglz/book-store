package com.guliz.bookstore.stock.service;


import com.guliz.bookstore.stock.data.StockEntity;
import com.guliz.bookstore.stock.data.StockRepository;
import com.guliz.bookstore.stock.mapper.StockMapper;
import com.guliz.bookstore.stock.service.exception.StockServiceException;
import com.guliz.bookstore.stock.service.model.StockDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Calendar.getInstance;

@Service
public class StockServiceImpl implements StockService {

    private static final StockMapper stockMapper = StockMapper.INSTANCE;
    private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);

    private StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public StockDto order(String bookId, int requestedQuantity) {
        StockEntity stockEntity = retrieveStock(bookId);

        checkStock(stockEntity, requestedQuantity);

        updateStockWithNewQuantity(stockEntity, requestedQuantity);

        return stockMapper.toStockDto(stockEntity);
    }

    @Override
    public StockDto newStock(StockDto stockDto) {
        stockRepository.findByBookName(stockDto.getBookName())
                .ifPresent(t -> {
                            throw new StockServiceException("there is already book exist");
                        }
                );
        logger.info("POST stock -" + stockDto.getBookId());
        return stockMapper.toStockDto(stockRepository.save(stockMapper.toStockEntity(stockDto)));
    }

    @Override
    public StockDto updateStock(StockDto stockDto) {
        StockEntity stockEntity = retrieveStock(stockDto.getBookId());

        logger.info("PUT stock -" + stockEntity.getBookId());

        return stockMapper.toStockDto(stockRepository.save(stockMapper.toStockEntity(stockDto)));
    }

    private void checkStock(StockEntity stock, int requestedQuantity) {
        if (stock.getStockCount() < requestedQuantity) {
            throw new StockServiceException("There is not enough stock...");
        }
    }

    private void updateStockWithNewQuantity(StockEntity stockEntity, int requestedQuantity) {
        stockEntity.setStockCount(stockEntity.getStockCount() - requestedQuantity);
        stockEntity.setUpdatedAt(getInstance().getTime());
        updateStock(stockMapper.toStockDto(stockEntity));
    }

    private StockEntity retrieveStock(String bookId) {
        return stockRepository.findById(bookId)
                .orElseThrow(() -> new StockServiceException("There is no book with this book id"));
    }
}
