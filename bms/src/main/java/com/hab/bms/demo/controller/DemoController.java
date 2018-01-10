package com.hab.bms.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hab.bms.demo.model.Demo;
import com.hab.bms.demo.service.DemoService;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
	@Autowired
	private DemoService demoService;

	@RequestMapping(value = "showDemo")
	public String toIndex(HttpServletRequest request, Model model) {
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