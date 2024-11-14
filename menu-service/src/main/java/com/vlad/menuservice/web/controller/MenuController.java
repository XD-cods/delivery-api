package com.vlad.menuservice.web.controller;

import com.vlad.menuservice.service.MenuService;
import com.vlad.menuservice.web.request.MenuRequest;
import com.vlad.menuservice.web.response.MenuResponse;
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
@RequestMapping("/api/menu/")
@RequiredArgsConstructor
public class MenuController {
  private final MenuService menuService;

  @GetMapping("{id}")
  public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
    return ResponseEntity.ok(menuService.getMenuById(id));
  }

  @GetMapping
  public ResponseEntity<List<MenuResponse>> getAllMenus() {
    return ResponseEntity.ok(menuService.getAllMenu());
  }

  @PostMapping
  public ResponseEntity<MenuResponse> addMenu(@RequestBody MenuRequest menu) {
    return ResponseEntity.ok(menuService.createMenu(menu));
  }

  @PutMapping("{id}")
  public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long id, @RequestBody MenuRequest menu) {
    return ResponseEntity.ok(menuService.updateMenu(id, menu));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Boolean> deleteMenu(@PathVariable Long id) {
    return ResponseEntity.ok(menuService.deleteMenu(id));
  }

}
