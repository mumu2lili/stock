package com.piggy.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.piggy.stock.dict.dao")
public class StockDictServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockDictServiceApplication.class, args);
	}
}