package com.piggy.stock.zuul.client;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.zuul.web.vo.StockQuoteVo;

@Component
public class QuoteFeignFallback implements QuoteFeign {

	@Override
	public PageInfo<StockQuoteVo> getQuotePage(int pageNum, int pageSize) {
		return null;
	}

}
