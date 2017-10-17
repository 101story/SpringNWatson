<%@page import="memo.model.MemoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name=request.getParameter("name");
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	
	MemoVO mvo = new MemoVO();
	mvo.setName(name);
	mvo.setTitle(title);
	mvo.setContent(content);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<body>
<%=mvo %>
</body>
</html>