<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="selectbox.css" />
<link rel="stylesheet" href="search.css" />
<link rel="stylesheet" href="table.css" />

<style type="text/css">

@import url('https://fonts.googleapis.com/css?family=Rubik&display=swap');

body{
   margin: 0;
}

.sidebar{
   position: fixed;
   width: 230px;
   top:0;
   left: 0;
   bottom: 0;
   background: #111;
   padding-top: 50px;
}


.sidebar h1{
   display: block;
   padding: 10px 20px;
   color: #fff;
   text-decoration: none;
   font-family: "Rubik";
   letter-spacing: 2px;
   font-weight: 400;
   margin: 0;
   font-size: 25px;
   text-transform: uppercase;
}

.sidebar a{
   display: block;
   padding: 10px 20px;
   color: #bbb;
   text-decoration: none;
   font-family: "Rubik";
   letter-spacing: 2px;
}

.sidebar a:hover{
   color: #fff;
   margin-left: 20px;
   transition: 0.4s;
}

.nav {
	letter-spacing: 2px;
	border-bottom: 2px solid #000000;
}

.nav a{
	margin-right:15px;
	text-decoration: none;
	color: inherit;
}

.body {
    margin-left: 300px;
    margin-right: 60px;
}

	
</style>
</head>
<body>
<!-- 사이드바 -->
	<div class="sidebar">
	   <h1>Cono</h1>
	   <a href="../admin_center/main.jsp">대시보드</a>
	   <a href="AdminDealList.admin">거래관리</a>
	   <a href="AdminMemberList.admin">회원관리</a>
	   <a href="">1:1 문의</a>
	   <a href="">공지관리</a>
	   <a href="">신고관리</a>
	</div>
	
<!-- 네비게이션	 -->
	<section id="nav">
		<div class="nav" align="right">
			<h5><a href="">관리자님 </a> &nbsp;
			<a href="">로그아웃</a></h5>
		</div>
	</section>
	

</body>
</html>