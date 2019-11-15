package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;


import com.example.demo.dto.ChartInfo;
import com.example.demo.service.StockService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class StockServiceTests {

	@Autowired
	StockService stockService;

	@Test
	void get_chart_info_test() {
		int stockCode = 2301;
		ChartInfo chartInfo = stockService.get_chart_info(stockCode);

		assertEquals(chartInfo.getStock().getStockCode(),stockCode);
	}

}
