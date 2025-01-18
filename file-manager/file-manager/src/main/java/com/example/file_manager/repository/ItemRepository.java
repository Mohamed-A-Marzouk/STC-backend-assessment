package com.example.file_manager.repository;

import com.example.file_manager.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByNameAndType(String name, String type);
}