<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myshop/item_detail.jsp</title>
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
	
	<!-------- 본문 -------->
	<h3>상품 정보 상세</h3>
	<div>
		<hr>
		<form action="item_modify.jsp">
			<table border="1">
				<tr>
					<td colspan="2">
						<input type="submit" value="수정하기">
						<input type="button" value="삭제하기" onclick="location.href='item_delete.jsp'">
					</td>
				</tr>
				<tr>
					<th>상품 이미지</th>
					<td><!-- 파일 첨부 -->이미지파일</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>제목임</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						뭐시기 > 어떤거 > 이런거
					</td>
				</tr>
				<tr>
					<th>거래지역</th>
					<td><!-- 우편번호 API 사용 -->김해시 삼정동 (동까지만)</td>
				</tr>
				<tr>
					<th>가격</th>
					<td>100,000원</td>
				</tr>
				<tr>
					<th>상품 소개</th>
					<td>상 품 소 개~</td>
				</tr>
				<tr>
					<th>결제 방식</th>
					<td>
						코노페이
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	<!---------------------->
	
	<div>
		<!-- 하단 부분 -->
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>