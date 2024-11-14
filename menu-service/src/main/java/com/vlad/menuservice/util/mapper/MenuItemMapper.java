package com.vlad.menuservice.util.mapper;

import com.vlad.menuservice.persistence.entity.MenuItem;
import com.vlad.menuservice.web.request.MenuItemRequest;
import com.vlad.menuservice.web.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemMapper {
  private final ModelMapper modelMapper;

  public MenuItem mapMenuItemRequstToMenuItem(MenuItemRequest menuItemRequest) {
    return modelMapper.map(menuItemRequest, MenuItem.class);
  }

  public MenuItemResponse mapMenuItemToMenuItemResponse(MenuItem menuItem) {
    return modelMapper.map(menuItem, MenuItemResponse.class);
  }

  public void updateMenuItem(MenuItem menuItem, MenuItemRequest menuItemRequest) {
    modelMapper.map(menuItem, menuItemRequest);
  }
}
