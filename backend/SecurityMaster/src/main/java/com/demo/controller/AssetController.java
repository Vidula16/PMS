package com.demo.controller;

import java.io.IOException;
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

import com.demo.exception.DataNotFoundException;
import com.demo.model.Asset;
import com.demo.model.Master;
import com.demo.model.PortfolioComposition;
import com.demo.model.ThemeAsset;
import com.demo.repository.ThemeAssetRepo;
import com.demo.service.AssetService;

@RestController
@RequestMapping("/asset")
@CrossOrigin(origins="*")
public class AssetController {
	@Autowired
	AssetService service;
	@Autowired
	ThemeAssetRepo themeAssetRepo;
	
	 @RequestMapping("/assetApi") 
	  public void setAssetData() throws IOException {
	  service.saveAssetData();
	  }
	@PostMapping("/addAsset")
	public ResponseEntity<String>addAsset(@RequestBody Asset asset ){
		 service.addAsset(asset); 
		 return new  ResponseEntity<>("Data has been inserted Successfully",HttpStatus.OK); }


	@GetMapping("/fetchAsset")
	public ResponseEntity<List<Asset>>getAll(){
		List<Asset> asset=service.fetchAsset();
		return new ResponseEntity<>(asset,HttpStatus.OK);
	}
	
	
	
	}
