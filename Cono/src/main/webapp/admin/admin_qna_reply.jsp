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

	<!-- 게시판 답변 -->
	<section id="replyForm">
		<h1>게시판 답변</h1>
		<form action="" name="boardForm" method = "post" >
<!-- 		<!-- input type = "hidden" 사용하여 글번호 (board_num)와 페이지번호(page) 전달 --> -->
<%-- 		<input type ="hidden" name="board_num" value="<%=article.getBoard_num()%>"> --%>
<%-- 		<input type ="hidden" name="page" value="<%=request.getParameter("page")%>"> --%>
<!-- 		<!-- 원본 글에 대한 답글 관련 정보를 담는 board_re_lev, board_re_seq 도 전달 --> -->
<%-- 		<input type ="hidden" name="board_re_ref" value="<%=article.getBoard_re_ref()%>"> --%>
<%-- 		<input type ="hidden" name="board_re_lev" value="<%=article.getBoard_re_lev()%>"> --%>
<%-- 		<input type ="hidden" name="board_re_seq" value="<%=article.getBoard_re_seq()%>"> --%>
			<table>
				<tr>
					<td class="td_left"><label for="board_name">관리자</label></td>
					<td class="td_right"><input type="text" name="board_name"  required="required" /></td>
				</tr>
				<!-- 답글 작성 시 원본글의 제목, 내용은 표시 -->
				<tr>
					<td class="td_left"><label for="board_subject">제목</label></td>
					<td class="td_right"><input type="text" name="board_subject" value="Re:윗글제목.. "required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내용</label></td>
					<td class="td_right">
						<textarea id="board_content" name="board_content" cols="40" rows="15" required="required">
						----- 원본 글 내용 ------
						윗글 내용 따오기
						</textarea>
					</td>
				</tr>
				
			</table>
			<section id="commandCell">
				<input type="submit" value="답글등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type ="button" value ="취소" onclick ="history.back()">  
			</section>
		</form>
	</section>
	
	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
	
</body>
</html>