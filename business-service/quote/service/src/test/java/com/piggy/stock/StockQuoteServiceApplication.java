package com.piggy.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.piggy.stock.quote.dao")
public class StockQuoteServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockQuoteServiceApplication.class, args);
	}
}