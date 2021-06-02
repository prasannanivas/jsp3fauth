<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "add" method = "get">
		<input type = "text" name = "num1"/>
		<input type = "text" name = "num2"/>
		<input type = "submit"/>
	</form>
	<%
 String name=(String)request.getParameter("name");
 String email=(String)request.getParameter("email");
  %>

<%=name %>

<%=email %>
	
</body>
</html>