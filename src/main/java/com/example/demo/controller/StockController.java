package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Price;
import com.example.demo.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StockController {


	@Autowired
	private StockService stockService;

	// S01_チャート表示対象銘柄選択画面の表示
	@RequestMapping("/")
	public ModelAndView top(ModelAndView mav) {

		mav.setViewName("index");
		return mav;
	}

	// S02_チャート表示画面の表示
	@RequestMapping("/chart")
	public ModelAndView get_rate(@RequestParam int stockCode, ModelAndView mav) {
		List<Price> listPrice = stockService.get_rate();

		mav.setViewName("chart");
		mav.addObject("listPrice", listPrice);
		return mav;
	}

	// S03_銘柄追加画面の表示
	@RequestMapping("/addStock")
	public ModelAndView add_stock(ModelAndView mav) throws Exception {

		mav.setViewName("addStock");
		return mav;
		
    }
	// S04_銘柄登録完了画面の表示
	@RequestMapping("/addStockComplete")
	public ModelAndView add_stock_complete(@RequestParam int stockCode,ModelAndView mav) throws Exception {

		String stockCodeStr = Integer.toString(stockCode);

		stockService.add_stock(stockCode);
		mav.setViewName("addStockComplete");
		mav.addObject("stockCodeStr",stockCodeStr);
		return mav;
		
    }
}