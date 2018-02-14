package com.hab.bms.sys.common.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class BMSSiteMeshFilter  extends ConfigurableSiteMeshFilter {
	// 弃用，使用ScriptTagRuleBundle的XML调用
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.setMimeTypes("text/html", "application/xhtml+xml", "application/vnd.wap.xhtml+xml")
//				.addDecoratorPath("/page/system/*", "/page/default.jsp")
				.addDecoratorPath("/page/**/*", "/WEB-INF/layouts/default.jsp")
				.addExcludedPath("/static*")
				.addExcludedPath("/js*")
				.addExcludedPath("/page/index")
				.addExcludedPath("/page/login")
				.addTagRuleBundles(new ScriptTagRuleBundle());
	}

}
