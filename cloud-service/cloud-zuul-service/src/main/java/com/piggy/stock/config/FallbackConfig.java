package com.piggy.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.piggy.stock.zuul.fallback.GlobalFallbackProvider;
import com.piggy.stock.zuul.fallback.StockDictFallbackProvider;

@Configuration
public class FallbackConfig {

	@Bean
	public GlobalFallbackProvider globalFallbackProvider() {
		return new GlobalFallbackProvider();
	}

	@Bean
	public StockDictFallbackProvider stockDictFallbackProvider() {
		return new StockDictFallbackProvider();
	}

}