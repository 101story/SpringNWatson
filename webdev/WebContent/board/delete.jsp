<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
<form action="delete_action.jsp" method="post">
<table>
	<caption>게시물 수정</caption>
<%
	long no = Long.parseLong(request.getParameter("no"));
%>
<tr>
	<th>글번호</th>
	<td><%=no %><input type="hidden" name="no" value="<%=no %>"/></td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="pwd" /> <br/>
		<div style="color:red">처음 등록시 입력한 비밀번호</div>
	</td>
</tr>
</table>
<input type="submit" value="완료" />
</form>
</body>
</html>