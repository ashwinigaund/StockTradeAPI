package com.hackerrank.stocktrade.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.entity.TradeEntity;
import com.hackerrank.stocktrade.service.DataService;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {

	@Autowired
	private DataService dataService;

	@GetMapping("/{stockSymbol}/price")
	public ResponseEntity<?> findStocks(@PathVariable String stockSymbol,
			@RequestParam(name = "start") Timestamp startDate, @RequestParam(name = "end") Timestamp endDate) {
		List<TradeEntity> tradeList = dataService.findStocks(stockSymbol, startDate, endDate);
		if (!CollectionUtils.isEmpty(tradeList)) {
			return ResponseEntity.ok(tradeList);
		}
		return ResponseEntity.notFound().build();

	}
}
