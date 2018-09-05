package com.piggy.stock.quote.crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import com.piggy.stock.constant.ParseCsts;
import com.piggy.stock.core.constant.StockCsts;
import com.piggy.stock.core.domain.Stock;
import com.piggy.stock.core.util.DateUtils;
import com.piggy.stock.crawler.StockBatchCrawler;
import com.piggy.stock.quote.domain.StockQuote;

public class QuoteCrawler extends StockBatchCrawler {

	@Override
	public String buildUrl(Stock... stocks) {
		StringBuilder sb = new StringBuilder();
		for (Stock stock : stocks) {
			sb.append(stock.getExchange().toLowerCase()).append(stock.getCode()).append(",");
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}

		return "http://hq.sinajs.cn/rn=grp4n&list=" + sb.toString();
	}

	@Override
	protected <T> T parse(Document doc) {

		String s = doc.body().text();
		String[] quotes = s.split(";");
		List<StockQuote> list = new ArrayList<>(quotes.length);// 可优化
		for (int i = 0; i < quotes.length; i++) {
			String quote = quotes[i];
			quote = quote.substring(quote.indexOf("hq_str_") + "hq_str_".length());

			StockQuote stockQuote = new StockQuote();
			stockQuote.setExchange(quote.substring(0, 2).toUpperCase());
			stockQuote.setBoard(StockCsts.BOARD_MAIN);// TODO 暂时写死
			stockQuote.setCode(quote.substring(2, 8));
			quote = quote.substring(10, quote.length() - 1);
			if (StringUtils.isBlank(quote)) {
				continue;
			}
			String[] arr = quote.split(",");
			stockQuote.setOpening(Double.valueOf(arr[1]));
			// 2是昨收拾，3是最新
			stockQuote.setClosing(Double.valueOf(arr[3]));
			stockQuote.setLow(Double.valueOf(arr[5]));
			stockQuote.setHigh(Double.valueOf(arr[4]));

			String dateStr = arr[arr.length - 3] + " " + arr[arr.length - 2];
			Date date = null;
			if (StringUtils.isNotBlank(dateStr.replaceAll("-", ""))) {
				date = DateUtils.parseDate(dateStr, ParseCsts.DATE_FORMATS);
			}
			stockQuote.setTradeTime(date);
			list.add(stockQuote);

		}

		@SuppressWarnings("unchecked")
		T t = (T) list;
		return t;
	}

}
