<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("UTF-8");

String coin = request.getParameter("coin");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>코인페이지<%= coin %></h1>
	<table border="1">
		<tr><td colspan="2">사용가능한 코인<td><td><%= coin %></td></tr>
		<tr><td>적립 코인</td><td>사용내역</td><td>날짜</td><td>총 코인</td></tr>
		<tr><td>적립 코인</td><td>사용내역</td><td>날짜</td><td>총 코인</td></tr>
	</table>
</body>
</html>