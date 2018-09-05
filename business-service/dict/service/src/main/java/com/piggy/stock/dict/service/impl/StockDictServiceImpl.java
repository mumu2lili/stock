package com.piggy.stock.dict.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.piggy.stock.dict.dao.StockDictMapper;
import com.piggy.stock.dict.domain.StockDict;
import com.piggy.stock.dict.service.StockDictService;

@Service
public class StockDictServiceImpl implements StockDictService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockDictMapper stockDictDao;

	@Override
	public List<StockDict> getStockDict(Map<String, Object> params) {
		return stockDictDao.select(params);
	}

	@Override
	public StockDict getStockDict(Long id) {
		return this.stockDictDao.selectByPrimaryKey(id);
	}

	@Override
	public StockDict insertStockDict(StockDict stockDict) {

		this.stockDictDao.insert(stockDict);
		log.info("insert StockDict exchange {} board {} code {}", stockDict.getExchange(), stockDict.getBoard(),
				stockDict.getCode());
		return stockDict;
	}

	@Override
	public void deleteStockDict(Long id) {
		this.stockDictDao.deleteByPrimaryKey(id);

	}

	@Override
	public PageInfo<StockDict> getStockDict(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StockDict> list = this.stockDictDao.select(null);
		PageInfo<StockDict> pageInfo = new PageInfo<StockDict>(list);
		return pageInfo;

	}

}
