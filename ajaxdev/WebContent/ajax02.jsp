<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
$(function(){
	$('#btnText').click(function(){
		$('#msgTxt').load('msg.txt');		
	});
	$('#btnJsp').click(function(){
		$('#msgTxt').load('msg.jsp');		
	});

});

</script>
</head>
<body>
<input type="button" id="btnText" value="get text"/>
<input type="button" id="btnJsp" value="get jsp" />
<div id=msgTxt></div>
</body>
</html>