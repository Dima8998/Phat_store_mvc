package com.example.phat_store_mvc.repositories;

import com.example.phat_store_mvc.model.entities.stock.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    @Query(value = "SELECT * FROM item_t WHERE category_id = ?1", nativeQuery = true)
    List<Item> filterByCategoryId(int categoryId);
}
