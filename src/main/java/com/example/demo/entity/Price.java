package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int stockCode;
	private Date date;
	private int highPrice;
	private int lowPrice;
	private int openPrice;
	private int closePrice;
	private int volume;

	public int getStockCode() {
		return stockCode;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(int closePrice) {
		this.closePrice = closePrice;
	}

	public int getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(int openPrice) {
		this.openPrice = openPrice;
	}

	public int getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}

	public int getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(int highPrice) {
		this.highPrice = highPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStockCode(int stockCode) {
		this.stockCode = stockCode;
	}


	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{stockCode:"+Integer.toString(this.stockCode));
		sb.append(",date:"+this.date.toString());
		sb.append(",highPrice:"+Integer.toString(this.highPrice));
		sb.append(",lowPrice:"+Integer.toString(this.lowPrice));
		sb.append(",openPrice:"+Integer.toString(this.openPrice));
		sb.append(",closePrice:"+Integer.toString(this.closePrice));
		sb.append(",volume:"+Integer.toString(this.volume) + "}");
		return     sb.toString();

	}

}
	
