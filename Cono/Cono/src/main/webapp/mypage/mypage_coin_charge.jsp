<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script>
  var IMP = window.IMP; 
  IMP.init('imp58939464'); // 아임포트 가맹점 식별코드
    function requestPay1() {
      // IMP.request_pay(param, callback) 결제창 호출
      
      var price = document.getElementById("se");
      
      IMP.request_pay({ // param
      
          pg: "kakao",
          pay_method: "card",
          merchant_uid: 'merchant_' + new Date().getTime(),
          name: "CONO coin",
          amount: price.options[price.selectedIndex].value,
          buyer_email: "yys0507@naver.com",
          buyer_name: "양윤석",
      }, function (rsp) { // callback
          if (rsp.success) {
        	  var msg = '결제가 완료되었습니다.';
        	  updatePoint1.submit();
              //결제가 완료되면 처리될 함수
          } else {
        	  var msg = '결제에 실패하였습니다.';  
          }
      alert(msg);
      });
    }
</script>
<body>
<h1>코인페이지</h1>
	<select id="se">
		<option value="1000" selected>1000</option>
		<option value="2000">2000</option>
		<option value="3000">3000</option>
		<option value="5000">5000</option>
		<option value="10000">10000</option>
		<option value="20000">20000</option>
		<option value="30000">30000</option>
		<option value="50000">50000</option>
	</select>원
<div class="btn-group" ></div>
<form method="post" name="updatePoint1">
<input type="hidden" name="m_id"  value="${sessionScope.loginId}"> 
<input type="hidden" name="m_point" value="100000">
<button class="btn btn-outline-danger" type="button" onclick="requestPay1()">충전</button>
</form>
</body>