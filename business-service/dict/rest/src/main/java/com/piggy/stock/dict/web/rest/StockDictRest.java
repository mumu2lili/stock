package com.piggy.stock.dict.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.core.util.stock.StockIdUtils;
import com.piggy.stock.dict.domain.StockDict;
import com.piggy.stock.dict.service.StockDictService;

@RestController
@RequestMapping("/dicts")
public class StockDictRest {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockDictService stockDictService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public StockDict createStockDict(@RequestBody StockDict stockDict) {
		log.info("create stock dict {} start ", stockDict.getName());
		long id = StockIdUtils.calStockId(stockDict);
		stockDict.setId(id);
		stockDict = stockDictService.insertStockDict(stockDict);
		log.info("create stock dict {} end ", stockDict.getName());
		return stockDict;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public StockDict getStockDict(@PathVariable("id") Long id) {
		log.info("get stock dict {} start ", id);
		StockDict stockDict = stockDictService.getStockDict(id);
		log.info("get stock dict {} end ", id);
		return stockDict;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public PageInfo<StockDict> getStockDictPage(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		return stockDictService.getStockDict(pageNum, pageSize);

	}

}