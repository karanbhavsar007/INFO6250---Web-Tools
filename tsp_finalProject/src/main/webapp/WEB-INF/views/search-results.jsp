<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h3>You searched for : ${sessionScope.keyword}</h3>
	<h1>Here are the search results</h1>
	<form:form action="${contextPath}/cart/addToCart"
		modelAttribute="productList" method="post">
		<c:if test="${ not empty productList}">
			<table border="1">
				<caption>
					<h2>List of Products</h2>
				</caption>
				<tr>
					<th><b>Product Id</b></th>
					<th><b>Photo Thumb</b></th>
					<th><b>Name</b></th>
					<th><b>Category</b></th>
					<th><b>Price</b></th>
					<td><b>Quantity</b></td>
					<td><b>Add Products</b></td>
				</tr>
				<c:forEach items="${productList}" var="productList">
					<tr>
						<td><input value="${productList.id}" name="prodId" readonly /></td>
						<td><img height="150" width="150" name="image"
							src="${productList.filename}" /></td>
						<td><input value="${productList.name}" name="name" readonly /></td>
						<td><input value="${productList.category}" name="category"
							readonly /></td>
						<td><input value="${productList.price}" name="price" readonly /></td>
						<td><input type="number" name="quantity" min="1"></td>
						<td><select name="option">
								<option value="yes">Yes</option>
								<option value="no" selected="selected">No</option>
						</select></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<button type="submit">Add products to Cart</button>
	</form:form></br>
	<form:form action="${contextPath}/welcome/backBuyer" method="get">
			<button type="submit">Go Back</button></br></br>
		</form:form>
</body>
</html>