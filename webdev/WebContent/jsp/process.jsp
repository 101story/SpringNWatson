<%@ page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	public String commentAge(int age){
		String[] nona = {"묘령","약관","이립","불혹","지천명","이순","종심"};	
		int n = age/10;
		if(n<1){
			n = 1;
		}else if(n>6){
			n = 7;
		}
		return nona[n-1];
	}	
%>
<%--!
	public void commentAge(JspWriter out, int age) throws IOException {
		switch(age/10) {
		case 0:
		case 1:
			out.println("묘령");break;
		case 2:
			out.println("약관");break;
		case 3:
			out.println("이립");break;
		case 4:
			out.println("불혹");break;
		case 5:
			out.println("지천명");break;
		case 6:
			out.println("이순");break;
		default:
			out.println("종심");break;
		}
	}	
--%>
<%
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
내 이름은 <%=name %> 야. 
그래, <%=age %> ( <%=commentAge(age) %> )이다.!!
<%-- 그래, <%=age %> ( <% commentAge(out, age); %> )이다.!!--%>
</body>
</html>