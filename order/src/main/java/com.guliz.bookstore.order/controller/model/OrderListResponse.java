package com.guliz.bookstore.order.controller.model;

import com.guliz.bookstore.order.service.model.OrderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderListResponse extends BaseResponse {

    private static final long serialVersionUID = -5987563803519434756L;

    private List<OrderDto> orderList;


}
