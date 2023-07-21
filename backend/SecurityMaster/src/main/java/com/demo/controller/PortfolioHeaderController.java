package com.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.PortfolioAssetDto;
import com.demo.exception.DataNotFoundException;
import com.demo.model.Asset;
import com.demo.model.Master;
import com.demo.model.PortfolioHeader;
import com.demo.model.Theme;
import com.demo.model.ThemeAsset;
import com.demo.repository.PortfolioHeaderRepo;
import com.demo.repository.ThemeAssetRepo;
import com.demo.repository.ThemeRepo;
import com.demo.service.PortfolioHeaderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/portfolio")
public class PortfolioHeaderController {
	@Autowired
	PortfolioHeaderService service;
	@Autowired
	ThemeRepo themeRepo;
	@Autowired
	PortfolioHeaderRepo repo;
	@Autowired
	ThemeAssetRepo themeAssetRepo;

	@PostMapping("/addPortfolio/{themeName}")
	public ResponseEntity<String> addPortfolio(@RequestBody PortfolioHeader portfolioHeader,
			@PathVariable("themeName") String themeName) {
		Optional<Theme> optional = themeRepo.findById(themeName);
		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Theme");
		Theme theme = optional.get();
		portfolioHeader.setTheme(theme);
		portfolioHeader.setInvestmentTheme(themeName);
		portfolioHeader.setStatus("New");
		service.addPortfolio(portfolioHeader);
		return new ResponseEntity<>("Portfolio added Successfully!", HttpStatus.OK);
	}

	//themeasset calculation
	@GetMapping("/{portfolioName}")

	public ResponseEntity<List<PortfolioAssetDto>> getPortfolioAssets(
			@PathVariable("portfolioName") String portfolioName) {

		Optional<PortfolioHeader> portfolioHeaderOptional = repo.findById(portfolioName);

		if (!portfolioHeaderOptional.isPresent()) {

			return ResponseEntity.notFound().build();

		}

		PortfolioHeader portfolioHeader = portfolioHeaderOptional.get();

		String themeName = portfolioHeader.getTheme().getThemeName();

		List<ThemeAsset> themeAssets = themeAssetRepo.findByAsset(themeName);

		List<PortfolioAssetDto> portfolioAssets = new ArrayList<>();

		double investedValue = portfolioHeader.getInvestmentValue();

		double currentValue = portfolioHeader.getCurrentValue();

		for (ThemeAsset themeAsset : themeAssets) {

			Asset asset = themeAsset.getAsset();

			double allocation = themeAsset.getAllocation();

			double themeAllocation = themeAsset.getThemeAllocation();
			String portfolioName1=portfolioHeader.getPortfolioName();
			String themeName1=portfolioHeader.getTheme().getThemeName();
		
			double initialInvestmentValue = investedValue * themeAllocation / 100.0;

			double currentAssetValue = currentValue * allocation / 100.0;

			PortfolioAssetDto assetDto = new PortfolioAssetDto(asset.getAssetId(), asset.getAssetClass(), allocation,themeAllocation,
					initialInvestmentValue, currentAssetValue,portfolioName1,themeName1,investedValue,currentValue);

			portfolioAssets.add(assetDto);

		}

		return ResponseEntity.ok(portfolioAssets);

	}

	@GetMapping("/fetchPortfolio")
	public ResponseEntity<List<PortfolioHeader>> getAll() {
		List<PortfolioHeader> object = service.fetchPortfolio();
		return new ResponseEntity<>(object, HttpStatus.OK);
	}

	@GetMapping("/fetchPortfolioByName/{portfolioName}")
	public ResponseEntity<Optional<PortfolioHeader>> findByPortfolioName(@PathVariable String portfolioName) {
		Optional<PortfolioHeader> object = service.findByPortfolioName(portfolioName);
		if (object.isEmpty()) {
			throw new DataNotFoundException("Given portfolioname is not available");
		} else {
			return new ResponseEntity<>(object, HttpStatus.OK);
		}
	}

	@DeleteMapping("/closePortfolio/{portfolioName}")

	public ResponseEntity<String> softDeletePortfolio(@PathVariable("portfolioName") String portfolioName) {

		Optional<PortfolioHeader> optionalPortfolioHeader = repo.findById(portfolioName);

		if (optionalPortfolioHeader.isPresent()) {

			PortfolioHeader portfolioHeader = optionalPortfolioHeader.get();

			// Set the status of the PortfolioHeader to "closed"

			portfolioHeader.setStatus("Closed");

			repo.save(portfolioHeader);

			// Remove the records from the frontend

			// ...

			return ResponseEntity.ok("Portfolio Closed successfully");

		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/OpenPortfolio/{portfolioName}")

	public ResponseEntity<String> softOpenPortfolio(@PathVariable("portfolioName") String portfolioName) {

		Optional<PortfolioHeader> optionalPortfolioHeader = repo.findById(portfolioName);

		if (optionalPortfolioHeader.isPresent()) {

			PortfolioHeader portfolioHeader = optionalPortfolioHeader.get();

			// Set the status of the PortfolioHeader to "closed"

			portfolioHeader.setStatus("Active");

			repo.save(portfolioHeader);

			// Remove the records from the frontend

			// ...

			return ResponseEntity.ok("Portfolio Open successfully");

		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/update/{portfolioName}/{investmentValue}")
	public ResponseEntity<String> updateInvestmentValue(@PathVariable("investmentValue") double investmentValue,
			@PathVariable("portfolioName") String portfolioName) {
		Optional<PortfolioHeader> optional = repo.findById(portfolioName);
		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Portfolio doest not exists");
		PortfolioHeader portfolioHeader = optional.get();
		portfolioHeader.setInvestmentValue(investmentValue);
		repo.save(portfolioHeader);
		return ResponseEntity.status(HttpStatus.OK).body("Investment value updated");
	}

//	@GetMapping("/{portfolioName}/assets")
//
//	public ResponseEntity<List<PortfolioAssetDto>> getPortfolioAssets(
//			@PathVariable("portfolioName") String portfolioName) {
//
//		Optional<PortfolioHeader> portfolioHeaderOptional = repo.findById(portfolioName);
//
//		if (!portfolioHeaderOptional.isPresent()) {
//
//			return ResponseEntity.notFound().build();
//
//		}
//
//		PortfolioHeader portfolioHeader = portfolioHeaderOptional.get();
//
//		String themeName = portfolioHeader.getTheme().getThemeName();
//
//		List<ThemeAsset> themeAssets = themeAssetRepo.findByAsset(themeName);
//
//		List<PortfolioAssetDto> portfolioAssets = new ArrayList<>();
//
//		double investedValue = portfolioHeader.getInvestmentValue();
//
//		double currentValue = portfolioHeader.getCurrentValue();
//
//		for (ThemeAsset themeAsset : themeAssets) {
//
//			Asset asset = themeAsset.getAsset();
//
//			double allocation = themeAsset.getAllocation();
//
//			double themeAllocation = themeAsset.getThemeAllocation();
//
//			double initialInvestmentValue = investedValue * themeAllocation / 100.0;
//
//			double currentAssetValue = currentValue * allocation / 100.0;
//
//			PortfolioAssetDto assetDto = new PortfolioAssetDto(asset.getAssetId(), asset.getAssetClass(), allocation,
//					initialInvestmentValue, currentAssetValue);
//
//			portfolioAssets.add(assetDto);
//
//		}
//
//		return ResponseEntity.ok(portfolioAssets);
//
//	}

}
