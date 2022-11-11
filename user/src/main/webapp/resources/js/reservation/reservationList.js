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
        } else if(text == "이용완료") {
            p.setAttribute("class","reviewInsert");
            p.innerHTML = "후기작성";
            reservationStatus[i].after(p);
        }
    }
})


// 예약취소
$('.reservation').on('click', '.reservationCancel', function(){
    $(this).after(`
        <div id="reservation_cancel">
            <p>이용자 예약취소</p>
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
            url : '/user/reservation/cancel.do',
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
                reservationStatus.text("이용자취소");
                reservationStatus.css({'background-color':'red', 'color':'white'});
            }
        })
    }
})

// 후기작성
$('.reservation').on('click','.reviewInsert',function(){
    $('#review_update_box').detach();
    let r_num = $(this).parent().children('.r_num').val();
    let room = $(this).parent().children('.grade_box').children('.user_review_room').text();
    let text = $(this).parent().children('.reviewText').text();
    let review_num = $(this).parent().children('.review_num').val();
    let grade = $(this).parent().children('.grade_box').children('.reviewGradeColor').attr('value');
    let input_radio = ""
    console.log(room,text,review_num,grade);
    $(this).after(`
        <div id="review_update_box">
            <p>후기작성</p>
    		<form action="http://localhost:8100/user/mypage/review_insert.do" method="post" enctype="multipart/form-data">
            <div class="grade_wrap">
                <div class="insert_grade_box">
                    <label for="grade_1" class="label_star" title="0.5"><span class="blind">0.5점</span></label>
                    <label for="grade_2" class="label_star" title="1"><span class="blind">1점</span></label>
                    <label for="grade_3" class="label_star" title="1.5"><span class="blind">1.5점</span></label>
                    <label for="grade_4" class="label_star" title="2"><span class="blind">2점</span></label>
                    <label for="grade_5" class="label_star" title="2.5"><span class="blind">2.5점</span></label>
                    <label for="grade_6" class="label_star" title="3"><span class="blind">3점</span></label>
                    <label for="grade_7" class="label_star" title="3.5"><span class="blind">3.5점</span></label>
                    <label for="grade_8" class="label_star" title="4"><span class="blind">4점</span></label>
                    <label for="grade_9" class="label_star" title="4.5"><span class="blind">4.5점</span></label>
                    <label for="grade_10" class="label_star" title="5"><span class="blind">5점</span></label>
                    <input type="radio" name="grade" value="0.5" id="grade_1" class="star_radio"/>
                    <input type="radio" name="grade" value="1" id="grade_2" class="star_radio"/>
                    <input type="radio" name="grade" value="1.5" id="grade_3" class="star_radio"/>
                    <input type="radio" name="grade" value="2" id="grade_4" class="star_radio"/>
                    <input type="radio" name="grade" value="2.5" id="grade_5" class="star_radio"/>
                    <input type="radio" name="grade" value="3" id="grade_6" class="star_radio"/>
                    <input type="radio" name="grade" value="3.5" id="grade_7" class="star_radio"/>
                    <input type="radio" name="grade" value="4" id="grade_8" class="star_radio"/>
                    <input type="radio" name="grade" value="4.5" id="grade_9" class="star_radio"/>
                    <input type="radio" name="grade" value="5" id="grade_10" class="star_radio"/>
                    <span class="grade_bg"></span>
                </div>
            </div>
            <textarea name="review" id="review_update_content"></textarea>
            <button id="review_update_close">닫기</button>
            <input type="file" name="multipartFile" id="imgname">
            <input type="submit" id="review_update_submit" value="작성">
            <input type="text' id="writer" name="writer" value="사용자" hidden>
            <input type="text" name="r_num" class="r_num" value="${r_num}" hidden>
        </form>
        </div>
    `)
    $('input:radio[name=grade]:input[value=' + grade + ']').attr("checked", true);
})

$('#review_selectAll').on('click','#review_update_close',function(){
    console.log("클릭");
    $('#review_update_box').detach();
})