package com.piggy.stock.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.piggy.stock.zuul.filter.InjectionAttackFilter;

@Configuration
public class WebConfig {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {
		FilterRegistrationBean<CharacterEncodingFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new CharacterEncodingFilter());
		filterRegBean.setName("characterEncodingFilter");
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setOrder(1);

		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean<InjectionAttackFilter> injectionAttackFilter() {
		FilterRegistrationBean<InjectionAttackFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new InjectionAttackFilter());
		filterRegBean.setName("injectionAttackFilter");
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setOrder(2);

		return filterRegBean;
	}

}