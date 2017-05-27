<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Products</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<h1>Search Products</h1>
	<form:form action="${contextPath}/product/buyerSearch"
		modelAttribute="" method="post">
            Keyword:<input name="keyword" required="required"></input>
		<br />
		<input type="radio" name="search" value="name" checked="checked">Search By Name</input>
		<br />
		<input type="radio" name="search" value="category">Search By Category</input>
		<br />

		<button type="submit">Search Products</button>
	</form:form></br>
	<form:form action="${contextPath}/user/welcomebackbuyer" method="get">
			<button type="submit">Go Back</button></br></br>
		</form:form>
</body>
</html>