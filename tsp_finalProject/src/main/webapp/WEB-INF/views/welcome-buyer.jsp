<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Buyer</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<h1>Welcome Buyer</h1>
	<h2>Please make your selection below</h2>
	<form method="post" action="${contextPath}/welcome/buyer">
		<select name="option">
			<option value="search">Search products</option>
			<option value="view">View all products</option>
		</select>
		<button type="submit">Submit</button>
	</form>
</body>
</html>