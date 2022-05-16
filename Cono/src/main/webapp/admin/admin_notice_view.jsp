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
		<h2>글 상세내용 보기</h2>
		<section id="basicInfoArea">
			<table border="1">
			<tr><th width="70">제 목</th><td colspan="3" >우리 코노 협회에서는~~ 블라블라</td></tr>
			<tr>
				<th width="70">작성자</th><td>코노 관리자</td>
				<th width="70">작성일</th><td> 2022-05-11</td>
			</tr>
			</table>
		</section>
		<section id="articleContentArea">
		저희는 이렇게 했고 저렇게 했고 블라블라 개힘들고 졸리고 배고프다....ㅋ css 차이가 크구나 
		</section>
		<input type="button" value="목록" onclick="location.href='admin_notice_list.jsp'">
		<input type="button" value="수정" class="btn" onclick="location.href='admin_notice_modify.jsp'">
		<input type="button" value="삭제" class="btn" onclick="location.href='admin_notice_delete.jsp'">
	</section>
	
	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>