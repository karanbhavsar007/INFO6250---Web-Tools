<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add products</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h1>Add Products</h1>
	<form:form action="${contextPath}/product/add"
	
		commandName="supplieradd" method="post" enctype="multipart/form-data">
		
		Name:<form:input path="name" size="30" required="required" /><font
					color="red"><form:errors path="name" /></font></td>
		<br>
		Category:<form:input path="category" size="30" required="required" /><font
					color="red"><form:errors path="category" /></font></td>
		<br>
		Price:<form:input path="price" type="number" step="0.0001" min="0" size="30" required="required" />
		<br>
		Create Album:<form:input path="filename" size="30" /><font
					color="red"><form:errors path="filename" /></font></td>
		<br />
		Select photo: <input type="file" name="photo" required="required" />
		<br />
		<br>
		<button type="submit">Request to add Product</button>

	</form:form></br>
	
	<form:form action="${contextPath}/logout/" method="post">
	<button type="submit">Logout</button>
	</form:form><br></br>
</body>
</html>