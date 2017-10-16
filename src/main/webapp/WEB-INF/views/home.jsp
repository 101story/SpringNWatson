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
<ol>
<li><a href="display"> watson: display voices</a> </li>
<li><a href="weather"> watson: classify weather</a> </li>
<li><a href="text2speech"> watson: text to speech</a></li>

<li><a href="board/1/1/">restful: 1번 게시판 1페이지</a></li>
<li><a href="board/1/2/">restful: 1번 게시판 2페이지</a></li>
<li><a href="board/2/1/">restful: 2번 게시판 1페이지</a></li>
<li><a href="board/2/2/">restful: 2번 게시판 2페이지</a></li>

<li><a href="displayJSON">jackson library 이용 RestController 서비스 구현</a></li>
<li><a href="displayJSON2">jackson library 이용 Controller + @ResponseBody 서비스 구현</a></li>
</ol>
</body>
</html>
