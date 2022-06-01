<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String sId = (String)session.getAttribute("sId");
%>
<script type="text/javascript">
	function userCenterList() {
// 		let state = document.getElementById("userCenterList").style.visibility;	
// 		state = (state == "hidden") ? "visible" : "hidden";

		let userCenterList = document.getElementById("userCenterList");
		userCenterList.style.visibility = "visible";
	}
	// =========================양윤석==================================
	function confirmLogout() {
		if(confirm("로그아웃 하시겠습니까?")) {
			location.href = "./MemberLogout.func";
		}
	}
	// =========================양윤석==================================
</script>
	
<header>
	<span id="login">
		<span id=""><a href = "main.func"> 홈 </a>-------</span>
		<%if(sId == null) { %>
			<a href="MemberHowJoin.func">회원가입</a> | 
			<a href="MemberLogin.func">로그인</a> | 
		<%} else { %>
			<a href="MypagePass.mypage"><%=sId %>님</a>
			<a href="../mypage/mypageList_alarm.jsp">알림</a> | 
			<a href="../mypage/mypageList_dm.jsp">메세지</a> | 
			<input type="button" value="판매자 전용 마이페이지" onclick="location.href='Myshop.shop'">
			<input type="button" value="관리자 전용 마이페이지" onclick="location.href='./admin_center/main.jsp'">
			<!-- 			==============================양윤석============================= -->
			<a href="javascript:void(0)" onclick="confirmLogout()">로그아웃</a>
<!-- 			==============================양윤석============================= -->
		<%}%>
		<button id="userCenter" onclick="userCenterList()">고객센터</button>
		<span id="userCenterList"  style="visibility: hidden">
			<a href="../only_member/user_notice_list.jsp">공지사항</a>
			<a href="../only_member/user_chat_bot.jsp">문의하기</a>
			<a href="../only_member/user_report_write.jsp">신고하기</a>
		</span>
		
		
		
	</span>
	<nav id="top_menu">
		<ul>
			<li><a href="../search/hot.jsp">실시간 인기상품</a></li>
		</ul>
	</nav>
</header>