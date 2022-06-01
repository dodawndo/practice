<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String sId = (String)session.getAttribute("sId");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="../HeaderFooter/top.jsp"/>
	</div>

<article>
			<h1>Report Modify Page</h1>
			<form action="AdminReportModifyPro.admin" method="post">
			<input type ="hidden" name ="report_idx" value="<%=request.getParameter("report_idx") %>">
				<table id="report">
					<tr>
						<td>
					
					<select name="report_type">
						<option value="선택">선택</option>
						<option value="게시글신고">게시글신고</option>
						<option value="사용자신고">사용자신고</option>
					</select>
					</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="member_id" value = "<%=sId %>" readonly="readonly" required="required"></td>
					</tr>
					
					<tr>
						<td>제목</td>
						<td><input type="text" name="report_subject" required="required"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="10" cols="20" name="report_content" required="required"></textarea></td>
					</tr>

				</table>

				<div id="table_search">
					<input type="submit" value="수정" class="btn">
				</div>
			</form>
			<div class="clear"></div>
		</article>
		
	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>