package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {
	@Id
private String symbol;
private String series;
private String last;
public Stock() {
	super();
}
public Stock(String symbol, String series, String last) {
	super();
	this.symbol = symbol;
	this.series = series;
	this.last = last;
}
public String getSymbol() {
	return symbol;
}
public void setSymbol(String symbol) {
	this.symbol = symbol;
}
public String getSeries() {
	return series;
}
public void setSeries(String series) {
	this.series = series;
}
public String getLast() {
	return last;
}
public void setLast(String last) {
	this.last = last;
}
@Override
public String toString() {
	return "Stock [symbol=" + symbol + ", series=" + series + ", last=" + last + "]";
}

}
