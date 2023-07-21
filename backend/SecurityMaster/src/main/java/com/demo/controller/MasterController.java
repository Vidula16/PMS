package com.demo.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.exception.DataNotFoundException;
import com.demo.model.Master;
import com.demo.model.PortfolioComposition;
import com.demo.repository.MasterRepo;
import com.demo.service.MasterService;
import com.demo.service.WebscraperService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/master")
public class MasterController {
	@Autowired
	MasterService service;
	@Autowired
	MasterRepo masterRepo;
	@Autowired
	WebscraperService webscraperService;

	@RequestMapping("/api")
	public void setMasterData() throws IOException {
		service.saveMasterData();
	}

	@RequestMapping("/usm")
	public void setData() throws IOException {
		service.saveData();
	}

	@PostMapping("/addMaster")
	public ResponseEntity<String> addMaster(@RequestBody Master master) {
		service.addMaster(master);
		return new ResponseEntity<>("Data has been inserted Successfully", HttpStatus.OK);
	}

	@GetMapping("/fetchData")
	public ResponseEntity<List<Master>> fetchData() {
		List<Master> master = service.fetchMaster();
		return new ResponseEntity<>(master, HttpStatus.OK);
	}

	@GetMapping("/fetchDataBySymbol/{symbol}")
	public ResponseEntity<Optional<Master>> findBySymbol(@PathVariable String symbol) {
		Optional<Master> master = service.findBySymbol(symbol);
		if (master.isPresent()) {
			return new ResponseEntity<>(master, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Given symbol is not available");
		}
	}

	@GetMapping("/fetchByAsset/{assetId}")
	public ResponseEntity<List<Master>> findByName(@PathVariable String assetId) {
		List<Master> object = service.findByAsset(assetId);
		if (object.isEmpty()) {
			throw new DataNotFoundException("Given Asset is not available");
		} else {
			return new ResponseEntity<>(object, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataBySector/{sector}")
	public ResponseEntity<List<Master>> findBySector(@PathVariable String sector) {
		List<Master> master = service.findBySector(sector);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given sector is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataByDescription/{description}")
	public ResponseEntity<List<Master>> findByDescription(@PathVariable String description) {
		List<Master> master = service.findByDescription(description);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given description is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataByIndustry/{industry}")
	public ResponseEntity<List<Master>> findByIndustry(@PathVariable String industry) {
		List<Master> master = service.findByIndustry(industry);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given industry is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataByExchange/{exchange}")
	public ResponseEntity<List<Master>> findByExchange(@PathVariable String exchange) {
		List<Master> master = service.findByExchange(exchange);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given exchange is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataBySeries/{series}")
	public ResponseEntity<List<Master>> findBySeries(@PathVariable String series) {
		List<Master> master = service.findBySeries(series);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given series is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataByNameOfCompany/{nameOfCompany}")
	public ResponseEntity<List<Master>> findByNameOfCompany(@PathVariable String nameOfCompany) {
		List<Master> master = service.findByNameOfCompany(nameOfCompany);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given company is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataByCountry/{country}")
	public ResponseEntity<List<Master>> findByCountry(@PathVariable String country) {
		List<Master> master = service.findByCountry(country);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given country is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataByCurrency/{currency}")
	public ResponseEntity<List<Master>> findByCurrency(@PathVariable String currency) {
		List<Master> master = service.findByCurrency(currency);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given cuurency is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchDataByIsinNumber/{isinNumber}")
	public ResponseEntity<List<Master>> findByIsinNumber(@PathVariable String isinNumber) {
		List<Master> master = service.findByIsinNumber(isinNumber);
		if (master.isEmpty()) {
			throw new DataNotFoundException("Given ISINNumber is not available");
		} else {
			return new ResponseEntity<>(master, HttpStatus.OK);
		}
	}

	@PutMapping("/updateMaster/{symbol}")
	public ResponseEntity<Master> updateMaster(@RequestBody Master master, @PathVariable String symbol) {
		Master object = service.updateMaster(master);
		return new ResponseEntity<>(object, HttpStatus.OK);
	}

	@DeleteMapping("/deleteMaster/{symbol}")
	public ResponseEntity<String> deleteMaster(@PathVariable String symbol) {
		service.deleteMaster(symbol);
		return new ResponseEntity<>("Data has been deleted successfully", HttpStatus.OK);
	}

	//update lastprice (Webscraping)
	@GetMapping("/data/{symbol}")

	public String fetchData(@PathVariable String symbol) {

		Master master = masterRepo.findBySymbol2(symbol);

		String updatedPrice = webscraperService.fetchPriceBySymbol(symbol);

		// String oldPrice=masterRepository.getLastPriceBySyambol(symbol);

		master.setLastPrice(updatedPrice);

		masterRepo.save(master);

		// Process and return the data as needed

		return updatedPrice;

	}

	@PutMapping("/updatePrice/{symbol}/updateLastPrice")

	public ResponseEntity<String> updateStockLastPrice(@PathVariable("symbol") String symbol,
			@RequestParam("lastPrice") String lastPrice) {

		Optional<Master> optionalStock = masterRepo.findBySymbolForPrice(symbol);

		if (optionalStock.isPresent()) {

			Master stock = optionalStock.get();

			stock.setLastPrice(lastPrice);

			masterRepo.save(stock);

			return ResponseEntity.ok("Stock last price updated successfully");

		}

		return ResponseEntity.notFound().build();

	}

}
