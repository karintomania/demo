
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.demo.entity.Price;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
	List<Price> findByStockCode(int stockCode);
	List<Price> findByStockCodeOrderByDateAsc(int stockCode);
}