<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title><sitemesh:write property='title' />基础布局 - Default</title></title>
<link rel="stylesheet" href="${ctxStatic}/layui/dist/css/layui.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<sitemesh:write property='body' />
			</div>
		</div>
	</div>
	
	<!-- basic scripts -->
	<!-- [if !IE]> -->
	<script src="${ctxStatic}/js/jquery.js"></script>
	<!-- <![endif]-->
	<!-- [if IE]>
	<script src="${ctxStatic}/assets/js/jquery1x.js"></script>
	<!-- [endif]-->
	<script src="${ctxStatic}/layui/dist/layui.js"></script>
	<script src="${ctxStatic}/js/pub.js"></script>
	<sitemesh:write property='xdtscript' />
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>