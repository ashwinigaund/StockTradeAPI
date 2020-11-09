package com.hackerrank.stocktrade.service;

import java.sql.Timestamp;
import java.util.List;

import com.hackerrank.stocktrade.entity.TradeEntity;
import com.hackerrank.stocktrade.model.Trade;


public interface DataService {

	public void deleteAllTrade();

	public boolean addTrade(Trade trade);

	public boolean existById(Long id);

	public Trade findById(Long id);

	public List<TradeEntity> findAll();

	public List<TradeEntity> findAllByUserId(Long userId);

	public List<TradeEntity> findTradeBySymbolAndType(String stockSymbol, String tradeType, Timestamp startDate,
			Timestamp endDate);

	public List<TradeEntity> findStocks(String stockSymbol, Timestamp startDate, Timestamp endDate);
}
