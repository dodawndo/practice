<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myshop/item_qna_mng.jsp</title>
</head>
<body>
	<div id="wrap">
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
		<!-- ajax로 처리 -->
		답변 대기<br>
		답변 완료<br>
	</div>
	
	<div>
		<table border="1">
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>상품명</th>
				<th>문의유형</th>
				<th>등록일</th>
			</tr>
			<tr onclick="location.href='item_qna_detail.jsp'">
				<td>1</td>
				<td>사람1</td>
				<td>언제옴</td>
				<td>상품명111</td>
				<td>배송</td>
				<td>2022.05.22</td>
			</tr>
		</table>
	</div>
	<div>
		<!-- 하단 부분 -->
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>