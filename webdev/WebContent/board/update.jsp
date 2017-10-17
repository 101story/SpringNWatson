<%@page import="board.model.BoardDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="board.model.BoardVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	long no = Long.parseLong(request.getParameter("no"));
	BoardDAO boardDAO = BoardDAO.getInstance();
	BoardVO vo = boardDAO.getBoard(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
<form action="update_action.jsp" method="post">
<table>
	<caption>게시물 수정</caption>
<tr>
	<th>글번호</th>
	<td><%=vo.getNo() %><input type="hidden" name="no" value="<%=vo.getNo() %>"/></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type="text" name="name" value="<%=vo.getName()%>"/></td>
</tr>
<tr>
	<th>제목</th>
	<td><input type="text" name="title"  value="<%=vo.getTitle() %>"/></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea cols="40" rows="5" name="content" ><%=vo.getContent()%></textarea></td>
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