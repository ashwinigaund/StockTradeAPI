package com.hackerrank.stocktrade.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.service.DataService;

@RestController
@RequestMapping(value = "/erase", produces = { APPLICATION_JSON_VALUE })
public class ResourcesController {

	@Autowired
	private DataService dataService;

	@DeleteMapping()
	public ResponseEntity<?> deleteAllTrade() {
		dataService.deleteAllTrade();
		return ResponseEntity.ok().build();
	}

}
