package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Master;
import com.demo.model.PortfolioComposition;

@Repository
public interface MasterRepo extends JpaRepository<Master, String> {

	@Query("select m FROM Master m WHERE m.symbol LIKE %:symbol%")
	public Optional<Master> findBySymbol(String symbol);

	@Query(" select m FROM Master m WHERE m.sector LIKE %:sector%")
	public List<Master> findBySector(String sector);

	@Query("FROM Master WHERE description=?1")
	public List<Master> findByDescription(String description);

	@Query("SELECT m FROM Master m WHERE m.industry LIKE %:industry%")
	public List<Master> findByIndustry(String industry);

	@Query("FROM Master WHERE exchange=?1")
	public List<Master> findByExchange(String exchange);

	@Query("FROM Master WHERE series=?1")
	public List<Master> findBySeries(String series);

	@Query("FROM Master WHERE nameOfCompany=?1")
	public List<Master> findByNameOfCompany(String nameOfCompany);

	@Query("FROM Master WHERE country=?1")
	public List<Master> findByCountry(String country);

	@Query("FROM Master WHERE currency=?1")
	public List<Master> findByCurrency(String currency);

	@Query("FROM Master WHERE isinNumber=?1")
	public List<Master> findByIsinNumber(String isinNumber);

	@Query("select m from Master m where m.asset.assetId=?1")
	List<Master> findByAsset(String assetId);

	@Query("select m from Master m where m.symbol=?1")
	Optional<Master> findBySymbolForPrice(String symbol);

	@Query("select m from Master m where m.symbol=?1")
	public Master findBySymbol2(String symbol);
}
