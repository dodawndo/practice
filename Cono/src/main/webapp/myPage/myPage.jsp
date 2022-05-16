<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function() {
		$(".coin").on({
			click: function() {
				$.ajax({
					type:"GET",
					url: "myPage_coin.jsp",
					data: {
						coin : $(".coin").val()
					},
					dataType: "text",
					success: function(msg) {
						$("#resultArea").html(msg);
					}
				});
			},

		});	
		$(".reserved_item").on("click", function() {
			$.ajax({
				type: "GET",
				url: "myPage_reservedItem.jsp",
				data: {
					quantity : $(".reserved_item").val()
				},
				dataType:"text",
				success: function(msg) {
					$("#resultArea").html(msg);
				}
			});
		});
		
		$(".purchased_item").on("click", function() {
			$.ajax({
				type: "GET",
				url: "myPage_purchasedItem.jsp",
				data: {
					quantity : $(".purchased_item").val()
				},
				dataType:"text",
				success: function(msg) {
					$("#resultArea").html(msg);
				}
			});
		});
		$(".basket").on("click", function() {
			$.ajax({
				type: "GET",
				url: "myPage_basket.jsp",
				data: {
					quantity : $(".basket").val()
				},
				dataType:"text",
				success: function(msg) {
					$("#resultArea").html(msg);
				}
			});
		});
		$(".interested_item").on("click", function() {
			$.ajax({
				type: "GET",
				url: "myPage_interestedItem.jsp",
				data: {
					quantity : $(".interested_item").val()
				},
				dataType:"text",
				success: function(msg) {
					$("#resultArea").html(msg);
				}
			});
		});
		$(".coupon").on("click", function() {
			$.ajax({
				type: "GET",
				url: "myPage_coupon.jsp",
				data: {
					quantity : $(".coupon").val()
				},
				dataType:"text",
				success: function(msg) {
					$("#resultArea").html(msg);
				}
			});
		});
		
	});
</script>
</head>
<body>

	<div id="wrap">
		<jsp:include page="../HeaderFooter/usertop.jsp"/>
	</div>
	
<nav>
	<div class="myPage_profile">
		<div class="myPage_profile_div_img">
			<img src="" class="myPage_profile_img">img 들어감
		</div>
		<div class="myPage_profile_div_myIntro">
			<span class="myPage_profile_nickname">닉네임</span>
			<span class="myPage_profile_myIntro">자기소개글</span>
		</div>
	</div>
	<div>
		<table border="1">
			<tr><th>나의 코노</th></tr>
			<tr><td><a href="myPageList_recentView.jsp">최근 본 상품</a></td></tr>
			<tr><td><a href="myPageList_follow.jsp">팔로잉 & 팔로워</a></td></tr>
			<tr><td><a href="myPageList_myReview.jsp">나의 후기</a></td></tr>
			
			<tr><th>알림 및 메세지</th></tr>
			<tr><td><a href="myPageList_alarm.jsp">알림</a></td></tr>
			<tr><td><a href="myPageList_dm.jsp">메세지</a></td></tr>
			
			<tr><th>내 정보</th></tr>
			<tr><td><a href="myPageList_modifyId.jsp">회원 정보 수정</a></td></tr>
			<tr><td><a href="myPageList_account.jsp">계좌 정보 관리</a></td></tr>
			<tr><td><a href="myPageList_deleteId.jsp">회원 탈퇴</a></td></tr>
		
		</table>
	</div>
</nav>

<br>

<nav>
	<section class="myPage_info">
		<table border="1">
			<tr><th colspan="3">내 정보</th></tr>
			<tr>
				<td>
					코인<hr>
<!-- 					<a href="" class="coin">1</a> -->
					<input type="button" class="coin" value="1">
				</td>
				<td>
					예약 중인 상품<hr>
					<input type="button" class=reserved_item value="2">
				</td>
				<td>
					구매완료<hr>
					<input type="button" class="purchased_item" value="3">
				</td>
			</tr>
			<tr>
				<td>
					장바구니<hr>
					<input type="button" class="basket" value="4">
				</td>
				<td>
					찜한상품<hr>
					<input type="button" class="interested_item" value="5">
				</td>
				<td>
					쿠폰<hr>
					<input type="button" class="coupon" value="6">
				</td>
			</tr>
		</table>
	</section>
	<section class="myPage_info_detail">
		
	</section>
</nav>
<div id="resultArea"></div>

	<div class="clear">
 		<jsp:include page="../HeaderFooter/bottom.jsp" />
	</div>

</body>
</html>