package com.piggy.stock.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.piggy.stock.core.domain.Stock;

public abstract class StockCrawler {

	public final <T> T crawl() {

		return this.crawl(null);
	}

	/**
	 * 暂时简单处理
	 *
	 * @param stock
	 * @return
	 */
	public final <T> T crawl(Stock stock) {

		String url = this.buildUrl(stock);
		Document doc = null;
		try {
			doc = this.getDoc(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return parse(doc);
	}

	public abstract String buildUrl(Stock stock);

	protected Document getDoc(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

	protected abstract <T> T parse(Document doc);

}
