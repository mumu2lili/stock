package com.piggy.stock.zuul.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.piggy.stock.zuul.web.vo.StockDictVo;

@FeignClient(name = "stock-dict-v1", fallback = DictFeignFallback.class)
public interface DictFeign {

	@RequestMapping(value = "/dicts", method = RequestMethod.GET)
	PageInfo<StockDictVo> getDictPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

}
