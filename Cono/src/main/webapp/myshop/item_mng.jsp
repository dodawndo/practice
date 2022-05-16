<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myshop/item_mng.jsp</title>
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
		<h3>상품 관리</h3>
		<input type="text" name="search_keyword" placeholder="상품명 입력"><input type="button" value="검색">
		<select name="sell_status">
			<option selected="selected">전체</option>
			<option>판매 중</option>
			<option>판매 완료</option>
		</select>
		
		<table border="1">
			<tr>
				<th>상품명</th>
				<th>가격</th>
				<th>상품 등록일</th>
				<th>판매 상태</th>
				<th>찜</th>
				<th>조회수</th>
			</tr>
			<!--수정이 가능한 (판매자가 보는) 상품 상세 페이지로 이동 -->
			<tr height="200px" onclick="location.href='item_detail.jsp'">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	
	<div>
		<!-- 하단 부분 -->
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>