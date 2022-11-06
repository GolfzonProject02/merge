// menu Open Close 함수
function menutabOpen() {
    const menutab = document.getElementById('menutab');
    menutab.setAttribute('style','display:block');
    }
    function menutabClose(login) {
        const menutab = document.getElementById('menutab');
        menutab.setAttribute('style','display:none');
        if(login != null) {
            pageMove('@login/login.html');
        }
    }

// 예약상태
$(document).ready(function(){
    const reservationStatus = document.getElementsByClassName('reservationStatus');
    const reservationDate = document.getElementsByClassName('reservationDate');
    for (let i = 0; i < reservationStatus.length; i++) {
        let p = document.createElement("p");
        let text = reservationStatus[i].innerText
        let date = reservationDate[i].innerText
        
        if(text == "예약완료") {
            reservationStatus[i].setAttribute('style','background-color:rgb(159, 221, 143); color:white');
        } else if(text == "이용완료") {
            reservationStatus[i].setAttribute('style','background-color:rgb(143, 177, 221); color:white');
        } else if(text == "이용자취소") {
            reservationStatus[i].setAttribute('style','background-color:red; color:white');
            reservationDate[i].innerText = "취소일"+date.slice(3,-1)	;
        } else if(text == "호스트취소") {
            reservationStatus[i].setAttribute('style','background-color:red; color:white');
            reservationDate[i].innerText = "취소일"+date.slice(3,-1);
        }
        if(text == "예약완료") {
            p.setAttribute("class","reservationCancel");
            p.innerHTML = "예약취소";
            reservationStatus[i].after(p);
        }
    }
})


// 예약취소
$('.reservation').on('click', '.reservationCancel', function(){
    $(this).after(`
        <div id="reservation_cancel">
            <p>호스트 예약취소</p>
            <textarea name="cancel_reason" id="cancel_reason" placeholder="사유를 작성해주세요."></textarea>
            <button id="cancel_cancel">닫기</button>
            <button id="cancelOK_button">취소</button>
        </div>
    `)
    
})

$('.reservation').on('click','#cancel_cancel',function(){
    $('#reservation_cancel').detach();
})


$('.reservation').on('click','#cancelOK_button',function(){
    const close = $(this).parent();
    const reservationStatus = $(this).parent().parent().children('.reservationStatus');
    const r_date = $(this).parent().parent().children('.r_date').val();
    const r_num = $(this).parent().parent().children('.r_num').val();
    const reservationDate = $(this).parent().parent().children('.reservationDate');
    const cancel_reason = $(this).parent().children('#cancel_reason').val();
    console.log(
        "reservationStatus : "+reservationStatus+
        "r_date : "+r_date+
        "r_num : "+r_num+
        "reservationDate : "+reservationDate+
        "cancel_reason : "+cancel_reason
    );
	if(confirm('정말 취소하시겠습니까?')) {
    	close.detach();
    	console.log("여기");
        $.ajax({
            type : "post",
            url : '/host/reservation/cancel.do',
            data : {
            	r_num : r_num,
            	r_date : r_date,
            	cancel_reason : cancel_reason,
            },
            success : function(data) {
            	console.log(data);
            	console.log(data.r_date);
            	reservationDate.before(`
            		<p>${cancel_reason}</p>
            	`)
            	reservationDate.text("취소일 : "+data.r_date+" / 예약일 : "+data.r_start);
                reservationStatus.text("호스트취소");
                reservationStatus.css({'background-color':'red', 'color':'white'});
            }
        })
    }
})