<%@page import="board.model.BoardDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="board.model.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	BoardVO boardVO = new BoardVO();	
	boardVO.setNo(Long.parseLong(request.getParameter("no")));
	boardVO.setPwd(request.getParameter("pwd"));
	
	BoardDAO boardDAO  = BoardDAO.getInstance();
	boolean result = boardDAO.deleteBoard(boardVO);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<body>
<script type="text/javascript">
<%	if(result) {%>
	alert('글 삭제 성공');
	location.href='list.jsp';
<% }else {%>
	alert('글 삭제 실패');
	location.href='content.jsp?no=<%=boardVO.getNo() %>';
<% }%>
</script>
</body>
</html>