<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="display"> watson: display voices</a> <br><br>
<a href="weather"> watson: classify weather</a> <br><br>
<a href="text2speech"> watson: text to speech</a><br><br>
<a href="board/1/1/">restful: 1번 게시판 1페이지</a><br/>
<a href="board/1/2/">restful: 1번 게시판 2페이지</a><br/>
<a href="board/2/1/">restful: 2번 게시판 1페이지</a><br/>
<a href="board/2/2/">restful: 2번 게시판 2페이지</a><br/>
</body>
</html>
