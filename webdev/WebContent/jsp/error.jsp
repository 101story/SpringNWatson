<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="/favicon.ico" />
</head>
<body>
다음과 같은 에러가 발생하였습니다.
<%= exception.getMessage() %>
</body>
</html>