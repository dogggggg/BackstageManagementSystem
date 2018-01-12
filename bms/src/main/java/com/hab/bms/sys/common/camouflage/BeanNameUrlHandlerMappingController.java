package com.hab.bms.sys.common.camouflage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hab.bms.sys.utils.StrUtil;

public class BeanNameUrlHandlerMappingController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String requestUri = StrUtil.substringBefore(request.getRequestURI(), ";");

		ModelAndView modelAndView = new ModelAndView(StrUtil.substringAfterLast(requestUri, "/"));
		modelAndView.addObject("uri", requestUri);

		return modelAndView;
	}

}