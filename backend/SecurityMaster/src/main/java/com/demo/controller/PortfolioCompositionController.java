package com.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.demo.dto.PortfolioCompositionDto;
import com.demo.exception.DataNotFoundException;
import com.demo.model.Asset;
import com.demo.model.Master;
import com.demo.model.PortfolioComposition;
import com.demo.model.PortfolioHeader;
import com.demo.model.ThemeAsset;
import com.demo.repository.AssetRepo;
import com.demo.repository.MasterRepo;
import com.demo.repository.PortfolioCompositionRepo;
import com.demo.repository.PortfolioHeaderRepo;
import com.demo.service.PortfolioCompositionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/compo")
public class PortfolioCompositionController {

	@Autowired
	PortfolioCompositionService service;

	@Autowired
	PortfolioCompositionRepo portfolioCompositionRepo;

	@Autowired
	PortfolioHeaderRepo repo;

	@Autowired
	MasterRepo masterRepo;
	@Autowired
	AssetRepo assetRepo;
	public static double totalTransaction;

	@PostMapping("/addCompo/{portfolioName}/{assetId}/{symbol}")

	public ResponseEntity<String> addCompo(@RequestBody PortfolioComposition portfolioComposition,

			@PathVariable("portfolioName") String portfolioName, @PathVariable("assetId") String assetId,
			@PathVariable("symbol") String symbol) {

		Optional<PortfolioHeader> optional = repo.findById(portfolioName);

		if (!optional.isPresent())

			return new ResponseEntity<>("Invalid portfolio", HttpStatus.BAD_REQUEST);

		PortfolioHeader portfolioHeader = optional.get();

		portfolioComposition.setPortfolioHeader(portfolioHeader);

		Optional<Asset> optionalA = assetRepo.findById(assetId);

		if (!optionalA.isPresent())

			return new ResponseEntity<>("Invalid Asset", HttpStatus.BAD_REQUEST);

		Asset asset = optionalA.get();

		portfolioComposition.setAsset(asset);

		Optional<Master> optionalM = masterRepo.findById(symbol);

		if (!optionalM.isPresent())

			return new ResponseEntity<>("Invalid securities", HttpStatus.BAD_REQUEST);

		Master master = optionalM.get();

		portfolioComposition.setMaster(master);

		portfolioComposition.setSecurityName(master.getNameOfCompany());

		portfolioComposition.setExchangeName(master.getExchange());

		double price = Double.parseDouble(master.getLastPrice());

		portfolioComposition.setPrice(price);

		int units = portfolioComposition.getUnits();

		double allocatedValue = price * units;

		portfolioComposition.setAllocatedValue(Math.round(allocatedValue));
		portfolioComposition.setTransactionType("BUY");
		portfolioComposition.setTotalTransaction(Math.round(allocatedValue));
		double totalAllocatedValue = allocatedValue;
		List<PortfolioComposition> portfolioCompositions = portfolioCompositionRepo.findByPortfolioName(portfolioName);
		for (PortfolioComposition composition : portfolioCompositions) {
			totalAllocatedValue += composition.getAllocatedValue();
		}
		portfolioComposition.setTotalTransaction(Math.round(totalAllocatedValue));

		double investmentValue = portfolioHeader.getInvestmentValue();

		portfolioComposition.setInvestmentValue(investmentValue);
		List<PortfolioComposition> compositions = portfolioCompositionRepo
				.findByPortfolioName(portfolioHeader.getPortfolioName());

		long noOfSecurities = compositions.stream().count();
		if (!compositions.isEmpty()) {
			++noOfSecurities;
		}
		portfolioHeader.setNoOfSecurities(noOfSecurities);

		portfolioHeader.setStatus("Active");
		portfolioHeader.setCurrentValue(investmentValue + portfolioComposition.getTotalTransaction());
		if (allocatedValue > investmentValue) {

			return new ResponseEntity<>("you have exceeded your invested Amount,decrease the units",
					HttpStatus.BAD_REQUEST);

		}

		else {

			service.addCompo(portfolioComposition);

			return ResponseEntity.status(HttpStatus.OK).body("Security added Successfully!");

		}

	}

	@PutMapping("/sellSecurity/{portfolioName}/{symbol}")
	public ResponseEntity<String> sellSecurity(@PathVariable("portfolioName") String portfolioName, @PathVariable("symbol") String symbol) {
	List<PortfolioComposition> compositions = portfolioCompositionRepo.findByPortfolioName(portfolioName);
	for (PortfolioComposition composition : compositions) {
	if (composition.getMaster().getSymbol().equals(symbol)) {
	// Perform actions on the composition
	composition.setTransactionType("Sell");
	portfolioCompositionRepo.save(composition);

	// Remove the records from the frontend
	// ...

	return ResponseEntity.ok("Security sold successfully");
	}
	}
	return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/fetchCompo")
	public ResponseEntity<List<PortfolioComposition>> getComposition() {
		List<PortfolioComposition> object = service.fetchComposition();
		return new ResponseEntity<>(object, HttpStatus.OK);
	}

	@GetMapping("/getData")
	public List<PortfolioCompositionDto> getData() {
		List<PortfolioComposition> list = portfolioCompositionRepo.findAll();
		List<PortfolioCompositionDto> listDto = new ArrayList<>();
		for (PortfolioComposition p : list) {
			PortfolioHeader ph = p.getPortfolioHeader();
			PortfolioCompositionDto dto = new PortfolioCompositionDto();
			dto.setPorfolioName(ph.getPortfolioName());
			dto.setFundMangerName(ph.getFundManagerName());
			dto.setThemeName(ph.getInvestmentTheme());
			dto.setBenchMark(ph.getBenchMark());
			dto.setInvestmentValue(ph.getInvestmentValue());
			// dto.setNoOfSecurities(p.getUnits());
			dto.setAllocatedValue(p.getAllocatedValue());
			listDto.add(dto);
		}
		return listDto;
	}

	@GetMapping("/fetchByName/{portfolioName}")
	public ResponseEntity<List<PortfolioComposition>> findByName(@PathVariable String portfolioName) {
		List<PortfolioComposition> object = service.findByName(portfolioName);
		if (object.isEmpty()) {
			throw new DataNotFoundException("Given name is not available");
		} else {
			return new ResponseEntity<>(object, HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{portfolioName}")
	public ResponseEntity<String> delete(@PathVariable String portfolioName) {
		service.delete(portfolioName);
		return new ResponseEntity<>("Data has been deleted successfully", HttpStatus.OK);
	}

	// soft delete for securitites
	@DeleteMapping("/{portfolioName}/securities")

	public ResponseEntity<String> softDeleteSecuritiesFromPortfolio(@PathVariable("portfolioName") String portfolioName,
			@RequestBody List<String> securityIds) {

		Optional<PortfolioHeader> optionalPortfolioHeader = repo.findById(portfolioName);

		if (optionalPortfolioHeader.isPresent()) {

			PortfolioHeader portfolioHeader = optionalPortfolioHeader.get();

			List<PortfolioComposition> portfolioCompositionList = portfolioCompositionRepo
					.findByPortfolioName(portfolioName);

			List<PortfolioComposition> compositionsToDelete = new ArrayList<>();

			for (String securityId : securityIds) {

				Optional<PortfolioComposition> optionalComposition = portfolioCompositionList.stream()

						.filter(composition -> composition.getMaster().getSymbol().equals(securityId))

						.findFirst();

				optionalComposition.ifPresent(compositionsToDelete::add);

			}

			if (!compositionsToDelete.isEmpty()) {

				compositionsToDelete.forEach(composition -> composition.setDeleteStatus(true));

				portfolioCompositionRepo.saveAll(compositionsToDelete);

			}

			return ResponseEntity.ok("Securities deleted successfully");

		}

		return ResponseEntity.notFound().build();

	}

}