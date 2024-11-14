package com.vlad.menuservice.util.mapper;

import com.vlad.menuservice.persistence.entity.Menu;
import com.vlad.menuservice.web.request.MenuRequest;
import com.vlad.menuservice.web.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuMapper {
  private final ModelMapper modelMapper;

  public MenuResponse mapMenuToMenuResponse(Menu menu) {
    return modelMapper.map(menu, MenuResponse.class);
  }

  public Menu mapMenuRequestToMenu(MenuRequest menuRequest) {
    return modelMapper.map(menuRequest, Menu.class);
  }

  public void updateExistMenu(Menu existMenu, MenuRequest menuRequest) {
    modelMapper.map(existMenu, menuRequest);
  }
}
