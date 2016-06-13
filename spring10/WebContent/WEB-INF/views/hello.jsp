<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8">
		<title>Hello World!</title>
	</head>
	<body>
		<h1>Hello !</h1>
        <form action="/logout" method="post">
            <input type="submit" value="Sign Out"/>
        </form>
	</body>
</html>