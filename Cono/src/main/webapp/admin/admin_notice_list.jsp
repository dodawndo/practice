<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_notice.jsp</title>
</head>
	<div>
		<jsp:include page="../HeaderFooter/usertop.jsp"/>
	</div>


<body>
<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
<c:set var="startPage" value="${pageInfo.getStartPage() }" />
<c:set var="endPage" value="${pageInfo.getEndPage() }" />
<c:set var="listCount" value="${pageInfo.getListCount() }" />
		<nav id="left_menu">
			<ul>
				<li><a href="admin_notice_list.jsp">Notice</a></li>
				<li><a href="admin_product.jsp">product</a></li>
				<li><a href="admin_member.jsp">member</a></li>
				<li><a href="admin_qna_list.jsp">qna</a></li>
				<li><a href="admin_report.jsp">report</a></li>
			</ul>
		</nav>
		<hr>
		<article>
			<h1>admin_notice_list</h1>
			<table id="notice">
				<tr>
			 		<th class="admin_no">No.</th> 
					<th class="admin_title">Title</th>
					<th class="admin_write">Write</th>
					<th class="admin_date">Date</th>
					
				</tr>
				
				<h1> ~~~공지사항 리스트 좌라락~~~</h1>
			<c:if test="${not empty noticeList && pageInfo.listCount > 0}">
			<c:forEach var = "notice" items = "${noticeList}">
					<tr><td>${notice.getNotice_idx() }</td>
					<td id="subject">
						<a href="admin_notice_view.admin?notice_idx=${notice.getNotice_idx() }&page=${pageNum}">${board.getBoard_subject() } </a></td><td>코노관리자</td><td>2022-05-11</td></tr>
					<tr><td> 1 <td> 코노협회에서는~~~</td><td>코노관리자</td><td>2022-05-11</td></tr>
					<tr><td> 1 <td> 코노협회에서는~~~</td><td>코노관리자</td><td>2022-05-11</td></tr>
					<tr><td> 1 <td> 코노협회에서는~~~</td><td>코노관리자</td><td>2022-05-11</td></tr>
					<tr><td> 1 <td> 코노협회에서는~~~</td><td>코노관리자</td><td>2022-05-11</td></tr>
			</c:forEach>
				</c:if>
			</table>
			<div id="table_search">
				<input type="button" value="글쓰기" class="btn" onclick="location.href='admin_notice_write.jsp'">
				
			</div>
			</article>
			<div id="table_search">
				<form action="admin_notice_search.jsp" method="get">
					<select name="searchType">
						<option value="subject">제목</option>
						<option value="name">작성자</option>
					</select>
					<input type="text" name="search" class="input_box">
					<input type="submit" value="Search" class="btn">
				</form>
				
			</div>

			


		<hr>
		
	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
</body>
</html>


