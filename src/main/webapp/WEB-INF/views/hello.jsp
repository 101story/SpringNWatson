<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/resource/css/bootstrap.css" />">
<script src="<c:url value="/resource/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resource/js/bootstrap.js" />"></script>
<script type="text/javascript">
</script>

<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
</head>
<body>
${msg}
<img src="resources/waving.png">
<br/>
<table border="1">
<c:forEach items="${list }" var="vo">
<tr>
	<td>${vo.no }</td>
	<td>${vo.statement }</td>
	<td>${vo.lang }</td>
	<td><a href="delete/${vo.no}"> x </a> </td>
</tr>
</c:forEach>
</table>
</body>
</html>