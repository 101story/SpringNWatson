<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
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
<a href="cookie_form.jsp">쿠키생성</a>
<hr/>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){%>
		[<a href="cookie_rm.jsp?name=user_id">아뒤 삭제</a>]
		[<a href="cookie_rm.jsp?name=user_name">이름 삭제</a>]
		[<a href="cookie_rm.jsp?name=user_level">레벨 삭제</a>]
		[<a href="cookie_rm.jsp?name=user_id&name=user_name&name=user_level">전체 삭제</a>]
		<hr/>
<%	} %>
[생성 쿠키 리스트]<p>
<%
	if(cookies!=null){
		for(Cookie c : cookies){
			if(!"JSESSIONID".equals(c.getName())){
				out.println("쿠키이름 : "+c.getName()+"<br/>");
				out.println("쿠키  값 : "+URLDecoder.decode(c.getValue())+"<br/>");
				out.println("<hr/>");	
			}
		}		
	}


%>
</body>
</html>