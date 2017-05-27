<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>The Sport Place</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h1>
		<center>The Sport Place</center>
	</h1>
	<form action="${contextPath}/user/register" method="post">
		<input type="radio" name="radios" value="buyer" checked> Buyer<br>
		<input type="radio" name="radios" value="supplier"> Supplier<br>
		<input type="submit" value="Register" />
	</form>

	<h2>Login</h2>
	<form action="${contextPath}/user/login" method="post">

		<table>
			<tr>
				<td>User Name:</td>
				<td><input name="username" size="30" required="required" /></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" size="30"
					required="required" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>

		</table>

	</form>

</body>
</html>