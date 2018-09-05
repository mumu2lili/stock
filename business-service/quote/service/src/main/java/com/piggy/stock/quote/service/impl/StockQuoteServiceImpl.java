package com.piggy.stock.quote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.piggy.stock.quote.dao.StockQuoteMapper;
import com.piggy.stock.quote.domain.StockQuote;
import com.piggy.stock.quote.service.StockQuoteService;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {

	@Autowired
	private StockQuoteMapper stockQuoteDao;

	@Override
	public List<StockQuote> getStockQuote(Map<String, Object> params) {
		return stockQuoteDao.select(params);
	}

	@Override
	public StockQuote getStockQuote(Long id) {
		return this.stockQuoteDao.selectByPrimaryKey(id);
	}

	@Override
	public StockQuote insertStockQuote(StockQuote stockQuote) {

		this.stockQuoteDao.insert(stockQuote);
		return stockQuote;
	}

	@Override
	public StockQuote saveStockQuote(StockQuote stockQuote) {

		this.stockQuoteDao.save(stockQuote);
		return stockQuote;
	}

	@Override
	public void deleteStockQuote(Long id) {
		this.stockQuoteDao.deleteByPrimaryKey(id);

	}

	@Override
	public PageInfo<StockQuote> getStockQuote(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StockQuote> list = this.stockQuoteDao.select(null);
		PageInfo<StockQuote> pageInfo = new PageInfo<StockQuote>(list);
		return pageInfo;

	}

}
