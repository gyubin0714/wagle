<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 생기면 오는 곳</title>
</head>
<body>

	<h1 align="center" style="color:red; margin-top:100px;"><%= errorMsg %></h1>
	
</body>
</html>