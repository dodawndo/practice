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
	
	<h3>상품 정보 수정</h3>
	<div>
		<hr>
		<form action="item_mng.jsp">
			<table border="1">
				<tr>
					<th>상품 이미지</th>
					<td><!-- 파일 첨부 --><img name="item_img"></img>이미지 파일</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="item_title" value="원래 제목"></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
					<!-- 원래 선택된 카테고리 selected -->
						<select name="item_category1">
							<option>1</option>
							<option>2</option>
							<option>3</option>
						</select>
						<select name="item_category2">
							<option>1</option>
							<option>2</option>
							<option>3</option>
						</select>
						<select name="item_category3">
							<option>1</option>
							<option>2</option>
							<option>3</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>거래지역</th>
					<td><!-- 우편번호 API 사용 -->우편 번호 API 사용 (원래 주소 출력)</td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" name="item_cost" value="원래 가격"> 원</td>
				</tr>
				<tr>
					<th>상품 소개</th>
					<td><textarea name="item_content">원래 소개</textarea></td>
				</tr>
				<tr>
					<th>결제 방식</th>
					<td>
						<select>
						<!-- 원래 선택된 방식 selected -->
							<option>만나서 현금 결제</option>
							<option>계좌 입금</option>
							<option selected="selected">코노페이</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정하기"></td>
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