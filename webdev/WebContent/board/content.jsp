<%@page import="board.model.BoardDAO"%>
<%@page import="board.model.BoardVO"%>
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
<table>
	<caption>게시물 상세보기</caption>
<tr>
	<th>글번호</th>
	<td><%=vo.getNo() %></td>
</tr>
<tr>
	<th>작성자</th>
	<td><%=vo.getName() %></td>
</tr>
<tr>
	<th>제목</th>
	<td><%=vo.getTitle() %></td>
</tr>
<tr>
	<th>내용</th>
	<td><%=vo.getContent() %></td>
</tr>
<tr>
	<th>날짜</th>
	<td><%=vo.getRegdate() %></td>
</tr>
<tr>
	<th>조회수</th>
	<td><%=vo.getViewcount() %></td>
</tr>
</table><br/>
<a href="list.jsp">리스트</a>
<a href="update.jsp?no=<%=vo.getNo()%>">수정</a>
<a href="delete.jsp?no=<%=vo.getNo()%>">삭제</a>
</form>
</body>
</html>