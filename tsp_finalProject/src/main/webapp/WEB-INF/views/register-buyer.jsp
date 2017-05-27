<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Buyer Registration</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a>
	<br />

	<h2>Register as a New Buyer</h2>

	<form:form action="${contextPath}/user/registerbuyer"
		commandName="buyer" method="post">

		<table>
			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" /> <font
					color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" /> <font
					color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" /> <font
					color="red"><form:errors path="lastName" /></font></td>
			</tr>

			<tr>
				<td>Gender:</td>
				<td><form:radiobutton path="gender" value="male" label="Male"
						checked="checked" /> <form:radiobutton path="gender"
						value="female" label="Female" /></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30" type="email" /> <font
					color="red"><form:errors path="email" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30" /> <font
					color="red"><form:errors path="password" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>