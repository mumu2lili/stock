package com.piggy.stock.quote.web.rest;

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
import com.piggy.stock.quote.domain.StockQuote;
import com.piggy.stock.quote.service.StockQuoteService;

@RestController
@RequestMapping("/quotes")
public class StockQuoteRest {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockQuoteService stockQuoteService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public StockQuote createStockQuote(@RequestBody StockQuote stockQuote) {
		log.info("create stock Quote {} start ", stockQuote.getId());
		long id = StockIdUtils.calStockId(stockQuote);
		stockQuote.setId(id);
		stockQuote = stockQuoteService.insertStockQuote(stockQuote);
		log.info("create stock Quote {} end ", stockQuote.getId());
		return stockQuote;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public StockQuote getStockQuote(@PathVariable("id") Long id) {
		log.info("get stock Quote {} start ", id);
		StockQuote stockQuote = stockQuoteService.getStockQuote(id);
		log.info("get stock Quote {} end ", id);
		return stockQuote;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public PageInfo<StockQuote> getStockQuotePage(
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

		return stockQuoteService.getStockQuote(pageNum, pageSize);

	}

}