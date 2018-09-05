package com.piggy.stock.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.piggy.stock.core.domain.Stock;

public abstract class StockBatchCrawler {
	private static final Stock[] EMPTY = new Stock[] {};

	public final <T> T crawl() {

		return this.crawl(EMPTY);
	}

	/**
	 * 暂时简单处理
	 *
	 * @param stock
	 * @return
	 */
	public final <T> T crawl(Stock... stocks) {

		String url = this.buildUrl(stocks);
		Document doc = null;
		try {
			doc = this.getDoc(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return parse(doc);
	}

	public abstract String buildUrl(Stock... stocks);

	protected Document getDoc(String url) throws IOException {
		return Jsoup.connect(url).ignoreContentType(true).get();
	}

	protected abstract <T> T parse(Document doc);

}
