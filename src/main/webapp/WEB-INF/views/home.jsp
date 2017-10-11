<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="display"> display voices</a> <br><br>
<a href="weather"> watson: classify weather</a> <br><br>
<a href="text2speech"> watson: text to speech</a>
</body>
</html>
