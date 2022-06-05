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
<title>공지사항</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
	color: inherit;
}

.board_list_wrap {
	padding: 50px;
}

.board_list_head, .board_list_body .item {
	font-size: 0;
	padding: 10px 0;
}

.board_list_head {
	border-top: 3px solid black;
	border-bottom: 1px solid #ccc;
}

.board_list_body .item {
	border-bottom: 1px solid #ccc;
}

.board_list_head>div, .board_list_body .item>div {
	display: inline-block;
	text-align: center;
	font-size: 14px;
}

.board_list_head>div {
	font-weight: 600;
}

.board_list .num {
	width: 5%;
}

.board_list .report_type {
	width: 15%;
}
.board_list .title {
	width: 55%;
}

.board_list_body div.title {
	text-align: left;
}

.board_list_body div.title a:hover {
	text-decoration: underline;
}

.board_list .writer {
	width: 10%;
}

.board_list .date {
	width: 15%;
}

/*  paging  */
.paging {
	margin-top: 30px;
	text-align: center;
	margin-left: 10px;
}

.paging input:not(.btn) {
	width: 30px;
	height: 30px;
	background-repeat: no-repeat;
	border-radius: 100px;
	border-style: none;
	overflow: hidden;
	font-size: 13px;
	font-weight: bold;
}

.paging input:not(.btn):hover {
	background-color: #999;
}

.paging a:hover {
	text-decoration: underline;
}

/* 검색 창 */
.ssearch {
	position: relative;
	float: right;
	margin-right: 50px;
	margin-top: 4px;
}

.hh {
	position: relative;
	float: left;
	margin-left: 90px;
	margin-top: 4px;
	color: #17191d;
}

select {
	width: 65px;
	height: 30px;
	font-size: 13px;
	color: #999;
	border: 2px solid #bbb;
	border-radius: 3px;
}

input[type=text] {
	width: 180px;
	border: 1px solid #bbb;
	border-radius: 3px;
	padding: 8px 12px;
	font-size: 14px;
}

#search_btn {
	font-size: 13px;
	padding: 6px 7px;
	background-color: black;
	color: white;
	border: 2px solid black;
	border-radius: 25px;
	text-transform: uppercase;
	letter-spacing: 1px;
	font-family: sans-serif;
	transition: all 0.4s;
}

#search_btn:focus {
	outline: none;
}

#search_btn:hover {
	background-color: white;
	color: black;
}

.btn {
	background-image:
		url(../images/2199097_edit_draw_pen_pencil_write_icon.png);
	background-size: cover;
	float: right;
	width: 40px;
	height: 40px;
	background-repeat: no-repeat;
	border: 0;
	border-style: none;
	background-color: white;
	margin: -10px;
}

#btn {
	font-size: 13px;
	font-weight: bold;
	padding: 4px 7px;
	background-color: white;
	color: #9fa1a7;
	border: 2px solid #9fa1a7;
	border-radius: 2px;
	text-transform: uppercase;
	letter-spacing: 1px;
	font-family: sans-serif;
	transition: all 0.4s;
}

#btn:focus {
	outline: none;
}

#btn:hover {
	background-color: #9fa1a7;
	color: white;
}

</style>


</head>
<div>
	<jsp:include page="../HeaderFooter/top.jsp" />
</div>
<!-- 헤더 -->
<div>
	<jsp:include page="../admin_css/sidebar.jsp" />
</div>


<body>

	<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />


	<hr>
	<div class="body">
		<div class="search">
			<div class="hh">
				<h1>REPORT</h1>
			</div>
			<div class="ssearch">
				<form action="AdminReportSearch.admin" method="get">
					<select name="searchType">
						<option value="subject">제목</option>
						<option value="name">작성자</option>
					</select> <input type="text" name="search" placeholder="here"> 
					<input type="submit" value="Search" id="search_btn">
				</form>
			</div>
		</div>


		<div class="board_list_wrap">

			<div class="board_list">
				<div class="board_list_head">
					<div class="num">No.</div>
					<div class="report_type">Classify</div>
					<div class="title">Title</div>
					<div class="write">Write</div>
					<div class="date">Date</div>
					<div class="report_delete">delete</div>
				</div>

			

			<c:if test="${not empty reportList && pageInfo.listCount > 0}">
				<c:forEach var="report" items="${reportList}">

					<div class="board_list_body">
						<div class="item">
							<div class="num">${report.report_idx }</div>
							<div class="report_type">${report.report_type }</div>
							<div class="title">
								<a href="AdminReportView.admin?report_idx=${report.report_idx }&page=${pageNum}">${report.report_subject }</a>
							</div>

							<div class="write">${report.member_id }</div>

							<div class="date">${report.report_date }</div>

							<div class="report_delete">
								<input type="button" id ="btn" onclick="location.href='AdminReportDeletePro.admin?report_idx=${report.getReport_idx() }'"
									value="삭제">
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	

		<div class="paging">
			<c:choose>
				<c:when test="${pageNum > 1 }">
					<input type="button" value=" < " onclick="location.href='AdminReportList.admin?page=${pageNum - 1}'">
				</c:when>
				<c:otherwise>

					<input type="button" value=" < ">
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
			<c:choose>
				<c:when test="${pageNum < maxPage}">
					<input type="button" value=" > " onclick="location.href='AdminReportList.admin?page=${pageNum + 1}'">
				</c:when>
				<c:otherwise>

					<input type="button" value=" > ">&nbsp;
			</c:otherwise>
			</c:choose>

			<input type="button" value="" class="btn" onclick="location.href='AdminReportWriteForm.admin'">
		</div> <!-- 페이지디브 -->
	</div> <!--  리스트랩 디브 -->
	</div>

	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp" />
	</div>
</body>
</html>