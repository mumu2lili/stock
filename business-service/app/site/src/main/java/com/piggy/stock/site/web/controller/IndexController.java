package com.piggy.stock.site.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class IndexController {

	@Value("${gateway.addr}")
	private String gatewayAddr;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String createStockDict(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		model.put("apiPath", gatewayAddr);
		return "index";
	}

}
