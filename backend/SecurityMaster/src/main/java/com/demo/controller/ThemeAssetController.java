package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ThemeAssetResponseDto;
import com.demo.exception.DataNotFoundException;
import com.demo.model.Asset;
import com.demo.model.Master;
import com.demo.model.PortfolioComposition;
import com.demo.model.PortfolioHeader;
import com.demo.model.Theme;
import com.demo.model.ThemeAsset;
import com.demo.repository.AssetRepo;
import com.demo.repository.PortfolioHeaderRepo;
import com.demo.repository.ThemeAssetRepo;
import com.demo.repository.ThemeRepo;
import com.demo.service.ThemeAssetService;

@RestController
@RequestMapping("/themeAsset")
@CrossOrigin(origins = "*")
public class ThemeAssetController {

	@Autowired
	ThemeAssetService service;
	@Autowired
	AssetRepo assetRepo;
	@Autowired
	ThemeAssetRepo repo;
	@Autowired
	ThemeRepo themeRepo;

	@PostMapping("/addThemeAsset/{assetId}")
	public ResponseEntity<String> addThemeAsset(@RequestBody ThemeAssetResponseDto themeAssetResponseDto,
			@PathVariable String assetId) {
		Optional<Asset> optional = assetRepo.findById(assetId);
		if (!optional.isPresent()) {
			return new ResponseEntity<>("Invalid Asset", HttpStatus.BAD_REQUEST);
		}
		System.out.println("error");
		Asset asset = optional.get();
		Theme theme = themeRepo.findById(themeAssetResponseDto.getThemeName()).orElse(null);
		if (theme == null) {
			theme = new Theme();
			theme.setThemeName(themeAssetResponseDto.getThemeName());
			themeRepo.save(theme);
		}

		ThemeAsset themeAsset = new ThemeAsset();
		themeAsset.setAsset(asset);
		themeAsset.setTheme(theme);
		themeAsset.setAllocation(themeAssetResponseDto.getAllocation());

		/*
		 * Double
		 * totalAllocationPercentage=repo.getTotalAllocationPercentageByThemeName(
		 * themeAssetResponseDto.getThemeName()); if(totalAllocationPercentage==null) {
		 * totalAllocationPercentage=0.0; } if
		 * (totalAllocationPercentage+themeAssetResponseDto.getAllocation()>100.0) {
		 * throw new DataNotFoundException("exceed 100%"); }
		 */

		String themeName = theme.getThemeName().toString();

		String assetClass = asset.getAssetClass().toString();

		if (themeName.equals("AGGRESSIVE"))

		{

			if (assetClass.equals("Equities"))

			{

				themeAsset.setThemeAllocation(60);

			}

			else if (assetClass.equals("Cash and cash equivalents"))

			{

				themeAsset.setThemeAllocation(15);

			}

			else if (assetClass.equals("Mutual Fund"))

			{

				themeAsset.setThemeAllocation(15);

			}

			else if (assetClass.equals("Fixed income"))

			{

				themeAsset.setThemeAllocation(10);

			}

		}

		if (themeName.equals("CONSERVATIVE"))

		{

			if (assetClass.equals("Equities"))

			{

				themeAsset.setThemeAllocation(40);

			}

			else if (assetClass.equals("Cash and cash equivalents"))

			{

				themeAsset.setThemeAllocation(20);

			}

			else if (assetClass.equals("Mutual Fund"))

			{

				themeAsset.setThemeAllocation(20);

			}

			else if (assetClass.equals("Fixed income"))

			{

				themeAsset.setThemeAllocation(20);

			}

		}

		if (themeName.equals("MODERATIVE"))

		{

			if (assetClass.equals("Equities"))

			{

				themeAsset.setThemeAllocation(50);

			}

			else if (assetClass.equals("Cash and cash equivalents"))

			{

				themeAsset.setThemeAllocation(20);

			}

			else if (assetClass.equals("Mutual Fund"))

			{

				themeAsset.setThemeAllocation(20);

			}

			else if (assetClass.equals("Fixed income"))

			{

				themeAsset.setThemeAllocation(10);

			}

		}

		if (themeName.equals("VERY AGGRESSIVE"))

		{

			if (assetClass.equals("Equities"))

			{

				themeAsset.setThemeAllocation(70);

			}

			else if (assetClass.equals("Cash and cash equivalents"))

			{

				themeAsset.setThemeAllocation(10);

			}

			else if (assetClass.equals("Mutual Fund"))

			{

				themeAsset.setThemeAllocation(10);

			}

			else if (assetClass.equals("Fixed income"))

			{

				themeAsset.setThemeAllocation(10);

			}

		}
		service.addThemeAsset(themeAsset);
		return new ResponseEntity<>("theme added successfully", HttpStatus.OK);

	}

	@GetMapping("/fetchThemeAsset")
	public ResponseEntity<List<ThemeAsset>> fetchThemeAsset() {
		List<ThemeAsset> themeAsset = service.fetchThemeAsset();
		return new ResponseEntity<>(themeAsset, HttpStatus.OK);
	}

	@GetMapping("/fetchAssetByTheme/{themeName}")
	public ResponseEntity<List<ThemeAsset>> findByAsset(@PathVariable String themeName) {
		List<ThemeAsset> object = service.findByAsset(themeName);
		if (object.isEmpty()) {
			throw new DataNotFoundException("Given theme is not available");
		} else {
			return new ResponseEntity<>(object, HttpStatus.OK);
		}

	}
}
