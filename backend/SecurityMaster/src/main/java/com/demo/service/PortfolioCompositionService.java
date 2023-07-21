package com.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.PortfolioComposition;
import com.demo.model.PortfolioHeader;
import com.demo.repository.PortfolioCompositionRepo;

@Service
public class PortfolioCompositionService {
@Autowired
PortfolioCompositionRepo repo;

public PortfolioComposition addCompo(PortfolioComposition compo) {
	return repo.save(compo);
	/*
	 * PortfolioHeaderService s = new PortfolioHeaderService(); PortfolioHeader
	 * portfolio=new PortfolioHeader(); Optional<PortfolioHeader> p =
	 * s.findByPortfolioName(portfolio.getPortfolioName()); return p;
	 */
	
}
public List<PortfolioComposition> fetchComposition() {
	return repo.findAll();
}
public List<PortfolioComposition> findByName(String portfolioName) {
	return repo.findByPortfolioName(portfolioName);
}
public void delete(String portfolioName) {
	repo.deleteById(portfolioName);
	
}


}

