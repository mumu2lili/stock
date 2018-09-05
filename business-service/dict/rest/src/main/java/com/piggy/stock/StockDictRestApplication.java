package com.piggy.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.piggy.stock.dict.dao")
public class StockDictRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockDictRestApplication.class, args);
	}
}