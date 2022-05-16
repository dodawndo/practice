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
		$("#fer").on({
			click: function() {
				$.ajax({
					type: "GET",
					url: "myPageList_follower_detail.jsp",
					success:function(msg) {
						$("#resultArea").html(msg);
					}
				});
			}
		});
		$("#fing").on({
			click: function() {
				$.ajax({
					type: "GET",
					url: "myPageList_following_detail.jsp",
					success:function(msg) {
						$("#resultArea").html(msg);
					}
				});
			}
		});
	});
</script>
</head>
<body>

	<div id="wrap">
		<jsp:include page="../HeaderFooter/usertop.jsp"/>
	</div>
	
	<section>
		<h1>팔로잉&팔로워</h1>
	</section>
	<section>
		<table>
			<tr><td><input type="button" id="fing" value="팔로잉"></td><td><input type="button" id="fer" value="팔로워"></td></tr>
		</table>	
	</section>
	<section>
		<div id="resultArea"></div>
	</section>
	
	<div class="clear">
 		<jsp:include page="../HeaderFooter/bottom.jsp" />
	</div>

</body>
</html>