<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("msg1", "abc");
	session.setAttribute("msg2", "def");
	session.setMaxInactiveInterval(10);
	response.sendRedirect("session_status.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<body>

</body>
</html>