package com.piggy.stock.zuul.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.piggy.stock.zuul.web.vo.StockDictVo;

@Service
public class DictHystrix {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getDictPageFallback")
	public PageInfo<StockDictVo> getDictPage(int pageNum, int pageSize) {
		ResponseEntity<PageInfo<StockDictVo>> res = this.restTemplate.exchange(
				"http://stock-dict-v1/dicts?pageNum={pageNum}&pageSize={pageSize}", HttpMethod.GET, null,
				new ParameterizedTypeReference<PageInfo<StockDictVo>>() {
				}, pageNum, pageSize);
		return res.getBody();
	}

	@SuppressWarnings("unused")
	private PageInfo<StockDictVo> getDictPageFallback(int pageNum, int pageSize) {
		return null;
	}

}
