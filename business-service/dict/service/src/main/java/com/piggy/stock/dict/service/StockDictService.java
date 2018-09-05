package com.piggy.stock.dict.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.dict.domain.StockDict;

public interface StockDictService {

	List<StockDict> getStockDict(Map<String, Object> params);

	StockDict getStockDict(Long id);

	PageInfo<StockDict> getStockDict(int pageNum, int pageSize);

	StockDict insertStockDict(StockDict stockDict);

	void deleteStockDict(Long id);

}
