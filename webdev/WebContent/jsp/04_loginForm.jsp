<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type = "text/javascript">
	function input_check(){
		console.log('input_chect 수행중');
		var f = document.myform;
		console.log('user id : '+f.userid.value);
		console.log('user pwd : '+f.userpwd.value);
		console.log('----------------');
		console.log('user id : '+document.getElementById('userid').value);
		console.log('user pwd : '+document.getElementById('userpwd').value);
		
		var user_id = document.getElementById('userid').value;
		var user_pwd = document.getElementById('userpwd').value;
		if(user_id === ''){ 
			alert('아이디를 입력하세요.');
			return;
		}
		if(user_pwd === ''){ 
			alert('비밀번호를 입력하세요.');
			return;
		}
		f.submit();
	}
</script>
</head>
<body>
<form name=myform method="post" action="04_testLogin.jsp">
	<label for="userid"> 아이디 : </label>
	<input type="text" name="id" id="userid"><br>
	
	<label for="userpwd">암 &nbsp; 호 : </label>
	<input type="password" name="pwd" id="userpwd"><br>
	
	<input type="button" value="로그인" onclick="input_check()">
</form>
</body>
</html>