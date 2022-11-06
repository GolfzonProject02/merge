<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

<script>
	function iamport(){
		//가맹점 식별코드
		var IMP = window.IMP; 
        IMP.init("imp38067385");
        
// 		var today = new Date();
// 		var year = today.getFullYear();
// 		var month = today.getMonth();
// 		var date = today.getDate();
//         var hours = today.getHours(); // 시
//         var minutes = today.getMinutes();  // 분
//         var seconds = today.getSeconds();  // 초
//         var p_date = year+'/'+month+'/'+date+' '+hours+':'+minutes+':'+ seconds;
        
        var today = new Date();

        var year = today.getFullYear();
        var month = ('0' + (today.getMonth() + 1)).slice(-2);
        var day = ('0' + today.getDate()).slice(-2);

        var dateString = year + '/' + month  + '/' + day;
        
        var hours = ('0' + today.getHours()).slice(-2); 
        var minutes = ('0' + today.getMinutes()).slice(-2);
        var seconds = ('0' + today.getSeconds()).slice(-2); 

        var timeString = hours + ':' + minutes  + ':' + seconds;
        
        
        var p_date = dateString+' '+timeString;        
		
		IMP.request_pay({
		    pg : 'kcp',
		    pay_method : 'card',
		    merchant_uid : "merchant"+p_date,
		    name : 280 , //결제창에서 보여질 이름
		    amount : 100, //실제 결제되는 가격
		    buyer_email : 'user',
		    buyer_name : '사용자',
		    buyer_tel : '010',
		    buyer_addr : '서울 강남구 도곡동',
		    buyer_postcode : '123-456',
		}, function(rsp) {
			console.log(rsp);
			// 결제검증
			$.ajax({
// 	        	url : "http://localhost:8100/user/payment/verify/" + rsp.imp_uid, 
	        	url : "http://localhost:8100/user/payment/verify", 
	        	type : "POST",
	        	data: {
                    imp_uid: rsp.imp_uid,            //결제 고유번호     
                    merchant_uid: rsp.merchant_uid   //주문번호
                    
                },
                dataType:"json"
	        }).done(function(data) {
	        	
	        	console.log(data);
	        	
	        	// 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
	        	if(rsp.paid_amount == data.response.amount){
		        	alert("결제 및 결제검증완료");
		        	
		        	$(function(){
            			console.log("jquery OK....");
            			$.ajax({
                         url: "http://localhost:8100/user/reservation/reserve.do", 
                         type: "POST",
                         data: {
							imp_uid: rsp.imp_uid,            
							merchant_uid: rsp.merchant_uid,   
							p_name : rsp.buyer_name,
							p_amount : rsp.paid_amount,
							p_status : 1, // 0 for deposit, 1 for prepaid, 2 for postpaid, 3 for deposit 
							p_date : rsp.paidAt,
							
							name: rsp.buyer_name,
							room_num : rsp.name,
							space_num : 4,
							r_start : '2022/11/12 09:00',
							r_end : '2022/11/12 15:00',
							amount : rsp.paid_amount,
							r_date : rsp.merchant_uid,
							paid : 1 // 0 for deposit, 1 for prepaid
                         },
                         dataType:"json",
                         success:function(){
                        	 console.log("jquery success....");
                         }
                     });
            		});
		        	
	        	} else {
	        		alert("결제 실패");
	        		$(function(){
            			console.log("jquery Not OK....");
            			$.ajax({
                        	url: "http://localhost:8100/user/payment/cancel/" + rsp.imp_uid, 
                        	type: "POST",
                        	data: {
                            	imp_uid: rsp.imp_uid,            //결제 고유번호     
                            	merchant_uid: rsp.merchant_uid   //주문번호
                             
                       		},
                         	dataType:"json",
                         	success:function(){
                        		console.log("jquery success....");
                         	}
                     	});
            		});
	        	}
	        });
		});
	}
</script>

<meta charset="UTF-8">
<title>Sample Payment</title>
</head>
<body>
	<button onclick="iamport()">검증하기</button>
	<!-- 결제하기 버튼 생성 -->
</body>
</html>