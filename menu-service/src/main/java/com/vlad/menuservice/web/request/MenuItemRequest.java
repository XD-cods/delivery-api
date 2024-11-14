package com.vlad.menuservice.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {
  private String name;

  private String description;

  private BigDecimal price;

  @JsonProperty("menu_id")
  private Long menuId;
}
