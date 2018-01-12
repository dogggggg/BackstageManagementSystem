<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<html lang="en">

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>BMS Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- CSS -->
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
<link rel="stylesheet" href="${ctxStatic}/assets/css/reset.css">
<link rel="stylesheet" href="${ctxStatic}/assets/css/supersized.css">
<link rel="stylesheet" href="${ctxStatic}/assets/css/style.css">
</head>

<body>
	<div class="page-container">
		<h1>Login</h1>
		<form id="loginForm" method="post" id="form" action="${ctx}/loginTest">
			<input type="text" id="userName" name="username" class="username" placeholder="Username" value=1> 
			<input type="password" id="pwd" name="password" class="password" placeholder="Password" value=1>
            <label><input type="checkbox" name="rememberMe"/> RemeberMe</label>  
			<button type="submit" id="btn_login">Sign me in</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
	</div>

	<!--[if !IE]> -->
	<script src="${ctxStatic}/js/jquery.js"></script>
	<script src="${ctxStatic}/js/pub.js?date=ll"></script>
	<!-- page specific plugin scripts -->
	<script src="${ctxStatic}/validator/jquery-html5Validate.js"></script>
	<script src="${ctx}/js/login.js"></script>
</body>
</html>