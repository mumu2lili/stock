package com.piggy.stock.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class IndexController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String createStockDict(HttpServletRequest req, HttpServletResponse res) {

		return "index";
	}

}
