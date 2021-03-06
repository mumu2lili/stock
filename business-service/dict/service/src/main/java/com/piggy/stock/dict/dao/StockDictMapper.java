package com.piggy.stock.dict.dao;

import java.util.List;
import java.util.Map;

import com.piggy.stock.dict.domain.StockDict;
import com.piggy.stock.dict.mybatis.TenantDataPerm;

public interface StockDictMapper {
	int deleteByPrimaryKey(Long id);

	int insert(StockDict record);

	int insertSelective(StockDict record);

	@TenantDataPerm
	StockDict selectByPrimaryKey(Long id);

	List<StockDict> select(Map<String, Object> params);

	int updateByPrimaryKeySelective(StockDict record);

	int updateByPrimaryKey(StockDict record);
}