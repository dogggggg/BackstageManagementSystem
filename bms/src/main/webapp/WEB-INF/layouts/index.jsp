<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF//include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>基础软件</title>
<!-- bootstrap & fonts  -->
<link rel="stylesheet" href="${ctxStatic}/ace/css/bootstrap.css" />
<link rel="stylesheet" href="${ctxStatic}/ace/css/font-awesome.css" />

<!-- ace styles & fonts & common-->
<link rel="stylesheet" href="${ctxStatic}/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctxStatic}/ace/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctxStatic}/css/common.css" />

<!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxStatic}/ace/css/ace-part2.css" class="ace-main-stylesheet" />
    <![endif]-->
<!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxStatic}/ace/css/ace-ie.css" />
    <![endif]-->

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
    <script src="${ctxStatic}/ace/js/html5shiv.js"></script>
    <script src="${ctxStatic}/ace/js/respond.js"></script>
    <![endif]-->
<sitemesh:write property='head' />
</head>
<body class="no-skin">
	<sitemesh:write property='body' />
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="${ctxStatic}/ace/js/jquery.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
<script src="${ctxStatic}/ace/js/jquery1x.js"></script>
<![endif]-->
	<script src="${ctxStatic}/ace/js/bootstrap.js"></script>
	<script src="${ctxStatic}/ace/js/ace.js"></script>
	<script src="${ctxStatic}/js/pub.js"></script>
	<!-- page specific plugin scripts -->
	<script src="${ctxStatic}/layer/layer/layer.dev.js"></script>
	<!--日期格式化组件-->
	<script src="${ctxStatic}/ace/js/date-time/moment.js"></script>
	<!-- ace scripts -->
	<script src="${ctxStatic}/ace/js/ace/elements.scroller.js"></script>

	<sitemesh:write property='xdtscript' />
</body>
</html>
