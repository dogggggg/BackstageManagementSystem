package com.hab.bms.sys.shiro.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import com.hab.bms.sys.shiro.constants.WebConstants;

public class PassThruAuthenticationWithGotoFilter extends PassThruAuthenticationFilter {

	private static final Logger logger = LogManager.getLogger(PassThruAuthenticationWithGotoFilter.class);

	/** 个人用户登陆页面 */
	private String defaultLoginUrl;

	private final static String GO_TO_PARAM_PREFIX = WebConstants.GO_TO + "=";

	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		String gotoUrl = getGotoUrl(request);
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		if (savedRequest == null) {
			logger.info("savedRequest is null");
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			logger.info("httpRequest: requestURI={}, QueryString={}, method={}", httpRequest.getRequestURI(),
					httpRequest.getQueryString(), httpRequest.getMethod());
			httpRequest.getPathTranslated();
		} else {
			logger.info("savedRequest: requestUrl={}, requestURI={}, QueryString={}, method={}",
					savedRequest.getRequestUrl(), savedRequest.getRequestURI(), savedRequest.getQueryString(),
					savedRequest.getMethod());
		}
		WebUtils.issueRedirect(request, response, getRedirectUrl(request, gotoUrl));
	}

	/**
	 * 得到用户原始访问路径，若是POST方式提交，则将参数组装到URL中
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String getGotoUrl(ServletRequest request) throws UnsupportedEncodingException {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		String queryString = httpRequest.getQueryString();
		StringBuffer basePath = httpRequest.getRequestURL();
		if (AccessControlFilter.POST_METHOD.equalsIgnoreCase(httpRequest.getMethod())) {
			Enumeration<String> parameterNames = httpRequest.getParameterNames();
			if (parameterNames.hasMoreElements()) {
				String firstName = parameterNames.nextElement();
				basePath.append("?").append(firstName).append("=").append(httpRequest.getParameter(firstName));
			}
			while (parameterNames.hasMoreElements()) {
				String nextName = parameterNames.nextElement();
				basePath.append("&").append(nextName).append("=").append(httpRequest.getParameter(nextName));
			}
		} else {
			if (StringUtils.hasLength(queryString)) {
				basePath.append("?").append(queryString);
			}
		}
		return URLEncoder.encode(basePath.toString(), "UTF-8");
	}

	/**
	 * 得到重定向路径
	 * 
	 * @param request
	 * @param gotoUrl
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String getRedirectUrl(ServletRequest request, String gotoUrl) throws UnsupportedEncodingException {
		String defaultLoginUrl = getLoginUrl(request, gotoUrl);
		if (defaultLoginUrl.endsWith("\\?")) {
			return defaultLoginUrl + GO_TO_PARAM_PREFIX + gotoUrl;
		} else if (defaultLoginUrl.contains("\\?")) {
			return defaultLoginUrl + "&" + GO_TO_PARAM_PREFIX + gotoUrl;
		} else {
			return defaultLoginUrl + "?" + GO_TO_PARAM_PREFIX + gotoUrl;
		}
	}

	/**
	 * 得到登陆Url
	 * 
	 * @param request
	 * @param gotoUrl
	 * @return
	 */
	public String getLoginUrl(ServletRequest request, String gotoUrl) {
		return this.defaultLoginUrl == null ? super.getLoginUrl() : this.defaultLoginUrl;
	}

	public void setDefaultLoginUrl(String defaultLoginUrl) {
		this.defaultLoginUrl = defaultLoginUrl;
	}

}
