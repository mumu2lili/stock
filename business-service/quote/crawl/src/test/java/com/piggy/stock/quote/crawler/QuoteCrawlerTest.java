package com.piggy.stock.quote.crawler;

import org.junit.Test;

import com.piggy.stock.core.constant.StockCsts;
import com.piggy.stock.core.domain.Stock;
import com.piggy.stock.crawler.StockBatchCrawler;

public class QuoteCrawlerTest {

	@Test
	public void testCrawl() {
		Stock stock = new Stock();
		stock.setExchange(StockCsts.EXCHANGE_SHANGHAI);
		stock.setBoard(StockCsts.BOARD_MAIN);
		stock.setCode("603011");

		Stock stock2 = new Stock();
		stock2.setExchange(StockCsts.EXCHANGE_SHANGHAI);
		stock2.setBoard(StockCsts.BOARD_MAIN);
		stock2.setCode("600555");

		StockBatchCrawler crawler = new QuoteCrawler();
		crawler.crawl(new Stock[] { stock, stock2 });

	}

}
