<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="wrap">
		<jsp:include page="../HeaderFooter/usertop.jsp"/>
	</div>
	
	<h1>팔로워</h1>
	<hr>
	<section>
		<h3>총 n 개의 상점</h3>
		<input type="text" placeholder="상점 이름 검색">
	</section>
	<section>
		<table border="1">
			<tr><td rowspan="2">logo</td><td>상점명</td><td rowspan="2">img1</td><td rowspan="2">img2</td><td rowspan="2">img3</td><td rowspan="2">하트</td></tr>
			<tr><td>소개글</td></tr>
		</table>
		<table border="1">
			<tr><td rowspan="2">logo</td><td>상점명</td><td rowspan="2">img1</td><td rowspan="2">img2</td><td rowspan="2">img3</td><td rowspan="2">하트</td></tr>
			<tr><td>소개글</td></tr>
		</table>
		<table border="1">
			<tr><td rowspan="2">logo</td><td>상점명</td><td rowspan="2">img1</td><td rowspan="2">img2</td><td rowspan="2">img3</td><td rowspan="2">하트</td></tr>
			<tr><td>소개글</td></tr>
		</table>
	</section>
	
	<div class="clear">
 		<jsp:include page="../HeaderFooter/bottom.jsp" />
	</div>
		
</body>
</html>