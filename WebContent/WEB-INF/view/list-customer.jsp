<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer list</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/customer-relation.jpg" />


<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<script>
	function confirmDelete(){
	return confirm("Are you sure you want to Delete this Customer ? ");		
	}
</script>
<body>

	<div id="wrapper">
		<div id="header">
				<h2>CRM-Customer Relationship Manager </h2>		
		</div>
	</div>
	<div id="container">
		<div id="content">
			<form:form action="search" method="GET">
				<input class="container" type="text" placeholder="Enter first name" name="searchName" />
				<input type="submit" value="Search"/>
			</form:form>
		</div> 
	</div>
	<div style="margin-left:66%">
		<img width="42px" src="${pageContext.request.contextPath}/resources/img/view.png"/> 
		<div style="margin-left:3%">${pageCount}</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd';return false;" class="add-button"/>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
		</tr>
			<c:forEach var="tempCust" items="${customers}">
			<c:url var="updateLink" value="${pageContext.request.contentType}/customer/showSaveFormForUpdate">
				<c:param name="customerID" value="${tempCust.id}"/>
			</c:url>
			<c:url var="deleteLink" value="${pageContext.request.contentType}/customer/delete">
				<c:param name="customerID" value="${tempCust.id}"/>
			</c:url>
			<tr>
				<td>${tempCust.firstName}</td>
				<td>${tempCust.lastName}</td>
				<td>${tempCust.email}</td>
				<td><a href="${updateLink}">Update</a> | <a href="${deleteLink}" onclick="return confirmDelete();">Delete</a></td>
				<td></td>
			</tr>
		</c:forEach>
	</table>		
		</div>
	</div>
</body>
</html>