package com.vlad.menuservice.web.controller;

import com.vlad.menuservice.service.MenuItemService;
import com.vlad.menuservice.web.request.MenuItemRequest;
import com.vlad.menuservice.web.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu/item/")
@RequiredArgsConstructor
public class MenuItemController {
  private final MenuItemService menuItemService;

  @GetMapping("{id}")
  public ResponseEntity<MenuItemResponse> getMenuById(@PathVariable Long id) {
    return ResponseEntity.ok(menuItemService.getMenuItemById(id));
  }

  @GetMapping
  public ResponseEntity<List<MenuItemResponse>> getAllMenus() {
    return ResponseEntity.ok(menuItemService.getAllMenuItems());
  }

  @PostMapping
  public ResponseEntity<MenuItemResponse> addMenu(@RequestBody MenuItemRequest menu) {
    return ResponseEntity.ok(menuItemService.createMenuItem(menu));
  }

  @PutMapping("{id}")
  public ResponseEntity<MenuItemResponse> updateMenu(@PathVariable Long id, @RequestBody MenuItemRequest menu) {
    return ResponseEntity.ok(menuItemService.updateMenuItem(id, menu));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Boolean> deleteMenu(@PathVariable Long id) {
    return ResponseEntity.ok(menuItemService.deleteMenuItem(id));
  }
}
