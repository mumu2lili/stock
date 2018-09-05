package com.piggy.stock.zuul.client;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.zuul.web.vo.StockDictVo;

@Component
public class DictFeignFallback implements DictFeign {

	@Override
	public PageInfo<StockDictVo> getDictPage(int pageNum, int pageSize) {
		return null;
	}

}
