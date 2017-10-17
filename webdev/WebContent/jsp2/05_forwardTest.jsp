<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
	<%
		// 서버 내부에서 값을 이동하는 것은 request.setAttribute()로 넘길 수 있다.
		// 값을 가져올 떄는 request.getAttribute()로 가져올 수 있다.

		int age = Integer.parseInt(request.getParameter("age"));
		if (age < 20) {
	%>
	<script type="text/javascript">
		alert("19세 미만이므로 출입 불가능")
		history.back()
	</script>
	<%
		} else {
			request.setAttribute("name", "JB");
			RequestDispatcher dispatcher = request.getRequestDispatcher("05_forwardResult.jsp");
			dispatcher.forward(request, response);
		}
	%>


</body>
</html>