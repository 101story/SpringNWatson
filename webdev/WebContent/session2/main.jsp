<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<%-- <% 
	if(session.getAttribute("user_id")==null){%>
	<script type="text/javascript">
		alert('먼저 로그인하세요.');
		location.href='session_form.jsp';
	</script>
<%
		return;
	} 	
%> --%>
<body>
<h1>세션 테스트 중입니다....</h1>
현재 <%=session.getAttribute("user_id") %>
(<%=session.getAttribute("user_name") %>,
<%=session.getAttribute("user_level") %>)
회원님이 접속중입니다.
<a href="session_inactivate.jsp"> 로그아웃</a>
</body>
</html>