package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.StockSearchCriteria;
import com.example.demo.entity.Stock;

@Controller
public class TestController{


	@RequestMapping("/ajaxTest")
	public ModelAndView ajaxTest(ModelAndView mav){
		System.out.println("ajaxTest Called");
		mav.setViewName("ajaxTest");
		return mav;
	}


	@ResponseBody
	@PostMapping("/ajax")
	public List<Stock> ajaxResponse(StockSearchCriteria ssc){
		System.out.println("debug");

		System.out.println("銘柄コード"+ssc.getStockCode());

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