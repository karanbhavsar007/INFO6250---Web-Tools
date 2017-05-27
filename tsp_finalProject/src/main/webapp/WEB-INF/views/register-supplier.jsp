<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supplier Registration</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a>
	<br />

	<h2>Register as a New Supplier</h2>

	<form:form action="${contextPath}/user/registersupplier"
		commandName="supplier" method="post">

		<table>
			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30"  />
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Company Name:</td>
				<td><form:input path="company" size="30"  />
					<font color="red"><form:errors path="company" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30" type="email"
						 /> <font color="red"><form:errors
							path="email" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						 /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>

		</table>

	</form:form>

</body>
</html>