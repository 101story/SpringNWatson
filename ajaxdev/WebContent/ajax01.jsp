<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<LINK REL="SHORTCUT ICON" HREF="../favicon.ico" />
<script type="text/javascript">
var xhr = null;

function load(filename){
	console.log(filename);
	if(window.ActiveXObject){
		//explorer 4~10 이전 버전 
		console.log('explorer');
		try{
			xhr = new ActiveXObject('Msxml2.XMLHTTP');
		}catch(e){
			xhr = new ActiveXObject('Microsoft.XMLHTTP');
		}
	}else if(window.XMLHttpRequest){
		//chrome, explorer
		console.log('chrome');			
		xhr = new XMLHttpRequest();
	}else{
		console.log('ajax 구현 불가 지원하지 않음');			
	}
	console.log('ready state : '+xhr.readyState);
	xhr.open('GET', filename, true);
	console.log('ready state : '+xhr.readyState);
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			if(xhr.status == 200){
				document.getElementById('msgTxt').innerHTML = xhr.responseText;
			}
		}
	}
	xhr.send(null);
}	
</script>
</head>
<body>
<input type="button" value="get text" onclick="load('msg.txt')"/>
<input type="button" value="get jsp" onclick="load('msg.jsp')"/>
<div id=msgTxt></div>
</body>
</html>