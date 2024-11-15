package com.vlad.menuservice.persistence.repository;

import com.vlad.menuservice.persistence.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
  Optional<MenuItem> findByName(String name);
}