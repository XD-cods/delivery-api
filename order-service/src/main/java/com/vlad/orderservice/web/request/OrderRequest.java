package com.vlad.orderservice.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vlad.orderservice.persistence.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
  private Long customer;

  @JsonProperty("order_items_ids")
  private List<Long> orderItemsIds;

  @JsonProperty("total_amount")
  private BigDecimal totalAmount;

  private Status status;

}
