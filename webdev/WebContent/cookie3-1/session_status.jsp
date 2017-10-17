<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<body>
<%
	String msg1 = (String)session.getAttribute("msg1");
	String msg2 = (String)session.getAttribute("msg2");
%>
msg1 : <%=msg1 %>
msg2 : <%=msg2 %>
<a href="session_add.jsp">새션생성</a>
</body>
</html>