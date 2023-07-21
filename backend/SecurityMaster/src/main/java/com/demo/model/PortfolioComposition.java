package com.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.service.PortfolioCompositionService;

@Entity
public class PortfolioComposition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int compositionId;
	private LocalDate transactionDate;
	private String securityName;
	private String equityCategory;
	private String exchangeName;
	private int units;
	private double price;
	private double allocatedValue;
	private double totalTransaction;
	private double availableBalance;
	private double investmentValue;
	private String transactionType;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "portfolioName", referencedColumnName = "portfolioName")
	private PortfolioHeader portfolioHeader;
	private boolean deleteStatus;
	@ManyToOne
	private Master master;
	@ManyToOne
	private Asset asset;

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public int getCompositionId() {
		return compositionId;
	}

	public void setCompositionId(int compositionId) {
		this.compositionId = compositionId;
	}

//	public LocalDateTime getTransactionDate() {
//		return transactionDate;
//	}
//
//	public void setTransactionDate(LocalDateTime transactionDate) {
//		this.transactionDate = transactionDate;
//	}
	
	

	public String getSecurityName() {
		return securityName;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String getEquityCategory() {
		return equityCategory;
	}

	public void setEquityCategory(String equityCategory) {
		this.equityCategory = equityCategory;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAllocatedValue() {
		return allocatedValue;
	}

	public void setAllocatedValue(double allocatedValue) {
		this.allocatedValue = allocatedValue;
	}

	public double getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(double totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public double getInvestmentValue() {
		return investmentValue;
	}

	public void setInvestmentValue(double investmentValue) {
		this.investmentValue = investmentValue;
	}

	public PortfolioHeader getPortfolioHeader() {
		return portfolioHeader;
	}

	public void setPortfolioHeader(PortfolioHeader portfolioHeader) {
		this.portfolioHeader = portfolioHeader;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
