<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
			$("#b01").click(function(){
			  $.post("<%=path%>/mvc/getPerson",
			  {
			    name:"Donald Duck",
			  },
			  function(data,status){
				  alert(data);
			  });
			});
	});
</script>
</head>
	<body>
	  <form action="<%=path%>/mvc/upload" method="post" enctype="multipart/form-data">
          <input type="file" name="file">
          <input type="submit" value="submit">
      </form>
		<c:out value="${requestScope.p.name}" /> 
		${requestScope.p.age}
		<br />
		<c:out value="-----------------------------------"></c:out>
		<button id="b01" type="button">Change Content</button>
	</body>
</html>