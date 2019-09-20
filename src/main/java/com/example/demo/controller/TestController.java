package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Stock;

@Controller
public class TestController{

	@ResponseBody
	@RequestMapping("/ajax")
	public List<Stock> ajaxResponse(){

		List<Stock> result = new ArrayList<Stock>();
		for(int i = 0;i < 2; i ++){
			Stock tmpStock = new Stock();
			tmpStock.setName(Integer.toString(i));
			tmpStock.setStockCode(i+1000);

			result.add(tmpStock);
		}

		return result;
	}
}