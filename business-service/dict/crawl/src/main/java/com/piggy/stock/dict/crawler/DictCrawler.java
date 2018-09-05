package com.piggy.stock.dict.crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.piggy.stock.constant.ParseCsts;
import com.piggy.stock.core.constant.StockCsts;
import com.piggy.stock.core.domain.Stock;
import com.piggy.stock.core.util.DateUtils;
import com.piggy.stock.crawler.StockCrawler;
import com.piggy.stock.dict.domain.StockDict;

public class DictCrawler extends StockCrawler {

	@Override
	public String buildUrl(Stock stock) {

		return "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cb=jQuery112409155656220070201_1530260384729&type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&js=(%7Bdata%3A%5B(x)%5D%2CrecordsTotal%3A(tot)%2CrecordsFiltered%3A(tot)%7D)&cmd=C.2&sty=FCOIATC&st=(ChangePercent)&sr=-1&p=1&ps=5000&_=1530260384878";
	}

	@Override
	protected <T> T parse(Document doc) {

		String s = doc.body().text();
		s = s.substring(s.indexOf("{data:"), s.length() - 1);
		System.out.println(s);

		JSONObject jsonObj = JSON.parseObject(s);
		JSONArray jsonArr = jsonObj.getJSONArray("data");
		List<StockDict> list = new ArrayList<>(jsonArr.size());// 可优化
		for (int i = 0; i < jsonArr.size(); i++) {
			String str = jsonArr.getString(i);
			String[] arr = str.split(",");

			StockDict dict = new StockDict();
			dict.setExchange(StockCsts.EXCHANGE_SHANGHAI);
			dict.setBoard(StockCsts.BOARD_MAIN);
			dict.setCode(arr[1]);
			dict.setName(arr[2]);
			String dateStr = arr[arr.length - 3];
			Date date = null;
			if (StringUtils.isNotBlank(dateStr.replaceAll("-", ""))) {
				date = DateUtils.parseDate(dateStr, ParseCsts.DATE_FORMATS);
			}
			dict.setListingDate(date);
			dict.setStatus(StockCsts.STATUS_NORMAL);
			list.add(dict);

		}

		@SuppressWarnings("unchecked")
		T t = (T) list;
		return t;
	}

}
