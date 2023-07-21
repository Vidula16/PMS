package com.demo.dto;
public class PortfolioCompositionDto {
	private String porfolioName;
	private String benchMark;
	private String themeName;
	private double investmentValue;
	private double allocatedValue;
	//private int noOfSecurities;
	private String fundMangerName;
	public String getFundMangerName() {
		return fundMangerName;
	}
	public void setFundMangerName(String fundMangerName) {
		this.fundMangerName = fundMangerName;
	}
	public String getPorfolioName() {
		return porfolioName;
	}
	public void setPorfolioName(String porfolioName) {
		this.porfolioName = porfolioName;
	}
	public String getBenchMark() {
		return benchMark;
	}
	public void setBenchMark(String benchMark) {
		this.benchMark = benchMark;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public double getInvestmentValue() {
		return investmentValue;
	}
	public void setInvestmentValue(double investmentValue) {
		this.investmentValue = investmentValue;
	}
	public double getAllocatedValue() {
		return allocatedValue;
	}
	public void setAllocatedValue(double allocatedValue) {
		this.allocatedValue = allocatedValue;
	}
	/*
	 * public int getNoOfSecurities() { return noOfSecurities; } public void
	 * setNoOfSecurities(int noOfSecurities) { this.noOfSecurities = noOfSecurities;
	 * }
	 * 
	 */
}

