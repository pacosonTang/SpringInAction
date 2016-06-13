<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
</head>
<body>	
	<form method="POST">		
		<p>
			<label>Username</label>
			<input type="text" name="username" />  
		</p>
		<p>
			<label>Password</label> 
			<input type="password" name="password" />  
		</p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button type="submit" class="btn">登录</button>
	</form>
</body>
</html>