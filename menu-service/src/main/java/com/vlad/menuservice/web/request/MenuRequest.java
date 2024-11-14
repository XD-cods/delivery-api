package com.vlad.menuservice.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequest {
  @JsonProperty("menu_name")
  @NotEmpty
  private String menuName;

  @JsonProperty("menu_items_ids")
  private List<Long> menuItemsIds;
}
