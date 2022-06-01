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
		<jsp:include page="../HeaderFooter/top.jsp"/>
	</div>
<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />
	
	
	<nav id="left_menu">
		<ul>
			<li><a href="AdminNoticeList.admin">Notice</a></li>
			<li><a href="admin_product.jsp">product</a></li>
			<li><a href="admin_member.jsp">member</a></li>
			<li><a href="AdminQNAList.admin">qna</a></li>
			<li><a href="Admin_reportList.admin">report</a></li>
		</ul>
	</nav>
	<article>
		<h2>신고 글 관리</h2>
		
		<table id="report">
			<tr>
				<th class="report_idx">No.</th>
				<th class="report_type">Classify</th>
				<th class="report_subject">Title</th>
				<th class="member_id">Write</th>
				<th class="report_date">Date</th>
				<th class="report_delete">delete</th> 

			</tr>
				
			<h1>~~~ report 리스트 좌라락~~~</h1>
			<c:if test="${not empty reportList && pageInfo.listCount > 0}">
				<c:forEach var="report" items="${reportList}">

					<tr>
						<td>${report.report_idx }</td>
						<td>
								${report.report_type }
						</td>
						<td id="subject"><a href="AdminReportView.admin?report_idx=${report.report_idx }&page=${pageNum}">${report.report_subject }</a></td>

						<td>${report.member_id }</td>

						<td>${report.report_date }</td>
					
					<td>
					<input type="button" onclick="location.href='AdminReportDeletePro.admin?report_idx=${report.getReport_idx() }'" value="삭제">
				</td>
				</tr>
				</c:forEach>
			</c:if>
			<tr>
			
			</tr>
		</table>
		<div id="table_search">
			<input type="button" value="글쓰기" class="btn"
				onclick="location.href='AdminReportWriteForm.admin'">

		</div>
	</article>

	<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1 }">
				<input type="button" value="이전"
					onclick="location.href='AdminReportList.admin?page=${pageNum - 1}'">
			</c:when>
			<c:otherwise>

				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum eq i }">
				${i }
			</c:when>
				<c:otherwise>

					<a href="AdminReportList.admin?page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 
		현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 다음 링크 동작
		-->
		<c:choose>
			<c:when test="${pageNum < maxPage}">
				<input type="button" value="다음"
					onclick="location.href='AdminReportList.admin?page=${pageNum + 1}'">
			</c:when>
			<c:otherwise>

				<input type="button" value="다음">&nbsp;
			</c:otherwise>
		</c:choose>
	</section>


	<div id="table_search">
		<form action="AdminReportSearch.admin" method="get">
			<select name="searchType">
				<option value="subject">제목</option>
				<option value="name">작성자</option>
			</select> <input type="text" name="search" class="input_box"> <input
				type="submit" value="Search" class="btn">
		</form>

	</div>
	
	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>