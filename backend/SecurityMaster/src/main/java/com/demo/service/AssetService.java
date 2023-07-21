package com.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Asset;
import com.demo.model.Master;
import com.demo.model.Stock;
import com.demo.repository.AssetRepo;

@Service
public class AssetService {
	@Autowired
	AssetRepo repo;
	
	String line="";
	public void saveAssetData() throws IOException {
		
			try(BufferedReader br=new BufferedReader(new FileReader("src/main/resources/ASSET.csv"))){
		    while((line=br.readLine())!=null) {
		    	String [] data=line.split(",");
		    	Asset asset=new Asset();
		    	asset.setAssetId(data[0]);
		    	asset.setAssetClass(data[1]);
		    	asset.setSubAssetClass(data[2]);
		    	asset.setRisk(data[3]);
		    	asset.setInvestmentHorizon(data[4]);
		    	asset.setReturns(data[5]);
		    	asset.setLiquidity(data[6]);
		    	
	
		    	repo.save(asset);
		    }
		}
		
		    catch (FileNotFoundException f) {
				f.printStackTrace();
				
			}
		
	}

	public Asset addAsset(Asset asset){
		return  repo.save(asset);
	}

	public List<Asset> fetchAsset() {
		return repo.findAll();
	}

	
}
