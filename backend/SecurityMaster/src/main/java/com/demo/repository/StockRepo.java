package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Stock;
@Repository
public interface StockRepo extends JpaRepository<Stock,String> {
Stock findBySymbol(String symbol);
}
