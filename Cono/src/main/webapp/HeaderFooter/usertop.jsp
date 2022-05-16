<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<header>
	<div id="login">
		<a href="#">XXX님</a> | <a href="../member/logout.jsp">로그아웃</a> | <a href="../myPage/myPageList_alarm.jsp">알림</a> | <a href="../myPage/myPageList_dm.jsp">메세지</a> | <a href="../member/help.jsp">고객센터</a> | <a href = "../user/user.jsp"> 홈 </a><br>
		<input type="button" value="사용자 전용 마이페이지" onclick="location.href='../myPage/myPage.jsp'"><input type="button" value="판매자 전용 마이페이지" onclick="location.href='../myshop/my_page.jsp'"><input type="button" value="관리자 전용 마이페이지" onclick="location.href='../admin_center/main.jsp'">
	</div>
	<div class="clear"></div>
	<div id="logo">
		<a href="../user/user.jsp"><img src="../images/logo.gif"></a>
	</div>
	<nav id="top_menu">
		<ul>
			<li><a href="../search/hot.jsp">실시간 인기상품</a></li>
		</ul>
	</nav>
</header>