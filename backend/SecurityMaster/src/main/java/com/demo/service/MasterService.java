package com.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Asset;
import com.demo.model.Master;
import com.demo.model.PortfolioComposition;
import com.demo.model.Stock;
import com.demo.repository.AssetRepo;
import com.demo.repository.MasterRepo;
import com.demo.repository.StockRepo;


@Service
public class MasterService {
	@Autowired 
      MasterRepo repo;
	@Autowired
	StockRepo stockRepo;
   @Autowired
   AssetRepo assetRepo;

	 	
	String line="";
//	@PostConstruct
	public void saveMasterData() throws IOException {
		
			try(BufferedReader br=new BufferedReader(new FileReader("src/main/resources/MASTER.csv"))){
		    while((line=br.readLine())!=null) {
		    	String [] data=line.split(",");
		    	Master master=new Master();
		    	master.setSymbol(data[0]);
		    	master.setDescription(data[1]);
		    	master.setSector(data[2]);
		    	master.setIndustry(data[3]);
		    	master.setExchange(data[4]);
		    	master.setNameOfCompany(data[5]);
		    	master.setSeries(data[6]);
		    	master.setIsinNumber(data[7]);
		    	master.setCurrency(data[9]);
		    	master.setCountry(data[10]);
		    	String assetId=data[11];
		    	Asset asset=assetRepo.findById(assetId).orElse(null);
		    	if(asset==null) {
		        asset=new Asset();	
		        asset.setAssetId(assetId);
		        assetRepo.save(asset);
		    	}
		    	master.setAsset(asset);
		    	Stock stock=stockRepo.findBySymbol(master.getSymbol());
		    	if(stock!=null) {
		    		master.setLastPrice(stock.getLast());
		    	}
	
		    	repo.save(master);
		    }
		}
		
		    catch (FileNotFoundException f) {
				f.printStackTrace();
				
			}
		
	}

	public Optional<Master> findBySymbol(String symbol) {
		return repo.findBySymbol( symbol);
	}

	public List<Master> fetchMaster() {
		return repo.findAll();
	}

	public List<Master> findBySector(String sector) {

		return repo.findBySector(sector);
	}

	public List<Master> findByDescription(String description) {
		return repo.findByDescription(description);
	}

	public List<Master> findByIndustry(String industry) {
		return repo.findByIndustry(industry);
	}

	public List<Master> findByExchange(String exchange) {
		return repo.findByExchange(exchange);
	}

	public List<Master> findBySeries(String series) {
	
		return repo.findBySeries(series);
	}

	public List<Master> findByNameOfCompany(String nameOfCompany) {
		return repo.findByNameOfCompany(nameOfCompany);
	}

	public List<Master> findByCountry(String country) {
		return repo.findByCountry(country);
	}

	public List<Master> findByCurrency(String currency) {
		return repo.findByCurrency(currency);
	}

	public List<Master> findByIsinNumber(String isinNumber){
		return repo.findByIsinNumber(isinNumber);
	}

	public Master addMaster(Master master) {
		return repo.save(master);
	}

	public Master updateMaster(Master master) {
		return repo.save(master);
	}

	public void deleteMaster(String symbol) {
	    repo.deleteById(symbol);
	}
//   @PostConstruct
	public void saveData() throws IOException {
			try(BufferedReader br=new BufferedReader(new FileReader("src/main/resources/STOCKDATA.csv"))){
		    while((line=br.readLine())!=null) {
		    	String [] data=line.split(",");
		    	Stock stock=new Stock();
		    	stock.setSymbol(data[0]);
		    	stock.setSeries(data[1]);
		    	stock.setLast(data[2]);
		    	stockRepo.save(stock);
		    }
		    	
		} 
		catch (FileNotFoundException f) {
		   f.printStackTrace();		
		}
	}

	

	public List<Master> findByAsset(String assetId) {
		// TODO Auto-generated method stub
		return repo.findByAsset(assetId);
	}

	
}
	
	    
	
	


		
		
		
	
	


	


