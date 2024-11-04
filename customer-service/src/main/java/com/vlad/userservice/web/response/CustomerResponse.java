package com.vlad.userservice.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
public class CustomerResponse {
  @JsonProperty("customer_id")
  private Long customerId;

  private String name;

  private String email;

  private String phone;

  @JsonProperty("created_at")
  private LocalDate createdAt;

  public CustomerResponse(Long customerId, String name, String email, String phone, LocalDate createdAt) {
    this.customerId = customerId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.createdAt = createdAt;
  }
}
