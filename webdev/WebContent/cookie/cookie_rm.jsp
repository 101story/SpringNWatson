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
	String[] names = request.getParameterValues("name");
	if(names!=null){
		for(String name: names){
			Cookie cookie = new Cookie(name, "");		
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	
	response.sendRedirect("cookie_status.jsp");
%>
</body>
</html>