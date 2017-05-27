<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View products</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h1>View Products</h1>
	
	<form:form action="${contextPath}/cart/addToCart"
		modelAttribute="approvedProducts" method="post">
		<table border="1">
			<tr>
				<td><b>Product Id</b></td>
				<td><b>Photo Thumb</b></td>
				<td><b>Name</b></td>
				<td><b>Category</b></td>
				<td><b>Price</b></td>
				<c:if test="${sessionScope.user.role == 'buyer'}">
					<td><b>Quantity</b></td>
					<td><b>Add Products</b></td>
				</c:if>
			</tr>
			<c:forEach var="approvedProducts" items="${approvedProducts}">
				<tr>
					<td><input value="${approvedProducts.id}" name="prodId"
						readonly /></td>
					<td><img height="150" width="150" name="image"
						src="${approvedProducts.filename}" /></td>
					<td><input value="${approvedProducts.name}" name="name"
						readonly /></td>
					<td><input value="${approvedProducts.category}"
						name="category" readonly /></td>
					<td><input value="${approvedProducts.price}" name="price"
						readonly /></td>
					<c:if test="${sessionScope.user.role == 'buyer'}">
						<td><input type="number" name="quantity" class="qty${approvedProducts.id}" min="1"></td>
						<td><select name="option">
								<option class="yes${approvedProducts.id}" value="yes">Yes</option>
								<option class="no${approvedProducts.id}" value="no" selected="selected">No</option>
						</select></td>
						
					</c:if>
				</tr>
				 
			</c:forEach>
		</table>
		<c:if test="${sessionScope.user.role == 'buyer'}">
			<button type="submit">Add products to Cart</button></br></br>
		</c:if>
	</form:form>
	
	<form:form action="${contextPath}/user/welcomebackbuyer" method="get">
	<c:if test="${sessionScope.user.role == 'buyer'}">
			<button type="submit">Go Back</button></br></br>
		</c:if>
		</form:form>
	
	<form:form action="${contextPath}/logout/" method="post">
	<button type="submit">Logout</button>
	</form:form>
</body>
</html>