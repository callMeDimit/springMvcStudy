<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dimit.entity.Person" %>
<!DOCTYPE html PUBLIC "-//3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello world!!!
	<%
		Person p = (Person)request.getAttribute("p");
		p.getName();
	%>
</body>
</html>