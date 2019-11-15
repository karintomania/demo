package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.constant.Const;
import com.example.demo.dto.ChartInfo;
import com.example.demo.entity.Price;
import com.example.demo.entity.Stock;
import com.example.demo.repository.PriceRepository;
import com.example.demo.repository.StockRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private StockRepository stockRepository;

	public ChartInfo get_chart_info(int stockCode) {
		ChartInfo ci = new ChartInfo();
		
		List<Price> priceList = priceRepository.findByStockCodeOrderByDateAsc(stockCode);
		Stock stock = stockRepository.findByStockCode(stockCode);

		ci.setPriceList(priceList);
		ci.setStock(stock);
		return ci;
	}

	public void add_stock(int stockCode) throws Exception {

		// 株価取得サイトにGETでrequest飛ばす {baseUrl}/{銘柄コード}
		String url = Const.Stock.STOCK_SOURCE_URL.replace("{stockCode}", Integer.toString(stockCode));
		Document doc = Jsoup.connect(url).get();

		add_stock_priceTable(doc,stockCode);
		add_stock_stockTable(doc,stockCode);
	}

	// stockテーブルに銘柄情報を格納
	private void add_stock_stockTable(Document doc,int stockCode){

		Stock stock = new Stock();
		String stockText;
		String stockName;

		// Class名でタグ絞り込み、子要素のspanから株価銘柄名を取得
		Element nameElem = doc.select("."+Const.Stock.STOCK_NAME_CLASS).first();
		Element spanElem = nameElem.select("span").first();

		stockText = spanElem.text();
		System.out.println(stockText);

		// 「nnnn 銘柄名」のパターンから銘柄コードを削除
		stockName = stockText.replaceAll("^\\d{4} ", "");

		stock.setName(stockName);
		stock.setStockCode(stockCode);

		stockRepository.save(stock);
	}

	// priceテーブルに株価を格納
	private void add_stock_priceTable(Document doc,int stockCode) throws Exception {

		ArrayList<Price> liPrice = new ArrayList<Price>();
		SimpleDateFormat sdf     = new SimpleDateFormat("yyyy-MM-dd");

		// 株価が記載されている<table>タグのClass名でテーブルタグ要素を特定
		Elements tableElems = doc.getElementsByClass(Const.Stock.STOCK_TABLE_CLASS);

		// テーブルの行(trタグ)を配列で取得
		for(Element tableElem : tableElems){
			Elements trElems = tableElem.getElementsByTag("tr");
		// テーブルの列要素(tdタグ)を配列で取得
		for(Element trElem : trElems){
			Elements tdElems = trElem.getElementsByTag("td");
			Price price = new Price();

			price.setStockCode(stockCode);

			// td内のtextが欲しい値になっている
			// 日付、始値、高値、安値、終値、出来高の順番
			if(tdElems.size() == 7){ // ヘッダを対象外とする処理（ヘッダ列にtdタグはなく、thタグのためtdタグの数で区別できる）
				for(Element tdElem : tdElems){
					String content = tdElem.text();
					switch(tdElems.indexOf(tdElem)){
						case 0: // 日付
						price.setDate(sdf.parse(content)); break;
						case 1: // 始値
						price.setOpenPrice(Integer.parseInt(content)); break;
						case 2: // 高値
						price.setHighPrice(Integer.parseInt(content)); break;
						case 3: // 安値 
						price.setLowPrice(Integer.parseInt(content)); break;
						case 4: // 終値 
						price.setClosePrice(Integer.parseInt(content)); break;
						case 5: // 出来高
						price.setVolume(Integer.parseInt(content)); break;
					}
				}
				if(liPrice.size() > 30) break;	// テスト用ロジック　本番はこれを日付での判定とする			

				liPrice.add(price);
			}

				if(liPrice.size() > 30) break;// テスト用ロジック
		}
				if(liPrice.size() > 30) break;// テスト用ロジック
		}


		// DBにinsert
		for(Price pr : liPrice){
			System.out.println(pr.toString());
			priceRepository.save(pr);
		}

	}

}