package com.example.phat_store_mvc.repositories;

import com.example.phat_store_mvc.model.entities.stock.entities.StockPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPositionRepo extends JpaRepository<StockPosition, Integer> {
}
