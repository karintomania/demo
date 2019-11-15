
package com.example.demo.dto;

import com.example.demo.entity.Stock;

import java.util.List;

import com.example.demo.entity.Price;

public class ChartInfo {
	private Stock stock;
	private List<Price> priceList;

	public Stock getStock() {
		return stock;
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}


}