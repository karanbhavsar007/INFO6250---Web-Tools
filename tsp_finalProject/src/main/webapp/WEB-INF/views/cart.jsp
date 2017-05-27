<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h1>Cart</h1>
	<h4>All selected products in your shopping cart</h4>

	<form:form action="${contextPath}/order/generateOrder"
		modelAttribute="cart" method="post">
		<table border="1">
			<tr>
				<td><b>Name</b></td>
				<td><b>Price</b></td>
				<td><b>Quantity</b></td>
				<td><b>Total Price</b></td>
			</tr>
			<c:forEach var="productCart" items="${cart.productCart}">
				<tr>
					<td>${productCart.product.name}</td>
					<td>${productCart.product.price}</td>
					<td>${productCart.quantity}</td>
					<td>${productCart.product.price * productCart.quantity}</td>
				</tr>
			</c:forEach>

		</table>
		<input type="hidden" name="cartId" value="${cart.id}" />
	Grand Total : ${cart.totalPrice} <br />
		<button type="submit">Submit Order</button>
	</form:form>

</body>
</html>