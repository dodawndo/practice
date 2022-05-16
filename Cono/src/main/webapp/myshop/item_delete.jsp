<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<!-- 상단 부분 -->
		<jsp:include page="../HeaderFooter/usertop.jsp"/>
	</div>
	
	<nav>
		<ul>
			<li><a href="my_page.jsp">내 상점</a></li>
			<li><a href="item_upload.jsp">상품 등록</a></li>
			<li><a href="item_mng.jsp">상품 관리</a></li>
			<li><a href="item_qna_mng.jsp">상품 문의 관리</a></li>
			<li><a href="item_review_mng.jsp">상품 후기 관리</a></li>
			<li><a href="item_order_mng.jsp">상품 주문 관리</a></li>
			<li><a href="../myPage/myPageList_follow.jsp">팔로우 상점</a></li>
			<li><a href="my_qna_list.jsp">내가 쓴 문의 내역</a></li>
		</ul>
	</nav>
	
	<div>
		<h3>상품 삭제</h3>
		<hr>
		<form action="item_mng.jsp">
			<input type="text" name="id" placeholder="아이디를 입력하세요"><br>
			<input type="password" name="pass" placeholder="패스워드를 입력하세요"><br>
			<input type="submit" value="삭제하기" onclick="confirm('상품을 삭제 하시겠습니까?')">
		</form>
	</div>
	
	<div>
		<!-- 하단 부분 -->
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>