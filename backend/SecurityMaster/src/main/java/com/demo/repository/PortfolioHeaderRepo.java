package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.PortfolioHeader;

@Repository
public interface PortfolioHeaderRepo extends JpaRepository<PortfolioHeader,String> {

}
