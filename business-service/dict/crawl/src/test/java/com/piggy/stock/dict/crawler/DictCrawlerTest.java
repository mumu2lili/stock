package com.piggy.stock.dict.crawler;

import org.junit.Test;

import com.piggy.stock.crawler.StockCrawler;

public class DictCrawlerTest {

	@Test
	public void testCrawl() {
		StockCrawler crawler = new DictCrawler();

		crawler.crawl();
	}

}
