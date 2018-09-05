package com.piggy.stock.quote.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.core.domain.Stock;
import com.piggy.stock.core.util.stock.StockIdUtils;
import com.piggy.stock.quote.crawler.QuoteCrawler;
import com.piggy.stock.quote.domain.StockQuote;
import com.piggy.stock.quote.service.QuoteCrawlService;
import com.piggy.stock.quote.service.StockQuoteService;

@Service
public class QuoteCrawlServiceImpl implements QuoteCrawlService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockQuoteService stockQuoteService;
	@Autowired
	private RestTemplate restTemplate;

	@Scheduled(cron = "0/5 * * * * ?")
	@Override
	public void crawlQuote() {
		log.info("crawl StockQuote begin");
		try {
			crawlQuoteInner();
		} catch (Exception e) {
			log.error("crawl StockQuote error", e);
		}

		log.info("crawl StockQuote end");

	}

	private void crawlQuoteInner() {
		for (int pageNum = 1;; pageNum++) {
			final int pageSize = 100;

			ResponseEntity<PageInfo<Stock>> res = this.restTemplate.exchange(
					"http://stock-dict-v1/dicts?pageNum={pageNum}&pageSize={pageSize}", HttpMethod.GET, null,
					new ParameterizedTypeReference<PageInfo<Stock>>() {
					}, pageNum, pageSize);
			PageInfo<Stock> pageInfo = res.getBody();

			if (pageInfo.getSize() == 0) {
				break;
			}

			QuoteCrawler crawler = new QuoteCrawler();
			List<StockQuote> list = crawler.crawl(pageInfo.getList().toArray(new Stock[pageInfo.getSize()]));

			// TODO 批量操作
			for (int i = 0; i < list.size(); i++) {
				StockQuote quote = list.get(i);
				Long id = StockIdUtils.calStockId(quote);
				quote.setId(id);
				stockQuoteService.saveStockQuote(quote);

			}

			if (pageInfo.getSize() < pageSize) {
				break;
			}
		}

	}

}
