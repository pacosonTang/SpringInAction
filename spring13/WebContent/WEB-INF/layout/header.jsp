<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<html>
<body>
	<a href="<s:url value="/"/>">
		<img alt="spittr home" src="<s:url value="/"/>/resources/images/spitter_logo_50.png" >
	</a>
	<h1>
		<c:if test="${cur_user != null}">
			dear&nbsp;${cur_user },
		</c:if>
		welcome to spittr!
	</h1>
	
	<hr />
</body>
</html>
