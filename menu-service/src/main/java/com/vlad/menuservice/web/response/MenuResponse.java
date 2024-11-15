package com.vlad.menuservice.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
  @JsonProperty("menu_id")
  private Long menuId;
  @JsonProperty("menu_name")
  private String menuName;
  @JsonProperty("menu_item_ids")
  private List<Long> menuItemsIds;
}
