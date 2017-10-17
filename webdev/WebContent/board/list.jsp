<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.model.BoardDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.BoardVO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String defIP = "0:0:0:0:0:0:0:1";
%>
    
<%
	long pg = 1;
	if(request.getParameter("pg")!=null){
		try{
			pg = Long.parseLong(request.getParameter("pg"));
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	int pageSize = 10;
	long startnum = (pg-1)*pageSize+1;
	long endnum = pg * pageSize;
 	long totalCount = 0;
 	long pageCount = 0;
 	
 	long blockSize = 10;
	long startPage = (pg-1)/blockSize*blockSize+1;
	long endPage = (pg-1)/blockSize*blockSize+blockSize;

	BoardDAO boardDAO = BoardDAO.getInstance();
	totalCount = boardDAO.getTotalCount();
	List<BoardVO> list = boardDAO.getBoardList(startnum, endnum);
	
	pageCount = totalCount / pageSize;
	if(totalCount % pageSize !=0 ){
		pageCount++;
	}
	
	if (pageCount < endPage) endPage = pageCount;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
<%
	String ip = request.getRemoteAddr();
	long time = System.currentTimeMillis(); 
	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String now = dayTime.format(new Date(time));
	System.out.println("접속 IP : "+ip+ " 시간 : "+ now);
	if(!defIP.equals(ip)){
		String str = "";
		%>IP : <%=ip %><br>
		당신의 아이피는 불량입니다. 접속 차단! 
		 <% 
		 return;
	}
%>
 
<table>
	<caption>게시물 리스트</caption>
<tr>
	<th>번호</th>
	<th>작성자</th>
	<th>제목</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>

<% 

for(BoardVO vo : list){ %>
<tr>
	<th><%=vo.getNo() %></th>
	<th><%=vo.getName()%></th>
	<th><a href='content.jsp?no=<%=vo.getNo()%>'><%=vo.getTitle()%></a></th>
	<th><%=vo.getRegdate()%></th>
	<th><%=vo.getViewcount()%></th>
</tr>
<% } %>
<tr>
	<td colspan="5">
		<%	if(startPage == 1) {%>
			[이전블록]
		<% }else{%>
			<a href ="list.jsp?pg=<%=startPage-1 %>">[이전블럭]</a>	
		<% }
		
		for(long p=startPage; p<= endPage; p++){ 
			if(p==pg){%>	
				<%=p %>
			<% }else{%>
				<a href ="list.jsp?pg=<%=p %>"><%=p %></a>	
			<% }
		}
		
		if(endPage == pageCount) {%>
			[다음블록]
		<% }else{%>
			<a href ="list.jsp?pg=<%=endPage+1 %>">[다음블럭]</a>	
		<% }
		%>
	</td>
</tr>
</table><br/>
<a href="insert.jsp">글쓰기</a><br/>
</body>
</html>