package com.piggy.stock.quote.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.quote.domain.StockQuote;

public interface StockQuoteService {

	List<StockQuote> getStockQuote(Map<String, Object> params);

	StockQuote getStockQuote(Long id);

	PageInfo<StockQuote> getStockQuote(int pageNum, int pageSize);

	StockQuote insertStockQuote(StockQuote stockQuote);

	StockQuote saveStockQuote(StockQuote stockQuote);

	void deleteStockQuote(Long id);

}
