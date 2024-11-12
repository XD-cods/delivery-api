package com.vlad.orderservice.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vlad.orderservice.persistence.entity.Customer;
import com.vlad.orderservice.persistence.entity.OrderItem;
import com.vlad.orderservice.persistence.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class OrderResponse {

  public OrderResponse(List<OrderItem> orderItems, LocalDateTime createdAt, Customer customer) {
    this.createdAt = createdAt;
    orderItemIds.addAll(orderItems.stream().map(OrderItem::getOrderItemId).toList());
    this.customerId = customer.getCustomerId();
  }

  @JsonProperty("order_id")
  private Long orderId;

  @JsonProperty("customer_id")
  private Long customerId;

  @JsonProperty("order_items")
  private List<Long> orderItemIds;

  @JsonProperty("total_amount")
  private BigDecimal totalAmount;

  private Status status;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;
}
