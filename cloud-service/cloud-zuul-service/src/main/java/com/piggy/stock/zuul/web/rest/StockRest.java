package com.piggy.stock.zuul.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.zuul.client.DictFeign;
import com.piggy.stock.zuul.client.DictHystrix;
import com.piggy.stock.zuul.client.QuoteFeign;
import com.piggy.stock.zuul.client.QuoteHystrix;
import com.piggy.stock.zuul.web.vo.StockDictVo;
import com.piggy.stock.zuul.web.vo.StockQuoteVo;

@RestController
@RequestMapping("/stocks")
public class StockRest {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DictHystrix dictHystrix;
	@Autowired
	private QuoteHystrix quoteHystrix;

	@Autowired
	private DictFeign dictFeign;
	@Autowired
	private QuoteFeign quoteFeign;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> getStock(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

		log.info("get stock pageNum {} pageSize {} start ", pageNum, pageSize);

		Map<String, Object> map = new HashMap<>();
		PageInfo<StockDictVo> dictPage = dictHystrix.getDictPage(pageNum, pageSize);
		map.put("dicts", dictPage);
		PageInfo<StockQuoteVo> quotePage = quoteHystrix.getQuotePage(pageNum, pageSize);
		map.put("quotes", quotePage);

		log.info("get stock pageNum {} pageSize {} end ", pageNum, pageSize);
		return map;

	}

	@RequestMapping(value = "/feign", method = RequestMethod.GET)
	public Map<String, Object> getStock2(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

		log.info("get stock pageNum {} pageSize {} start ", pageNum, pageSize);

		Map<String, Object> map = new HashMap<>();
		PageInfo<StockDictVo> dictPage = dictFeign.getDictPage(pageNum, pageSize);
		map.put("dicts", dictPage);
		PageInfo<StockQuoteVo> quotePage = quoteFeign.getQuotePage(pageNum, pageSize);
		map.put("quotes", quotePage);

		log.info("get stock pageNum {} pageSize {} end ", pageNum, pageSize);
		return map;

	}

}