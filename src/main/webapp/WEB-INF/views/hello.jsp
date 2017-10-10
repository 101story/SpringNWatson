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

<textarea id="readText" style="width:500px; height:100px"></textarea><br>
<select name="voiceselect">
<c:forEach items="${voices}" var="voice">
	<option value=${voice.description }>${voice.description }</option>
</c:forEach>
</select>
<br/>

<input type="range"  min="0" max="100" width="500"/>
<button type="button">읽기</button>

</body>
</html>