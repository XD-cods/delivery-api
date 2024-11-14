package com.vlad.menuservice.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {
  @JsonProperty("menu_item_id")
  private Long menuItemId;

  private String name;

  private String description;

  private BigDecimal price;

  @JsonProperty("menu_id")
  private Long menuId;
}
