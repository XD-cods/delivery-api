package com.vlad.orderservice.utility.mapper;

import com.vlad.orderservice.persistence.entity.Order;
import com.vlad.orderservice.web.request.OrderRequest;
import com.vlad.orderservice.web.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

  private final ModelMapper modelMapper;

  public OrderResponse mapOrderToOrderResponse(Order order) {
    return modelMapper.map(order, OrderResponse.class);
  }

  public Order mapOrderRequestToOrder(OrderRequest orderRequest) {
    return modelMapper.map(orderRequest, Order.class);
  }
}
