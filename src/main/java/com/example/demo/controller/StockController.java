package com.example.demo.controller;

import java.util.List;

import com.example.demo.constant.Const;
import com.example.demo.dto.ChartInfo;
import com.example.demo.dto.StockSearchCriteria;
import com.example.demo.entity.Stock;
import com.example.demo.service.StockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StockController {


	@Autowired
	private StockService stockService;

	private static Logger log = LoggerFactory.getLogger(StockController.class);

	// S01_チャート表示対象銘柄選択画面の表示
	@RequestMapping("/")
	public ModelAndView index(
		ModelAndView mav,
		@RequestParam(value = "stockCode", required=false) Integer stockCode
	) {
		log.info("/ called");

		Integer stockCodeValid = stockCode != null ? stockCode : Const.Stock.STOCK_DEFAULT_STOCKCODE;
		mav.addObject("stockCode", stockCodeValid);

		mav.setViewName("chart");
		return mav;
	}


	// S02_チャート表示用データ送信用API
	@ResponseBody
	@PostMapping("/getPriceList")
	public ChartInfo get_pricelist(StockSearchCriteria ssc){
		log.info("銘柄コード:{}",ssc.getStockCode());

		ChartInfo chartInfo = stockService.get_chart_info(ssc.getStockCode());

		return chartInfo;
	}


	// 銘柄一覧画面の表示
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView mav) throws Exception {
		
		List<Stock> stockList = stockService.get_stock_list();
		mav.addObject("stockList", stockList);

		mav.setViewName("list");
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

		stockService.add_stock(stockCode);
		mav.setViewName("redirect:list");
		return mav;
    }
}