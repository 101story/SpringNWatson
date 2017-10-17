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
<form action="login_action.jsp" method="post">
<table>
	<caption>쿠키 생성 폼</caption>
<tr>
	<th>아이디</th>
	<td><input type="text" name="user_id"/></td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="user_pwd"/></td>
</tr>
	<td colspan="2" align="center">
		<input type="submit" value="로그인"/>
	</td>
</table>

</form>
</body>
</html>