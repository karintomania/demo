package com.example.demo.constant;

public final class Const{

	private Const(){};

	public static final class General{
		public static final String BR = System.getProperty("line.separator");
	}

	public static final class Stock{
		public static String STOCK_SOURCE_URL = "https://kabuoji3.com/stock/{stockCode}/";
		public static String STOCK_TABLE_CLASS = "stock_table stock_data_table";
	}
}