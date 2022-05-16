<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>member/login.jsp</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<jsp:include page="../HeaderFooter/top.jsp"></jsp:include>
		  
		  <div id="sub_img_member"></div>
		  	<h1>로그인</h1>
		  	<h3>로그인이 필요한 서비스입니다.</h3>
		  	
		  	<h1>일단 로그인 버튼만 누르면 로그인 된걸로 가정함 ㅎ</h1>
		  	<br>
		  	간편로그인<br>
		  	<input type="button" value="카카오톡으로 로그인"><br>
		  	<input type="button" value="네이버로 로그인">
		  	<form action="loginPro.jsp" method="post" id="login">
		  		<fieldset>
		  			<legend>일반 로그인</legend>
		  			<input type="text" name="id" placeholder="Username"><br>
		  			<input type="password" name="pass" placeholder="password"><br>
		  			<a href="./forget.jsp">아이디 & 비밀번호를 잊어버리셨나요?</a>
		  		</fieldset>
		  			<input type="submit" value="로그인"><br>
		  			<input type="checkbox" name="1" value="저장" checked="checked">아이디 저장<br>
		  	</form>
		<jsp:include page="../HeaderFooter/bottom.jsp"></jsp:include>
	</div>
</body>
</html>


