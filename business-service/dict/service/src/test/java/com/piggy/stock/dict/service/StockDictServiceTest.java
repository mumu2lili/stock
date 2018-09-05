package com.piggy.stock.dict.service;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.piggy.stock.dict.domain.StockDict;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockDictServiceTest {
	@Autowired
	private StockDictService stockDictService;

	@Test
	public void test() {
		Long id = 1L;
		StockDict dict = stockDictService.getStockDict(id);
		boolean r = dict == null || dict.getId().equals(id);
		Assert.assertTrue(r);
	}

}
