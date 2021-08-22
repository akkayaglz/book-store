package com.guliz.bookstore.order.mapper;

import com.guliz.bookstore.order.controller.model.OrderRequest;
import com.guliz.bookstore.order.controller.model.OrderResponse;
import com.guliz.bookstore.order.data.OrderEntity;
import com.guliz.bookstore.order.service.model.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponse toOrderResponse(OrderDto orderDto);

    OrderDto toOrderDto(OrderRequest orderRequest);

    OrderEntity toOrderEntity(OrderDto orderDto);

    OrderDto toOrderDto(OrderEntity orderEntity);
}
