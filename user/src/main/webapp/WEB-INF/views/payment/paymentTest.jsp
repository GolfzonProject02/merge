<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
 <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<script>
        var IMP = window.IMP; 
        IMP.init("imp38067385"); 
      
        var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;
        

        function requestPay() {
            IMP.request_pay({
                pg : 'kcp',
                pay_method : 'card',
                merchant_uid: "IMP"+makeMerchantUid, 
                r_num : '310',
                amount : 70000,
                email : 'user',
                name : '사용자',
                tel : '010',
            }, function (rsp) { // callback
            	console.log(rsp);
            	console.log(rsp.success);
            	if (rsp.success) {            // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
            		console.log("if...."+rsp.imp_uid);
            		console.log("if...."+rsp.merchant_uid);
            		$(function(){
            			console.log("jquery ready....");
            			$.ajax({
                         url: "payment_test_getResult.do", 
                         type: "POST",
                         data: {
                             imp_uid: rsp.imp_uid,            //결제 고유번호     
                             merchant_uid: rsp.merchant_uid   //주문번호
                         },
                         dataType:"json",
                         success:function(){
                        	 console.log("jquery ready....");
                         }
                     });
            		});
                    // jQuery로 HTTP 요청
//                     jQuery.ajax({
//                         url: "payment_test_getResult.do", 
//                         method: "POST",
//                         headers: { "Content-Type": "application/json" },
//                         data: {
//                             imp_uid: rsp.imp_uid,            //결제 고유번호     
//                             merchant_uid: rsp.merchant_uid   //주문번호
//                         }
//                     }).done(function (data) {
//                     	alert("결제에 성공했지라." );
//                     });
	             } else {
	               alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
	             }
            }
            );
        }
	</script>
<meta charset="UTF-8">
<title>Payment_Test</title>
</head>
<body>
	<h2>payment_test.jsp</h2>
	<button onclick="requestPay()">결제하기</button> <!-- 결제하기 버튼 생성 -->
</body>
</html>