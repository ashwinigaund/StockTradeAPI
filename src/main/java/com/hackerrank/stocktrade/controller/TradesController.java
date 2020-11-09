package com.hackerrank.stocktrade.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.entity.TradeEntity;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.DataService;

@RestController
@RequestMapping(value = "/trades", produces = { APPLICATION_JSON_VALUE })
public class TradesController {

	@Autowired
	private DataService dataService;

	@PostMapping()
	public ResponseEntity<?> addTrade(@RequestBody Trade trade) {
		if (dataService.addTrade(trade)) {
			return ResponseEntity.status(201).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findTrade(@PathVariable Long id) {
		if (dataService.existById(id)) {
			return ResponseEntity.ok(dataService.findById(id));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping()
	public ResponseEntity<?> findAllTrade() {
		return ResponseEntity.ok(dataService.findAll());
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> findTradeByUserId(@PathVariable Long userId) {
		List<TradeEntity> tradeList = dataService.findAllByUserId(userId);
		if (!CollectionUtils.isEmpty(tradeList)) {
			return ResponseEntity.ok(tradeList);
		}
		return ResponseEntity.notFound().build();

	}

	@GetMapping("/stocks/{stockSymbol}")
	public ResponseEntity<?> findTradeBySymbolAndType(@PathVariable String stockSymbol,
			@RequestParam(name = "type") String tradeType, @RequestParam(name = "start") Timestamp startDate,
			@RequestParam(name = "end") Timestamp endDate) {
		List<TradeEntity> tradeList = dataService.findTradeBySymbolAndType(stockSymbol, tradeType, startDate, endDate);
		if (!CollectionUtils.isEmpty(tradeList)) {
			return ResponseEntity.ok(tradeList);
		}
		return ResponseEntity.notFound().build();

	}

}
