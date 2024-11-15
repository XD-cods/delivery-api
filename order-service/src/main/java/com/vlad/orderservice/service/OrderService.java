package com.vlad.orderservice.service;

import com.vlad.orderservice.exception.NotFoundException;
import com.vlad.orderservice.persistence.entity.Order;
import com.vlad.orderservice.persistence.repository.OrderRepository;
import com.vlad.orderservice.utility.kafka.KafkaSender;
import com.vlad.orderservice.utility.mapper.OrderMapper;
import com.vlad.orderservice.web.request.OrderRequest;
import com.vlad.orderservice.web.response.OrderResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final KafkaSender kafkaSender;
  public static final String ORDER_NOT_FOUND = "Order not found by id: %d";

  @Transactional
  public List<OrderResponse> getAllOrders() {
    return orderRepository.findAll().stream().map(orderMapper::mapOrderToOrderResponse).toList();
  }

  @Transactional
  public OrderResponse getOrderById(Long id) {
    Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(ORDER_NOT_FOUND, id)));

    return orderMapper.mapOrderToOrderResponse(order);
  }

  @Transactional
  public OrderResponse createOrder(OrderRequest orderRequest) {
    Order order = orderMapper.mapOrderRequestToOrder(orderRequest);
    order = orderRepository.save(order);
    kafkaSender.send("order-created", order);
    return orderMapper.mapOrderToOrderResponse(order);
  }

  @Transactional
  public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
    Order existOrder = orderRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(ORDER_NOT_FOUND, id)));

    orderMapper.updateExistOrder(orderRequest, existOrder);
    orderRepository.save(existOrder);
    kafkaSender.send("order-updated", existOrder);
    return orderMapper.mapOrderToOrderResponse(existOrder);
  }

  @Transactional
  public Boolean deleteOrder(Long id) {
    orderRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(ORDER_NOT_FOUND, id)));
    orderRepository.deleteById(id);
    return true;
  }

}
