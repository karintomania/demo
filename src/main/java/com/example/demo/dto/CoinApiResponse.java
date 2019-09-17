package com.example.demo.dto;

import java.io.Serializable;

public class CoinApiResponse implements Serializable {
    private String rate;

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

 
}