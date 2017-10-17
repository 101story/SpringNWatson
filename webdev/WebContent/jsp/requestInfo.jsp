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
컨텍스트 패스 : <%= request.getContextPath() %> <p>  
요청방식 : <%= request.getMethod() %><p>
요청한 URL : <%= request.getRequestURL() %><p>
요청한 URI : <%= request.getRequestURI() %><p>
서버 이름 : <%= request.getServerName() %><p>
프로토콜 : <%= request.getProtocol() %><p>
</body>
</html>