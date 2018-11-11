package com.piggy.stock.dict.rest;

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
import com.piggy.stock.dict.domain.StockDict;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockDictRestTest {

	@Autowired
	private RestTemplate restTemplate;

	// @Test
	public void testPost() {
		StockDict stock = new StockDict();
		stock.setExchange(StockCsts.EXCHANGE_SHENZHEN);
		stock.setBoard(StockCsts.BOARD_MAIN);
		stock.setCode("000001");
		stock.setStatus(StockCsts.STATUS_NORMAL);
		stock.setName("平安");

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);

		HttpEntity<Object> entity = new HttpEntity<Object>(stock, headers);
		ResponseEntity<StockDict> res = restTemplate.postForEntity("http://stock-dict-v1/dicts", entity,
				StockDict.class);
		stock = res.getBody();
	}

	@Test
	public void testGetStockDict() {

		Long id = 1010000000000600000L;
		ResponseEntity<StockDict> res = restTemplate.getForEntity("http://stock-dict-v1/dicts/{id}", StockDict.class,
				id);
		StockDict stock = res.getBody();
		Assert.assertEquals(id, stock.getId());
	}

	@Test
	public void testGetStockDictPage() {

		int pageNum = 2;
		int pageSize = 10;
		ResponseEntity<PageInfo<StockDict>> res = restTemplate.exchange(
				"http://stock-dict-v1/dicts?pageNum={pageNum}&pageSize={pageSize}", HttpMethod.GET, null,
				new ParameterizedTypeReference<PageInfo<StockDict>>() {
				}, pageNum, pageSize);
		PageInfo<StockDict> stock = res.getBody();
		Assert.assertEquals(pageNum, stock.getPageNum());
	}

}
