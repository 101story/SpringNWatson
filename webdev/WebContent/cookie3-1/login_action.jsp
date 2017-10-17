<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_id = request.getParameter("user_id");
	String user_pwd = request.getParameter("user_pwd");
	String user_name = null;
	
 	String save_id = request.getParameter("save_id");
	
	Cookie c1 = null;
	if(save_id!=null){
		c1 = new Cookie("user_id", URLEncoder.encode(user_id, "UTF-8"));
		c1.setMaxAge(60*60*24*30);
		System.out.println("쿠키 생성");
	}else{	
		c1 = new Cookie("user_id", "");
		c1.setMaxAge(0);
		System.out.println("쿠키 삭제");
	}
	response.addCookie(c1);	
%>
<%
	
	
	Connection cn = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	
	StringBuffer sql = new StringBuffer();
	sql.append(" select user_name");
	sql.append(" from t_mem");
	sql.append(" where user_id = ?");
	sql.append(" and user_pw = ?");
	
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bigdata", "bigdata");
		st = cn.prepareStatement(sql.toString());
	    st.setString(1, user_id);
	    st.setString(2, user_pwd);
		rs = st.executeQuery();
		if(rs.next()){
			user_name= rs.getString("user_name");
		}
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		if(rs != null) try{rs.close();}catch(Exception e){}
		if(st != null) try{st.close();}catch(Exception e){}
		if(cn != null) try{cn.close();}catch(Exception e){}
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
<% 
	if(user_name!=null){
		out.println(user_name+"님이 로그인 하셨습니다.");
	}else{%>
		<script type="text/javascript">	
			alert('뉴...규....????');
			location.href = 'login.jsp'; //다시 폼으로 가게됨 reset
		</script>
	<%}
%>
</body>
</html>