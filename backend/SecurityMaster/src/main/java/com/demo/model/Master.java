package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Master extends Auditable{
	@Id
private String symbol;
private String description;
private String sector;
private String industry;
private String exchange;
private String nameOfCompany;
private String series;
private String isinNumber;
private String currency;
private String country;
private String lastPrice;

@ManyToOne
private Asset asset;

public Master() {
	super();
}

public Asset getAsset() {
	return asset;
}

public void setAsset(Asset asset) {
	this.asset = asset;
}

public String getSymbol() {
	return symbol;
}
public void setSymbol(String symbol) {
	this.symbol = symbol;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getSector() {
	return sector;
}
public void setSector(String sector) {
	this.sector = sector;
}
public String getIndustry() {
	return industry;
}
public void setIndustry(String industry) {
	this.industry = industry;
}
public String getExchange() {
	return exchange;
}
public void setExchange(String exchange) {
	this.exchange = exchange;
}
public String getNameOfCompany() {
	return nameOfCompany;
}
public void setNameOfCompany(String nameOfCompany) {
	this.nameOfCompany = nameOfCompany;
}
public String getSeries() {
	return series;
}
public void setSeries(String series) {
	this.series = series;
}
public String getIsinNumber() {
	return isinNumber;
}
public void setIsinNumber(String isinNumber) {
	this.isinNumber = isinNumber;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getLastPrice() {
	return lastPrice;
}
public void setLastPrice(String lastPrice) {
	this.lastPrice = lastPrice;
}


@Override
public String toString() {
	return "Master [symbol=" + symbol + ", description=" + description + ", sector=" + sector + ", industry=" + industry
			+ ", exchange=" + exchange + ", nameOfCompany=" + nameOfCompany + ", series=" + series + ", isinNumber="
			+ isinNumber + ", currency=" + currency + ", country=" + country +  "]";
}
}