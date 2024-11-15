package com.vlad.menuservice.service;

import com.vlad.menuservice.exception.ConflictException;
import com.vlad.menuservice.exception.NotFoundException;
import com.vlad.menuservice.persistence.entity.MenuItem;
import com.vlad.menuservice.persistence.repository.MenuItemRepository;
import com.vlad.menuservice.util.mapper.MenuItemMapper;
import com.vlad.menuservice.web.request.MenuItemRequest;
import com.vlad.menuservice.web.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemService {
  private final MenuItemRepository menuItemRepository;
  private final MenuItemMapper menuItemMapper;

  public static final String NOT_FOUND_BY_ID_MESSAGE = "Menu not found by id: %d";
  public static final String EXISTENCE_BY_NAME_MESSAGE = "Menu already exists with name: %s";

  @Transactional
  public MenuItemResponse getMenuItemById(Long id) {
    return menuItemMapper.mapMenuItemToMenuItemResponse(menuItemRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_BY_ID_MESSAGE, id))));
  }

  @Transactional
  public List<MenuItemResponse> getAllMenuItems() {
    return menuItemRepository.findAll()
            .stream()
            .map(menuItemMapper::mapMenuItemToMenuItemResponse)
            .toList();
  }

  @Transactional
  public MenuItemResponse createMenuItem(MenuItemRequest MenuItemRequest) {
    Optional<MenuItem> existMenuItem = menuItemRepository.findByName(MenuItemRequest.getName());
    if (existMenuItem.isPresent()) {
      throw new ConflictException(String.format(EXISTENCE_BY_NAME_MESSAGE, MenuItemRequest.getName()));
    }

    MenuItem newMenuItem = menuItemMapper.mapMenuItemRequstToMenuItem(MenuItemRequest);
    menuItemRepository.save(newMenuItem);
    return menuItemMapper.mapMenuItemToMenuItemResponse(newMenuItem);
  }

  @Transactional
  public MenuItemResponse updateMenuItem(Long id, MenuItemRequest MenuItemRequest) {
    MenuItem existMenuItem = menuItemRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_BY_ID_MESSAGE, id)));

    menuItemMapper.updateMenuItem(existMenuItem, MenuItemRequest);
    menuItemRepository.save(existMenuItem);
    return menuItemMapper.mapMenuItemToMenuItemResponse(existMenuItem);
  }

  @Transactional
  public Boolean deleteMenuItem(Long id) {
    menuItemRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_BY_ID_MESSAGE, id)));

    menuItemRepository.deleteById(id);
    return true;
  }
}
