<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myshop/my_page.jsp</title>
</head>
<body>
	<div>
		<!-- 상단 부분 -->
			<jsp:include page="../HeaderFooter/top.jsp"/>
	</div>
	
	<nav>
		<ul>
			<li><a href="my_page.jsp">내 상점</a></li>
			<li><a href="ItemUploadForm.shop">상품 등록</a></li>
			<li><a href="ItemMng.shop">상품 관리</a></li>
			<li><a href="item_review_mng.jsp">상품 후기 관리</a></li>
			<li><a href="item_order_mng.jsp">상품 주문 관리</a></li>
			<li><a href="../mypage/mypageList_follow.jsp">팔로우 상점</a></li>
		</ul>
	</nav>
	
	<hr>
	
	<div>
		<div id="img" style="width:100px;height:100px;border:1px solid black;">상점 이미지(보이게하려고 스타일 쫌 넣어둠)</div>
		<!-- 편집 누르면 상점 이름 변경 칸 생기면서 입력 가능 -->
		내 상점 이름<input type="button" value="편집(이미지)"><br>
		판매 횟수 x회<br>
		상품 갯수 x개<br>
		팔로워 x명<br>
		상점 오픈일 xx.xx.xx<br>
		상점 소개 내용<input type="button" value="편집(이미지)"><br>
	</div>
	
	<div>
		<!-- 하단 부분 -->
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>