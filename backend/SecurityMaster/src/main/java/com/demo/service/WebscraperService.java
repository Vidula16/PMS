package com.demo.service;

import javax.lang.model.util.Elements;
import javax.swing.text.Document;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class WebscraperService {
	public String fetchPriceBySymbol(String symbol) {

		try {
		String url = "https://markets.ft.com/data/equities/tearsheet/summary?s=" + symbol;

		org.jsoup.nodes.Document document = Jsoup.connect(url).get();

		org.jsoup.select.Elements elements = document.select("span[class=mod-ui-data-list__value]");

		String price= elements.get(0).text();

		return price;

		} catch (Exception e) {

		e.printStackTrace();

		}

		 

		return null;

		}
	
}
