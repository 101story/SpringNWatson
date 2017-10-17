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
<form action="Login" method="post">
<table>
	<caption>로그인</caption>
<tr>
	<th>아이디</th>
	<td><input type="text" name="user_id" value="${user_id }"/></td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="user_pwd" /></td>
</tr>
<tr>
	<td></td>
	<td><input type="checkbox" name="save_id"  ${user_id != null ? 'checked ':'' }>아이디저장</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="완료" />
	</td>
</tr>
</table>
</form>
</body>
</html>