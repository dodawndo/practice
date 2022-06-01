<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
	padding : 10px 0;
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

.board_list_head>div{
	font-weight: 600;
}

.board_list .num {
	width : 10%;
}
.board_list .title {
	width : 55%;
}

.board_list_body div.title {
	text-align: left;
}

.board_list_body div.title a:hover {
	text-decoration: underline;
}

.board_list .writer {
	width : 10%;
}
.board_list .date {
	width : 15%;
}


/*  paging  */

  .paging  {  
  	margin-top: 30px;  
   	text-align: center;   
  	margin-left: 10px;
  }  


  .paging input{ 
    width: 30px;  
   	height: 30px;   
   	background-repeat:no-repeat; 
   	border-radius:100px;  
   	border-style: none;  
    overflow: hidden;   
    
  	
  } 
  
  .paging input:hover{
  	background-color: #999;
  } 
  
  .paging a:hover{
  	text-decoration: underline;
  }

/* 검색 창 */


.search {
 	position:relative; 
 	float: right; 
 	margin-right: 50px; 
 	margin-top: 4px; 
}

select {
	width:65px;
	height :30px;
	font-size: 13px;
	color:#999;
	border:2px solid #bbb;
	border-radius:25px;
}


input[type=text] { 
 	width:180px; 
 	border:1px solid #bbb; 
 	border-radius: 14px; 
 	padding: 10px 12px;  	
 	font-size: 14px; 
 } 
#search_btn{
	font-size: 13px;
 	padding: 7px 7px; 
	background-color: black;
	color: white;
	border : 2px solid black;
	border-radius: 25px; 
 	text-transform: uppercase; 
 	letter-spacing:1px;
  	font-family: sans-serif;  
	transition : all 0.4s;
}

#search_btn:focus{
	outline: none;
}

#search_btn:hover{
	background-color: white;
	color:black;
}


</style>


</head>
<div>
	<jsp:include page="../HeaderFooter/top.jsp" />
</div>


<body>

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
			<li><a href="AdminReportList.admin">report</a></li>
		</ul>
	</nav>
	<hr>
	
	<div class ="search">
		<form action="AdminNoticeSearch.admin" method="get">
			<select name="searchType">
				<option value="subject">제목</option>
				<option value="name">작성자</option>
			</select> 
			<input type="text" name="search" placeholder="here" > 
			<input type="submit" value="Search" id="search_btn" >
		</form>
	</div>
		
		
		<div class="board_list_wrap">
		
			<div class="board_list">
				<div class="board_list_head">
					<div class="num">번호</div>
					<div class="title">제목</div>
					<div class="writer">작성자</div>
					<div class="date">작성일</div>
				</div>
				
				<c:if test="${not empty noticeList && pageInfo.listCount > 0}">
					<c:forEach var="notice" items="${noticeList}">
						<div class="board_list_body">
							<div class="item">
								<div class="num">${notice.getNotice_idx() }</div>
								<div class="title"><a href="AdminNoticeView.admin?notice_idx=${notice.getNotice_idx() }&page=${pageNum}">${notice.getNotice_subject() }
								</a></div>
								<div class="writer">${notice.getAdmin_id() }</div>
								<div class="date">${notice.getNotice_date() }</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
			
		

	<div class ="paging">
		<c:choose>
			<c:when test="${pageNum > 1 }">
				<input type="button" value=" < " onclick="location.href='AdminNoticeList.admin?page=${pageNum - 1}'">
			</c:when>
			<c:otherwise>

				<input type="button" value=" < ">&nbsp;
			</c:otherwise>
		</c:choose>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum eq i }">
				${i }
			</c:when>
				<c:otherwise>
					<a href="AdminNoticeList.admin?page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	<c:choose>
			<c:when test="${pageNum < maxPage}">
				<input type="button" value=" > "  onclick="location.href='AdminNoticeList.admin?page=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value=" > " >&nbsp;
			</c:otherwise>
		</c:choose>
		</div>
	
	
		<div class ="writer">
			<input type="button" value="글쓰기" class="btn" onclick="location.href='AdminWriteForm.admin'" > <i class="fa-thin fa-pen-line"></i>
		</div>
	</div>	
	<hr>

	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp" />
	</div>
</body>
</html>


