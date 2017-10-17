<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	int global_cnt = 0;
	String defIP = "70.12.50.61";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	global_cnt++;	
	String ip = request.getRemoteAddr();
	String str = "당신의 아이피는 불량입니다. 접속 차단!";
	if(1 == global_cnt%2 && defIP.equals(ip)){
		str =  "당신의 아이피는 "+ip;
	}
%>
global_cnt : <%=global_cnt %> <br>
IP : <%=ip %><br>
<%=str %> 
</body>
</html>