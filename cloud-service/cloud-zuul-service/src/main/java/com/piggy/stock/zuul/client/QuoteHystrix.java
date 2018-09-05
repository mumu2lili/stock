package com.piggy.stock.zuul.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.piggy.stock.zuul.web.vo.StockQuoteVo;

@Service
public class QuoteHystrix {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getQuotePageFallback")
	public PageInfo<StockQuoteVo> getQuotePage(int pageNum, int pageSize) {
		ResponseEntity<PageInfo<StockQuoteVo>> res = this.restTemplate.exchange(
				"http://stock-quote-v1/quotes?pageNum={pageNum}&pageSize={pageSize}", HttpMethod.GET, null,
				new ParameterizedTypeReference<PageInfo<StockQuoteVo>>() {
				}, pageNum, pageSize);
		return res.getBody();
	}

	@SuppressWarnings("unused")
	private PageInfo<StockQuoteVo> getQuotePageFallback(int pageNum, int pageSize) {
		return null;
	}

}
