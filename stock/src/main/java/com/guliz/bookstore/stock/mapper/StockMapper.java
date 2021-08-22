package com.guliz.bookstore.stock.mapper;

import com.guliz.bookstore.stock.data.StockEntity;
import com.guliz.bookstore.stock.service.model.StockDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockEntity toStockEntity(StockDto stockDto);

    StockDto toStockDto(StockEntity stockEntity);
}
