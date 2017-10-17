<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<body>
<%=request.getParameter("id") %><br>
<%=request.getParameter("pwd") %><br>
${param.id } <br>
${param["pwd"]}
</body>
</html>