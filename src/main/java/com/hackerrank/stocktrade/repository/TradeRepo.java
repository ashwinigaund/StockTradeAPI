package com.hackerrank.stocktrade.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackerrank.stocktrade.entity.TradeEntity;

@Repository
public interface TradeRepo extends JpaRepository<TradeEntity, Long> {

	@Query(value = "select a from Trade a where a.userId = ?0", nativeQuery = true)
	List<TradeEntity> findAllByUserId(Long userId);

	@Query(value = "select a from Trade a where a.symbol = ?0 AND a.type = ?1 AND (a.timestamp between ?2 and ?3)", nativeQuery = true)
	List<TradeEntity> findTradeBySymbolAndType(String stockSymbol, String tradeType, Timestamp startDate,
			Timestamp endDate);

	@Query(value = "select a.symbol, a.price from Trade a where a.symbol = ?0 AND (a.timestamp between ?1 and ?2) order by a.symbol, a.price", nativeQuery = true)
	List<TradeEntity> findStocks(String stockSymbol, Timestamp startDate, Timestamp endDate);

}
