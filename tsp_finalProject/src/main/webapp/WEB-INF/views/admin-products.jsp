<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h1>Requests for all the products</h1>
	<form:form action="${contextPath}/product/adminadd"
		modelAttribute="viewRequest" method="post">
		<table border="1">
			<tr>
				<td><b>Product Id</b></td>
				<td><b>Photo Thumb</b></td>
				<td><b>Name</b></td>
				<td><b>Category</b></td>
				<td><b>Price</b></td>
				<td><b>Action</b></td>
				<td><b>Status</b></td>
			</tr>
			<c:forEach var="viewRequest" items="${viewRequest}">
				<tr>

					<td><input value="${viewRequest.id}" name="id" readonly /></td>
					<td><img height="150" width="150"
						src="${viewRequest.filename}" name="filename" readonly /></td>
					<td><input value="${viewRequest.name}" name="name" readonly /></td>
					<td><input value="${viewRequest.category}" name="category"
						readonly /></td>
					<td><input value="${viewRequest.price}" name="price" readonly /></td>
					<td><select name="option">
							<option value="approve">Approve</option>
							<option value="decline">Decline</option>
					</select></td>
					<td><input value="${viewRequest.status}" name="status" readonly /></td>

				</tr>
			</c:forEach>
		</table>
		<button type="submit">Add Product</button></br></br>
	</form:form>
	<form:form action="${contextPath}/logout/" method="post">
	<button type="submit">Logout</button>
	</form:form>
</body>
</html>