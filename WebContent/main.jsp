<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Integrity Electronics</title>
</head>
<body>


<% User user = (User) request.getAttribute("user"); %>

<h2> Greetings,  ${ user.getUsername() }  </h2>
	
	<div>
		<h1>Product Listing: </h1>
	
		<p> <%= user.toString() %> </p>
	
		<form action="Integrity" method="post" name="main">
			<input type="hidden" name="action" value="add" >
			<p>Please enter a keyword: <input type="text" name="item">
			<input type="submit" name="" value="Go!">
		</form>
		
		<form action="Integrity" method="post" name="main">
			<input type="hidden" name="action" value="remove">
			<p>Please enter the item to be removed: <input type="text" name="item">
			<input type="submit" name="" value="Go!">
		
		</form>
		
	</div>
	
	
	
	

	
	
	

</body>
</html>