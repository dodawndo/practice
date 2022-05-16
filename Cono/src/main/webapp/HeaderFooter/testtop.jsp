<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<%
String id = (String)session.getAttribute("sId"); 
%>    
<header>
	<div id="login">
	<%if(id == null) { %>
		<a href="../member/howjoin.jsp">회원가입</a> | <a href="../member/login.jsp">로그인</a> | <a href="../member/help.jsp">고객센터</a> | <a href = "../main/main.jsp"> 홈 </a>
	<%} else { %>	
		<a href="#"><%=id %>님</a> | <a href="../member/logout.jsp">logout</a> | <a href="../member/help.jsp">고객센터</a> | <a href = "../main/main.jsp"> 홈 </a>
  	<%if(id.equals("admin")) { %>
  			| <a href="../admin_center/main.jsp">관리자페이지</a>
	<%} else if(id.equals("seller")) { %>  			
			| <a href="../myshop/my_page.jsp">판매자페이지</a>
	<%} else if(id.equals("user")) { %>  			
			| <a href="../myPage/myPage.jsp">마이페이지</a>			
  		<%} %>
  	<%} %>
  	</div>
	<div class="clear"></div>
	<div id="logo">
		<a href="../main/main.jsp"><img src="../images/logo.gif"></a>
	</div>
	<nav id="top_menu">
		<ul>
			<li><a href="../search/hot.jsp">실시간 인기상품</a></li>
		</ul>
	</nav>
</header>




  	
  	