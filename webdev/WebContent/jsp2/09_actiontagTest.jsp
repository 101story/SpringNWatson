<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	
	String userId = request.getParameter("userid");
	String userPwd = request.getParameter("userpwd");
	String loginCheck = request.getParameter("loginCheck");
	
	if(loginCheck.equals("user")){
%>
	<jsp:forward page="09_userMain.jsp">
	<jsp:param value='<%=URLEncoder.encode("전고객", "UTF-8") %>' name="userName"/>
	</jsp:forward>
	<%}else{ %>
	<jsp:forward page="09_managerMain.jsp">
	<jsp:param value='<%=URLEncoder.encode("난관리", "UTF-8") %>' name="userName"/>
	</jsp:forward>
<%	}%>
