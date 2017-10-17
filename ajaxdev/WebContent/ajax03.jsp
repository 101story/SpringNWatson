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
	$('#btnJsp').click(function(){
		console.log('click');
		$.ajax({
			method:	'GET',
			url:	'iam.jsp',
			data:	{name:$('#name').val()}
		})
		.done(function(data){
			console.log(data);
			$('#msgTxt').text(data);
			<!-- $('#msgTxt').html(data); -->
		});
	});
});

</script>
</head>
<body>
<hr/>
<input type="text" id="name"/>
<input type="button" id="btnJsp" value="get jsp" />
<div id="msgTxt"></div>
</body>
</html>