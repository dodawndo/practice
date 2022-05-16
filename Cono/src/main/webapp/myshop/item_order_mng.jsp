<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myshop/item_order_mng.jsp</title>
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
	
	<hr>

	<h3>상품 주문 관리</h3>

	<div>
		<ul>
			<li onclick="location.href='item_order_mng.jsp'">전체 주문</li>
			<li onclick="location.href='item_order_mng_pay_status.jsp'">결제상태<br>(일반결제)</li>
			<li onclick="location.href='item_order_mng_delivery_status.jsp'">배송 상태</li>
			<li onclick="location.href='item_order_mng_cancel.jsp'">취소/환불 요청</li>
		</ul>
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<tr>
				<th>주문일시</th>
				<th>주문상품</th>
				<th>주문한 사람</th>
				<th>가격</th>
				<th>결제방식</th>
				<th>결제상태</th>
				<th>배송상태</th>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<div>
		<!-- 하단 부분 -->
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>