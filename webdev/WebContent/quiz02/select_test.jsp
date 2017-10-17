<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function what() {
	alert(document.myform.you.value);
}

function what_month() {
	alert(document.monthform.month.value + "월");
}
</script>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
<form name="myform">
<select name="you" multiple="multiple" onchange="what()">
	<option value="1">가위</option>
	<option value="2" selected>바위</option>
	<option value="3">보</option>
</select><br/>
</form>

<form name="monthform">
<select name="month" onchange="what_month()">
<%  
	int month = 6;
	for(int m=1; m<=12; m++){ %>
	<option <%= m==month ? " selected ":"" %>><%=m %></option>
<%	} %>
</select>
</form>
</body>
</html>