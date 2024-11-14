package com.vlad.menuservice.service;

import com.vlad.menuservice.exception.ConflictException;
import com.vlad.menuservice.exception.NotFoundException;
import com.vlad.menuservice.persistence.entity.Menu;
import com.vlad.menuservice.persistence.repository.MenuItemRepository;
import com.vlad.menuservice.persistence.repository.MenuRepository;
import com.vlad.menuservice.util.mapper.MenuMapper;
import com.vlad.menuservice.web.request.MenuRequest;
import com.vlad.menuservice.web.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
  private final MenuItemRepository menuItemRepository;
  private final MenuRepository menuRepository;
  private final MenuMapper menuMapper;


  public static final String NOT_FOUND_BY_ID_MESSAGE = "Menu not found by id: %d";
  public static final String EXISTENCE_BY_NAME_MESSAGE = "Menu already exists with name: %s";

  @Transactional
  public MenuResponse getMenuById(Long id) {
    return menuMapper.mapMenuToMenuResponse(menuRepository.findById(id)
            .orElseThrow(()->new NotFoundException(String.format(NOT_FOUND_BY_ID_MESSAGE, id))));
  }

  @Transactional
  public List<MenuResponse> getAllMenu() {
    return menuRepository.findAll()
            .stream()
            .map(menuMapper::mapMenuToMenuResponse)
            .toList();
  }

  @Transactional
  public MenuResponse createMenu(MenuRequest menuRequest) {
    Optional<Menu> existMenu = menuRepository.findByName(menuRequest.getMenuName());
    if (existMenu.isPresent()) {
      throw new ConflictException(String.format(EXISTENCE_BY_NAME_MESSAGE, menuRequest.getMenuName()));
    }

    Menu newMenu = menuMapper.mapMenuRequestToMenu(menuRequest);
    menuRepository.save(newMenu);
    return menuMapper.mapMenuToMenuResponse(newMenu);
  }

  @Transactional
  public MenuResponse updateMenu(Long id, MenuRequest menuRequest) {
    Menu existMenu = menuRepository.findById(id)
            .orElseThrow(()->new NotFoundException(String.format(NOT_FOUND_BY_ID_MESSAGE, id)));

    menuMapper.updateExistMenu(existMenu, menuRequest);
    menuRepository.save(existMenu);
    return menuMapper.mapMenuToMenuResponse(existMenu);
  }

  @Transactional
  public Boolean deleteMenu(Long id) {
    menuRepository.findById(id)
            .orElseThrow(()->new NotFoundException(String.format(NOT_FOUND_BY_ID_MESSAGE, id)));

    menuRepository.deleteById(id);
    return true;
  }
}
