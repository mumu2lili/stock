package com.piggy.stock.quote.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.piggy.stock.quote.domain.StockQuote;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockQuoteServiceTest {
	@Autowired
	private StockQuoteService stockQuoteService;

	@Test
	public void test() {
		StockQuote quote = stockQuoteService.getStockQuote(1L);
		System.out.println(quote);
	}

}
