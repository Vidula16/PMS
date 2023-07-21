package com.demo.dto;

public class PortfolioAssetDto {

	private String assetId;

	private String assetName;

	private double allocation;

	private double initialInvestmentValue;

	private double currentAssetValue;
	private String portfolioName;
	private String themeName;
	private double investedValue;
	private double currentValue;
	private double themeAllocation;
	

	public PortfolioAssetDto(String assetId, String assetName, double allocation,double themeAllocation, double initialInvestmentValue,
			double currentAssetValue, String portfolioName, String themeName, double investedValue,
			double currentValue) {
	
		this.assetId = assetId;
		this.assetName = assetName;
		this.allocation = allocation;
		this.themeAllocation=themeAllocation;
		this.initialInvestmentValue = initialInvestmentValue;
		this.currentAssetValue = currentAssetValue;
		this.portfolioName = portfolioName;
		this.themeName = themeName;
		this.investedValue = investedValue;
		this.currentValue = currentValue;
	}

	
	public double getThemeAllocation() {
		return themeAllocation;
	}


	public void setThemeAllocation(double themeAllocation) {
		this.themeAllocation = themeAllocation;
	}


	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public double getInvestedValue() {
		return investedValue;
	}

	public void setInvestedValue(double investedValue) {
		this.investedValue = investedValue;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public double getCurrentAssetValue() {

		return currentAssetValue;

	}

	public void setCurrentAssetValue(double currentAssetValue) {

		this.currentAssetValue = currentAssetValue;

	}

	public String getAssetId() {

		return assetId;

	}

	public void setAssetId(String assetId) {

		this.assetId = assetId;

	}

	public String getAssetName() {

		return assetName;

	}

	public void setAssetName(String assetName) {

		this.assetName = assetName;

	}

	public double getAllocation() {

		return allocation;

	}

	public void setAllocation(double allocation) {

		this.allocation = allocation;

	}

	public double getInitialInvestmentValue() {

		return initialInvestmentValue;

	}

	public void setInitialInvestmentValue(double initialInvestmentValue) {

		this.initialInvestmentValue = initialInvestmentValue;

	}

}