package com.hab.bms.sys.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultPageController {

	@RequestMapping("/page/{page}")
	public String pageIndex(@PathVariable("page") String page, HttpServletRequest request) {
		return "" + page;
	}

	@RequestMapping("/page/{module}/{page}")
	public String pageIndex2(@PathVariable("module") String module, @PathVariable("page") String page,
			HttpServletRequest request) {
		return "" + module + "/" + page;
	}

	@RequestMapping("/page/{module}/{page}/{view}")
	public String pageIndex3(@PathVariable("module") String module, @PathVariable("page") String page,
			@PathVariable("view") String view, HttpServletRequest request) {
		return "" + module + "/" + page + "/" + view;
	}
}
