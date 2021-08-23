package com.guliz.bookstore.stock.service;

import com.guliz.bookstore.stock.data.StockEntity;
import com.guliz.bookstore.stock.data.StockRepository;
import com.guliz.bookstore.stock.mapper.StockMapper;
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
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private StockServiceImpl stockServiceImpl;

    private EasyRandom generator = new EasyRandom();
    private StockDto stockDto;
    private StockEntity expectedStockEntity;
    private static final StockMapper mapper = StockMapper.INSTANCE;

    @Before
    public void setup() {
        stockDto = generator.nextObject(StockDto.class);
        expectedStockEntity = mapper.toStockEntity(stockDto);
    }

    @Test
    public void testNewStock() {
        when(stockRepository.save(any())).thenReturn(expectedStockEntity);

        StockEntity result = stockRepository.save(mapper.toStockEntity(stockDto));

        assertEquals(mapper.toStockDto(result), stockDto);
    }

}