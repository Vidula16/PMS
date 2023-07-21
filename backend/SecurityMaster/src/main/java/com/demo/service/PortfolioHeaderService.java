package com.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.PortfolioComposition;
import com.demo.model.PortfolioHeader;
import com.demo.repository.PortfolioHeaderRepo;
@Service
public class PortfolioHeaderService {
@Autowired
PortfolioHeaderRepo repo;

public PortfolioHeader addPortfolio(PortfolioHeader portfolio) {

	return repo.save(portfolio);
	/*
	 * PortfolioHeaderService s = new PortfolioHeaderService();
	 * Optional<PortfolioHeader> p =
	 * s.findByPortfolioName(portfolio.getPortfolioName()); return p;
	 */
}

public List<PortfolioHeader> fetchPortfolio() {
	return repo.findAll();
}

public Optional<PortfolioHeader> findByPortfolioName(String portfolioName) {
	return repo.findById(portfolioName);
}

public void delete(String portfolioName) {
    repo.deleteById(portfolioName);
	
}
	
}
