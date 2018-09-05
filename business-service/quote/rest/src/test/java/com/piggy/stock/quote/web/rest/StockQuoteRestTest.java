package com.piggy.stock.quote.web.rest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.core.constant.StockCsts;
import com.piggy.stock.quote.domain.StockQuote;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockQuoteRestTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testPost() {
		StockQuote stock = new StockQuote();
		stock.setExchange(StockCsts.EXCHANGE_SHENZHEN);
		stock.setBoard(StockCsts.BOARD_MAIN);
		stock.setCode("000001");
		stock.setClosing(10.1);

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);

		HttpEntity<Object> entity = new HttpEntity<Object>(stock, headers);
		ResponseEntity<StockQuote> res = restTemplate.postForEntity("http://stock-quote/quotes", entity,
				StockQuote.class);
		stock = res.getBody();
	}

	@Test
	public void testGetStockQuote() {

		Long id = 1010000000000600000L;
		ResponseEntity<StockQuote> res = restTemplate.getForEntity("http://stock-quote/quotes/{id}", StockQuote.class,
				id);
		StockQuote stock = res.getBody();
		Assert.assertEquals(id, stock.getId());
	}

	@Test
	public void testGetStockQuotePage() {

		int pageNum = 2;
		int pageSize = 10;
		ResponseEntity<PageInfo<StockQuote>> res = restTemplate.exchange(
				"http://stock-quote/quotes?pageNum={pageNum}&pageSize={pageSize}", HttpMethod.GET, null,
				new ParameterizedTypeReference<PageInfo<StockQuote>>() {
				}, pageNum, pageSize);
		PageInfo<StockQuote> page = res.getBody();
		Assert.assertEquals(pageNum, page.getPageNum());
	}

}
