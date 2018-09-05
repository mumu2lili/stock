package com.piggy.stock.zuul.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.zuul.web.vo.StockQuoteVo;

@FeignClient(name = "stock-quote-v1", fallback = QuoteFeignFallback.class)
public interface QuoteFeign {

	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	PageInfo<StockQuoteVo> getQuotePage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

}
