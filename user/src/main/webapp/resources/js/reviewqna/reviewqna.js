let today = new Date();
let year = Number(today.getFullYear());
let month = Number(today.getMonth());
let date = Number(today.getDate());
let hours = Number(today.getHours());
let minutes = Number(today.getMinutes());
month = month + 1;
if (month < 10) {
    month = "0" + month;
    if (date < 10) {date = "0" + date;}
} else {
    if (date < 10) {date = "0" + date;}
}
if (hours < 10) {
    hours = "0" + hours;
    if (minutes < 10) {minutes = "0" + minutes;}
} else {
    if (minutes < 10) {minutes = "0" + minutes;}
}
today = year+"-"+month+"-"+date;
today_time = year+"-"+month+"-"+date+" "+hours+":"+minutes;
console.log("today : "+today);
console.log("today_time : "+today_time);

// menu Open Close 함수
function menutabOpen() {
    const menutab = document.getElementById('menutab');
    menutab.setAttribute('style','display:block');
    }
    function menutabClose(login) {
        const menutab = document.getElementById('menutab');
        menutab.setAttribute('style','display:none');
        if(login != null) {
            pageMove('login/login.html');
        }
    }

    // menu 로그인/회원가입 출력
    const login = 0;
    const login_tag = document.getElementById('login');
    if(login == 0) {
        login_tag.innerHTML = `<a href="login.html">
        <p>로그인 / 회원가입</p>
        </a>`;
    }else if(login == 1) {
        login_tag.innerHTML = 
            `<a href="프로필페이지">
                <img src="image/icon/profill.png">
                <p>admin</p>
            </a>`;
}

// sort
$('.sort_box').change(function(){
    let val = $(this).val();
    console.log(val);
    let url = "";
    if (val == "reservation") {
        url = "customercenter.do?reservation=" + val;
    } else if(val == "anser") {
        url = "customercenter.do?anser=" + val;
    } else {
        url = "customercenter.do?review_anser=" + val;
    }
    $.ajax({
        type : "get",
        url : url,
        success : function(data){
            $(location).attr('href',url);
        }
    })
})

// 별점...
$('.reviewGradeColor').ready(function(){
    const grade = document.getElementsByClassName('reviewGradeColor');
    const gradeAvg = document.getElementById('reviewsGradeAvg');
    for (let i = 0; i < grade.length; i++) {
        if(grade[i].getAttribute('value') == '0') {
            grade[i].setAttribute('style','width:0px;right:100px;');
        } else if(grade[i].getAttribute('value') == '0.5') {
            grade[i].setAttribute('style','width:10px;right:90px;');
        } else if(grade[i].getAttribute('value') == '1') {
            grade[i].setAttribute('style','width:20px;right:80px;');
        } else if(grade[i].getAttribute('value') == '1.5') {
            grade[i].setAttribute('style','width:30px;right:70px;');
        } else if(grade[i].getAttribute('value') == '2') {
            grade[i].setAttribute('style','width:40px;right:60px;');
        } else if(grade[i].getAttribute('value') == '2.5') {
            grade[i].setAttribute('style','width:50px;right:50px;');
        } else if(grade[i].getAttribute('value') == '3') {
            grade[i].setAttribute('style','width:60px;right:40px;');
        } else if(grade[i].getAttribute('value') == '3.5') {
            grade[i].setAttribute('style','width:70px;right:30px;');
        } else if(grade[i].getAttribute('value') == '4') {
            grade[i].setAttribute('style','width:80px;right:20px;');
        } else if(grade[i].getAttribute('value') == '4.5') {
            grade[i].setAttribute('style','width:90px;right:10px;');
        } else if(grade[i].getAttribute('value') == '5') {
            grade[i].setAttribute('style','width:100px;right:0px;');
        }
    }
})

// qna_update
$('.qna_updateBtn').click(function(){
    $('#qna_update_box').detach();
    let title = $(this).parent().children('.user_qna_title').text();
    let text = $(this).parent().children('.qnaText').text();
    let qna_num = $(this).parent().children('.qna_num').val();
    console.log(title,text,qna_num);
    $(this).after(`
        <div id="qna_update_box">
            <p>문의수정</p>
            <form action="qna_update.do" method="post" enctype="multipart/form-data">
                <input type="text" name="title" id="qna_update_title" value="${title}">
                <select name="type" id="qna_update_type">
                    <option value="예약">예약</option>
                    <option value="계정">계정</option>
                    <option value="결제">결제</option>
                </select>
                <textarea name="content" id="qna_update_content">${text}</textarea>
                <button id="qna_update_close">닫기</button>
                <input type="file" name="multipartFile" id="qna_imgname">
                <input type="submit" id="qna_update_submit" value="수정">
                <input type="text" name="qna_num" id="qna_num" value="${qna_num}" hidden>
                <input type="text' id="writer" name="writer" value="kim" hidden>
            </form>
        </div>
    `)
})

$('#qna_selectAll').on('click','#qna_update_close',function(){
    console.log("클릭");
    $('#qna_update_box').detach();
})

// review_update
$('.review_updateBtn').click(function(){
    $('#review_update_box').detach();
    let room = $(this).parent().children('.grade_box').children('.user_review_room').text();
    let text = $(this).parent().children('.reviewText').text();
    let review_num = $(this).parent().children('.review_num').val();
    let grade = $(this).parent().children('.grade_box').children('.reviewGradeColor').attr('value');
    let input_radio = ""
    console.log(room,text,review_num,grade);
    $(this).after(`
        <div id="review_update_box">
            <p>후기수정</p>
            <form action="review_update.do" method="get" enctype="multipart/form-data">
                <input type="text" name="title" id="review_update_title" value="${room}" readonly>
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
                <textarea name="content" id="review_update_content">${text}</textarea>
                <button id="review_update_close">닫기</button>
                <input type="file" name="multipartFile" id="review_imgname">
                <input type="submit" id="review_update_submit" value="수정">
                <input type="text" name="review_num" id="review_num" value="${review_num}" hidden>
                <input type="text' id="writer" name="writer" value="kim" hidden>
            </form>
        </div>
    `)
    $('input:radio[name=grade]:input[value=' + grade + ']').attr("checked", true);
})

$('#review_selectAll').on('click','#review_update_close',function(){
    console.log("클릭");
    $('#review_update_box').detach();
})

// delete
$('.review_deleteBtn').click(function(){
    let qna_num = $(this).parent().children('.review_num').val();
    if(confirm('정말 삭제하시겠습니까?')) {
        $.ajax({
            type : "get",
            url : "customercenter_delete.do?qna_num=" + qna_num,
            success : function(data){
                alert("삭제완료");
            }
        })
    }
})
$('.qna_deleteBtn').click(function(){
    let qna_num = $(this).parent().children('.qna_num').val();
    if(confirm('정말 삭제하시겠습니까?')) {
        $.ajax({
            type : "get",
            url : "customercenter_delete.do?qna_num=" + qna_num,
            success : function(data){
                alert("삭제완료");
            }
        })
    }
})

$('#pageTitle').click(function(){
    $('#qna_update_box').detach();
    $('#review_update_box').detach();
})

// 후기관리, QnA 관리 출력
console.log(document.location.pathname)
console.log(document.location.search)

$(window).ready(function(){
    let param = document.location.search;
    if (param == "") {
        $('#qna').attr('style','displaynone:none');
    } else {
        $('#review').attr('style','displaynone:none');
    }
})