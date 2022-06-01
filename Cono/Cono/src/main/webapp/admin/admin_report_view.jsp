<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<%
String sId = (String) session.getAttribute("sId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="../HeaderFooter/top.jsp" />
	</div>

	<section id="articleForm">
		<h2>report 글 상세내용 보기</h2>
		<section id="basicInfoArea">
			<table border="1">
				
					<tr>
						<td>${report.report_idx }</td>

							<td>
								${report.report_type }
							</td>
					<th width="70">제 목</th>
					<td colspan="3">${report.report_subject }</td>
				</tr>
				<tr>
					<th width="70">작성자</th>
					<td>${sId }</td>
					<th width="70">작성일</th>
					<td>${report.report_date }</td>
				</tr>
			</table>
		</section>
		<section id="articleContentArea">${report.report_content}</section>
		
		<input type="button" value="목록" onclick="location.href='AdminReportList.admin'"> 
		<input type="button" value="수정" class="btn" onclick="location.href='AdminReportModifyForm.admin?report_idx=${report.getReport_idx() }&page=${param.page }'"> 
	</section>

	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp" />
	</div>
</body>
</html>