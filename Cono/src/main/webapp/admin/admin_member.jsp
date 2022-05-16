<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	// 	회원 전체선택
	function checkAll(){
		all = document.getElementById("check_all");
		if(all.checked==false){
			for(let i=0; i<3;i++){
				document.getElementsByName("chk")[i].checked=false;
			}
		}
		if(all.checked==true){
			for(let i=0; i<3;i++){
				document.getElementsByName("chk")[i].checked=true;
			}
		}
	}
</script>
</head>
<body>
	<!-- 헤더 -->
	<div>
		<jsp:include page="../HeaderFooter/usertop.jsp"/>
	</div>

	
	<nav id="left_menu">
			<ul>
				<li><a href="admin_notice_list.jsp">Notice</a></li>
				<li><a href="admin_product.jsp">product</a></li>
				<li><a href="admin_member.jsp">member</a></li>
				<li><a href="admin_qna_list.jsp">qna</a></li>
				<li><a href="admin_report.jsp">report</a></li>
			</ul>
		</nav>
	<article>
		<h2>가입 회원 정보 조회</h2>
		
		<div id="member_search">
			<form action="admin_member_search.jsp" method="get">
				<select name="searchType">
					<option value="">회원등급</option>
					<option value="">회원등급</option>
					<option value="">회원등급</option>
				</select>
				<input type="text" name="member_search" class="input_box">
				<input type="submit" value="검색" class="btn">
			</form>		
		</div>
		<table border="1">
			<tr>
				<th width="50">
					All<input type="checkbox" name="check_all" id="check_all" onclick="checkAll()">
				</th>
				<th width="50">N O</th>
				<th width="100">아이디</th>
				<th width="100">이 름</th>
				<th width="100">닉네임</th>
				<th width="100">Cono 온도</th>
				<th width="150">Cono Coin</th>
				<th width="200">Phone</th>	
				<th width="200">E-Mail</th>
				<th width="150">가입일</th>
				<th width="300">메 모</th>
				<th width="100">관 리</th>
			</tr>
			<tr align="center">
				<td><input type="checkbox" name="chk"></td>
				<td>1</td>
				<td>roro</td>
				<td>김로로</td>
				<td>roro</td>
				<td>37`</td>
				<td>37,000 Cono</td>
				<td>010-1234-5678</td>
				<td>roro@naver.com</td>
				<td>2022-05-11</td>
				<td>
					<input type="text" id="member_memo" placeholder="회원 상세 메모">
					<button onclick="">저장</button>
				</td>
				<td><button onclick="">복원</button>
					<button onclick="">탈퇴</button>
				</td>
			</tr>
			<tr>
				<th colspan="12"><button onclick="location.href='../center/main.jsp'">메 인</button></th>
			</tr>
		</table>
		<!-- 페이징 처리 -->
		<div id="page_control">
			<a href="">Prev</a>
			<a href="">Next</a>
		</div>
	</article>
	
	<!-- 푸터 -->
	<div>
		<jsp:include page="../HeaderFooter/bottom.jsp"/>
	</div>
	
</body>
</html>