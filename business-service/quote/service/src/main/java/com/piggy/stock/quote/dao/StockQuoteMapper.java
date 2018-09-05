package com.piggy.stock.quote.dao;

import java.util.List;
import java.util.Map;

import com.piggy.stock.quote.domain.StockQuote;

public interface StockQuoteMapper {
	int deleteByPrimaryKey(Long id);

	int insert(StockQuote record);

	int insertSelective(StockQuote record);

	StockQuote selectByPrimaryKey(Long id);

	List<StockQuote> select(Map<String, Object> params);

	int updateByPrimaryKeySelective(StockQuote record);

	int updateByPrimaryKey(StockQuote record);

	int save(StockQuote record);
}