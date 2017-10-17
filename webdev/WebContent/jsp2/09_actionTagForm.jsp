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
<form action="09_actiontagTest.jsp">
	<label for="userid"> 아이디 : </label>
	<input type="text" name="userid"><br>
	
	<label for="userpwd">암 &nbsp; 호 : </label>
	<input type="password" name="userpwd"><br>
	
	<input type="radio" name ="loginCheck" value="user" checked="checked"> 사용자<br>
	<input type="radio" name ="loginCheck" value="manager"> 관리자<br>
	<input type="submit" value="로그인" >
</form>
</body>
</html>