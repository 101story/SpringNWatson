<%@page import="basic.exam5.Dice"%>
<%@page import="unit01.Point2D"%>
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
<%-- <%
	Point2D pt = new Point2D();
	pt.setX(100);
	pt.setY(200);
%> --%>
<jsp:useBean id="pt" class="unit01.Point2D"/>
<jsp:setProperty property="x" name="pt" value="1000"/>
<jsp:setProperty property="y" name="pt" value="2000"/>
<%=pt %>
<hr/>
<%=Dice.rolling() %>
</body>
</html>