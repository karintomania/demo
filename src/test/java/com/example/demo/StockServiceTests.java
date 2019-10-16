package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.example.demo.entity.Price;
import com.example.demo.service.StockService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class StockServiceTests {

	@Autowired
	StockService stockService;

	@Test
	void get_rate_test() {
		int stockCode = 2301;
		List<Price> listPrice = stockService.get_rate(stockCode);

		System.out.println(listPrice.get(0).toString());
		assertEquals(listPrice.get(0).getStockCode(),stockCode);
	}

}
