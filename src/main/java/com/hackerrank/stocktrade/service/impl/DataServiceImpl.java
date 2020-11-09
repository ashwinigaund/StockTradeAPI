package com.hackerrank.stocktrade.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.entity.TradeEntity;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepo;
import com.hackerrank.stocktrade.service.DataService;

@Service
public class DataServiceImpl implements DataService{
	@Autowired
	private TradeRepo tradeRepo;

	@Override
	public void deleteAllTrade() {
		tradeRepo.deleteAll();
	}

	@Transactional
	@Override
	public boolean addTrade(Trade trade) {
		TradeEntity tradeEntity = new TradeEntity();
		BeanUtils.copyProperties(trade, tradeEntity);
		tradeEntity.setUserId(trade.getUser().getId());
		if (!tradeRepo.existsById(tradeEntity.getId())) {
			tradeRepo.save(tradeEntity);
			return true;
		}
		return false;
	}
	@Override
	public boolean existById(Long id) {
		return tradeRepo.existsById(id);
	}
	@Override
	public Trade findById(Long id) {
		Trade trade = new Trade();
		Optional<TradeEntity> tradeEntity = tradeRepo.findById(id);
		if (tradeEntity.isPresent()) {
			tradeEntity.get();
			BeanUtils.copyProperties(tradeEntity, trade);
		}
		return trade;
	}
	@Override
	public List<TradeEntity> findAll() {
		return tradeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	@Override
	public List<TradeEntity> findAllByUserId(Long userId) {
		return tradeRepo.findAllByUserId(userId);
	}
	@Override
	public List<TradeEntity> findTradeBySymbolAndType(String stockSymbol, String tradeType, Timestamp startDate,
			Timestamp endDate) {
		return tradeRepo.findTradeBySymbolAndType(stockSymbol, tradeType, startDate, endDate);
	}
	@Override
	public List<TradeEntity> findStocks(String stockSymbol, Timestamp startDate, Timestamp endDate) {
		return tradeRepo.findStocks(stockSymbol, startDate, endDate);
	}
}
