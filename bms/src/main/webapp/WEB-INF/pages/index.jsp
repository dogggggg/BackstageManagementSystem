<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="${ctxStatic}/layui/dist/css/layui.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">后台管理系统</div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:void(0);">  Admin
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:void(0);">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:void(0);">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:void(-1);">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">代码生成模块</a></li>
					<li class="layui-nav-item"><a href="javascript:;">系统管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">菜单设置</a>
							</dd>
							<dd>
								<a href="javascript:;">组织架构</a>
							</dd>
							<dd>
								<a href="javascript:;">用户管理</a>
							</dd>
							<dd>
								<a href="javascript:;">权限设置</a>
							</dd>
							<dd>
								<a href="javascript:;">日志管理</a>
							</dd>
							<dd>
								<a href="javascript:;">数据字典</a>
							</dd>
							<dd>
								<a href="javascript:;">后台作业</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<iframe frameborder="0" id="home"></iframe>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© Powered by Halb.Rooks.Lee - <a href="mailto:2926644365@qq.com">Send Email to Halb</a>
		</div>
	</div>
	<script src="${ctxStatic}/layui/dist/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>