package com.piggy.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.piggy.stock.quote.dao")
public class StockQuoteCrawlApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteCrawlApplication.class, args);
	}
}