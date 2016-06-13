<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<html>
<head>
<title>Spitter</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="../resources/style.css" />">

<style type="text/css">
div.errors {
	background-color: #ffcccc;
	border: 2px solid red;
}

label.error {
	color: red;
}

input.error {
	background-color: #ffcccc;
}
</style>
</head>
<body>
	<h1>Register</h1>

	<sf:form method="POST" commandName="spitter"
		enctype="multipart/form-data">
		<sf:errors path="*" element="div" cssClass="errors" />

		<sf:label path="firstName" cssErrorClass="error">First Name</sf:label>:
		<sf:input path="firstName" cssErrorClass="error" value="Thmoson" />
		<br />

		<sf:label path="lastName" cssErrorClass="error">Last Name</sf:label>:
		<sf:input path="lastName" cssErrorClass="error" value="Green" />
		<br />

		<sf:label path="email" cssErrorClass="error">email</sf:label>
		<sf:input path="email" cssErrorClass="error" value="nihao@sina.com" />
		<br />

		<sf:label path="username" cssErrorClass="error">username</sf:label>:
		<sf:input path="username" cssErrorClass="error" />
		<br />

		<sf:label path="password" cssErrorClass="error">password</sf:label>:
		<sf:password path="password" cssErrorClass="error" />
		<br />

		accept="image/jpeg,image/jpg,image/png,image/gif": <br />
		<input name="profilePicture" type="file"
			accept="image/jpeg,image/jpg,image/png,image/gif" />
		<br />
		<input type="submit" value="注册" />
		<br />
		<br />
	</sf:form>
</body>
</html>
