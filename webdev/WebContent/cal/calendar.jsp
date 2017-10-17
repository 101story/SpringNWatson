<%@ page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
!!!!
<!-- html 주석 -->
<%-- 스크립트릿 주석 --%>
<%	//scriptlet
	/* 멀티라인 주석 */
	int year = 2017;
	int month = 6;
	
	Calendar c = Calendar.getInstance();
	c.set(year, month - 1, 1);
	int week = c.get(Calendar.DAY_OF_WEEK);
	int end_day = c.getActualMaximum(Calendar.DATE);
%>	
<%= year + "년 " + month + "월 " %>
<table>
<tr>
	<td>일</td>
	<td>월</td>
	<td>화</td>
	<td>수</td>
	<td>목</td>
	<td>금</td>
	<td>토</td>
</tr>
<tr>	
<%
	for(int d=1; d < week; d++) {
		out.println("<td></td>");
	}
	for(int d=1, w=week; d <= end_day; d++, w++) {
		out.println("<td>" + d + "</td>");
		if (w % 7 == 0) out.println("</tr><tr>");
	}
%>
</tr>
</table>
</body>
</html>