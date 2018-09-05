package com.piggy.stock.dict.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.piggy.stock.core.util.stock.StockIdUtils;
import com.piggy.stock.dict.crawler.DictCrawler;
import com.piggy.stock.dict.domain.StockDict;
import com.piggy.stock.dict.service.DictCrawlService;
import com.piggy.stock.dict.service.StockDictService;

@Service
public class DictCrawlServiceImpl implements DictCrawlService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockDictService stockDictService;

	@Scheduled(cron = "0/5 * * * * ?")
	@Override
	public void crawlDict() {
		log.info("crawl StockDict begin");
		try {
			crawlDictInner();
		} catch (Exception e) {
			log.error("crawl StockDict error", e);
		}

		log.info("crawl StockDict end");

	}

	private void crawlDictInner() {
		DictCrawler crawler = new DictCrawler();
		List<StockDict> list = crawler.crawl();

		for (int i = 0; i < list.size(); i++) {
			StockDict dict = list.get(i);
			Long id = StockIdUtils.calStockId(dict);
			if (stockDictService.getStockDict(id) == null) {
				dict.setId(id);
				stockDictService.insertStockDict(dict);
			}
		}

	}

}
