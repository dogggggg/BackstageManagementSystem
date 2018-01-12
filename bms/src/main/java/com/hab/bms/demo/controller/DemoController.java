package com.hab.bms.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hab.bms.demo.model.Demo;
import com.hab.bms.demo.service.DemoService;
import com.hab.bms.demo.service.impl.DemoServiceImpl;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
	private static Logger logger = LogManager.getLogger(DemoServiceImpl.class);
	@Autowired
	private DemoService demoService;

	@RequestMapping("showDemo")
	public String toIndex(HttpServletRequest request, Model model) {
		logger.debug("request{}: " + request);
		logger.debug("model{}: " + model);
		try {
			int demoId = Integer.parseInt(request.getParameter("id"));
			Demo demo = this.demoService.getDemoById(demoId);
			model.addAttribute("demo", demo);
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}
	}
}