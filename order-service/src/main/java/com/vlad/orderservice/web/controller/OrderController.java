package com.vlad.orderservice.web.controller;

import com.vlad.orderservice.service.OrderService;
import com.vlad.orderservice.web.request.OrderRequest;
import com.vlad.orderservice.web.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<List<OrderResponse>> getAllOrders() {
    return ResponseEntity.ok(orderService.getAllOrders());
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
    return ResponseEntity.ok(orderService.createOrder(orderRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id
          , @RequestBody OrderRequest orderRequest) {
    return ResponseEntity.ok(orderService.updateOrder(id, orderRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteOrder(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.deleteOrder(id));
  }
}
