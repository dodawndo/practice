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
		<jsp:include page="../HeaderFooter/usertop.jsp"/>
	</div>

<section id="articleForm">
		<h2>qna 글 상세내용 보기</h2>
		<section id="basicInfoArea">
			<table border="1">
			<tr><th width="70">제 목</th><td colspan="3" >판매자잠수탐</td></tr>
			<tr>
				<th width="70">작성자</th><td>구매자</td>
				<th width="70">작성일</th><td> 2022-05-11</td>
			</tr>
			</table>
		</section>
		<section id="articleContentArea">
		저희는 이렇게 했고 저렇게 했고 블라블라 개힘들고 졸리고 배고프다....ㅋ 오늘 점심은 맛있는걸 먹겠어
		</section>
		<input type="button" value="목록" onclick="location.href='admin_qna_list.jsp'">
		<input type="button" value="수정" class="btn" onclick="location.href='admin_qna_modify.jsp'">
		<input type="button" value="삭제" class="btn" onclick="location.href='admin_qna_delete.jsp'">
		<input type="button" value="답변" class="btn" onclick="location.href='admin_qna_reply.jsp'">
	</section>
	
	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>