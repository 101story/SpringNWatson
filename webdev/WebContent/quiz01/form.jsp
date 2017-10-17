<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="want.jsp" method="post">
원하는 것은?
<select name="want">
	<option>야구</option>
	<option>축구</option>
	<option selected="selected">농구</option>
</select>
<input type="submit" value="확인" />
</form>
</body>
</html>