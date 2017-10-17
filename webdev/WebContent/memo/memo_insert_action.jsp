<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="memo.model.MemoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mvo" class="memo.model.MemoVO"/>
<jsp:setProperty name="mvo" property="*" />
<%
//Connection, PreparedStatement, ResultSet
	Connection cn = null;
	PreparedStatement ps = null;
	
	StringBuffer sql = new StringBuffer();
	sql.append("insert into t_memo(no, name, title, content)");
	sql.append("values(seq_memo.nextval, ?, ?, ?)");
	boolean result = false;
		
	try{
		Class.forName("oracle.jdbc.OracleDriver");
		cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bigdata", "bigdata");
		ps = cn.prepareStatement(sql.toString());
		ps.setString(1, mvo.getName());
		ps.setString(2, mvo.getTitle());
		ps.setString(3, mvo.getContent());
		ps.executeUpdate();
		result = true;
	}catch(Exception e){
		out.println("실패 "+e.getMessage());	
		e.printStackTrace();
	}finally{
		if(ps!=null)try{ps.close();}catch(Exception e){}
		if(cn!=null)try{cn.close();}catch(Exception e){}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<body>
<%=mvo %>
<% if(result) { %>
메모 등록 성공
<% } else { %>
메모 등록 실패
<% } %>
</body>
</html>