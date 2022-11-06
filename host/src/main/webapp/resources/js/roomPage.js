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

    // menu 로그인/회원가입 출력
    const login = 0;
    const login_tag = document.getElementById('login');
    if(login == 0) {
        login_tag.innerHTML = `<a href="login.do">
        <p>로그인 / 회원가입</p>
        </a>`;
    }else if(login == 1) {
        login_tag.innerHTML = 
            `<a href="프로필페이지">
                <img src="image/icon/profill.png">
                <p>admin</p>
            </a>`;
}

// 공간종류 선택 및 세부공간 변경
$('#space_category').ready(function(){
    const spaceType = $('#space_category').attr('value');
    if (spaceType == 1) {
        $('.desk').css('background-color','rgba(155, 170, 209, 0.459)');
        $('#room_type').prepend(`
            <option value="데스크">데스크</option>
        `)
    } else if (spaceType == 2) {
        $('.meetingRoom').css('background-color','rgba(155, 170, 209, 0.459)');
        $('#room_type').prepend(`
            <option value="회의실(4인)">회의실(4인)</option>
            <option value="회의실(6인)">회의실(6인)</option>
            <option value="회의실(8인)">회의실(8인)</option>
            <option value="회의실(20인)">회의실(20인)</option>
        `)
    } else if (spaceType == 3) {
        $('.office').css('background-color','rgba(155, 170, 209, 0.459)');
        $('#room_type').prepend(`
            <option value="오피스">오피스</option>
        `)
    }
})

$('#room_name').keydown(function(keyNum){
    if(keyNum.keyCode == 13) {
        $('#roomAddBtn').click();
    }
});

// 룸 입력공간 추가
$('#roomAddBtn').click(function(){
    console.log("추가");
    console.log($('#room_type').val());
    const room_type = $('#room_type').val();
    console.log($('#room_name').val());
    const room_name = $('#room_name').val();
    if(room_name == "") {
        return alert('공간이름을 작성해주세요');
    }
    let room_price = 0;
    if(room_type == "데스크") {
        room_price = 10000
    } else if(room_type == "회의실(4인)") {
        room_price = 20000
    } else if(room_type == "회의실(6인)") {
        room_price = 30000
    } else if(room_type == "회의실(8인)") {
        room_price = 50000
    } else if(room_type == "회의실(20인)") {
        room_price = 100000
    } else if(room_type == "오피스") {
        room_price = "가격을 입력해 주세요."
    }
    if(room_type == "오피스") {
        $('#roomAddSubmit').append(`
        <div class="roomInsert">
            <img src="../resources/css/image/icon/close.png" class="close">
            <div>
                <p>${room_name}</p>
                <input type="text" name="room_name" class="room_name" value="${room_name}" hidden>
                <p class="pricePTag">가격(하루당)</p>
                <input type="text" name="room_type" class="room_type" value="${room_type}">
                <input type="number" step="10000" name="room_price" class="room_price" value="" placehold="${room_price}">
            </div>
            <div class="work_time_setting">
                <p>운영시작시간</p>
                <select name="work_start" class="work_start">
                    <option value="0" selected hidden>00:00</option>
                    <option value="0">00:00</option><option value="1">01:00</option><option value="2">02:00</option><option value="3">03:00</option>
                    <option value="4">04:00</option><option value="5">05:00</option><option value="6">06:00</option>
                    <option value="7">07:00</option><option value="8">08:00</option><option value="9">09:00</option>
                    <option value="10">10:00</option><option value="11">11:00</option><option value="12">12:00</option>
                    <option value="13">13:00</option><option value="14">14:00</option><option value="15">15:00</option>
                    <option value="16">16:00</option><option value="17">17:00</option><option value="18">18:00</option>
                    <option value="19">19:00</option><option value="20">20:00</option><option value="21">21:00</option>
                    <option value="22">22:00</option><option value="23">23:00</option><option value="24">24:00</option>
                </select>
                <p>운영종료시간</p>
                <select name="work_end" class="work_end">
                    <option value="24" selected hidden>24:00</option>
                    <option value="0">00:00</option><option value="1">01:00</option><option value="2">02:00</option><option value="3">03:00</option>
                    <option value="4">04:00</option><option value="5">05:00</option><option value="6">06:00</option>
                    <option value="7">07:00</option><option value="8">08:00</option><option value="9">09:00</option>
                    <option value="10">10:00</option><option value="11">11:00</option><option value="12">12:00</option>
                    <option value="13">13:00</option><option value="14">14:00</option><option value="15">15:00</option>
                    <option value="16">16:00</option><option value="17">17:00</option><option value="18">18:00</option>
                    <option value="19">19:00</option><option value="20">20:00</option><option value="21">21:00</option>
                    <option value="22">22:00</option><option value="23">23:00</option><option value="24">24:00</option>
                </select>
            </div>
            <div>
                <p>세부공간설명</p>
                <p class="room_detailCount">0/250자</p>
                <textarea name="room_detail" class="room_detail" maxlength="250"></textarea>
            </div>
            <div>
                <p>공간사진</p>
                <input type="file" class="room_img">
            </div>
        </div>
        `)
    } else {
        $('#roomAddSubmit').append(`
        <div class="roomInsert">
            <img src="../resources/css/image/icon/close.png" class="close">
            <div>
                <p>${room_name}</p>
                <input type="text" name="room_name" class="room_name" value="${room_name}" hidden>
                <p class="pricePTag">가격(시간당)</p>
                <input type="text" name="room_type" class="room_type" value="${room_type}">
                <input type="number" name="room_price" class="room_price" value="${room_price}" readonly>
            </div>
            <div class="work_time_setting">
                <p>운영시작시간</p>
                <select name="work_start" class="work_start">
                    <option value="0" selected hidden>00:00</option>
                    <option value="0">00:00</option><option value="1">01:00</option><option value="2">02:00</option><option value="3">03:00</option>
                    <option value="4">04:00</option><option value="5">05:00</option><option value="6">06:00</option>
                    <option value="7">07:00</option><option value="8">08:00</option><option value="9">09:00</option>
                    <option value="10">10:00</option><option value="11">11:00</option><option value="12">12:00</option>
                    <option value="13">13:00</option><option value="14">14:00</option><option value="15">15:00</option>
                    <option value="16">16:00</option><option value="17">17:00</option><option value="18">18:00</option>
                    <option value="19">19:00</option><option value="20">20:00</option><option value="21">21:00</option>
                    <option value="22">22:00</option><option value="23">23:00</option><option value="24">24:00</option>
                </select>
                <p>운영종료시간</p>
                <select name="work_end" class="work_end">
                    <option value="24" selected hidden>24:00</option>
                    <option value="0">00:00</option><option value="1">01:00</option><option value="2">02:00</option><option value="3">03:00</option>
                    <option value="4">04:00</option><option value="5">05:00</option><option value="6">06:00</option>
                    <option value="7">07:00</option><option value="8">08:00</option><option value="9">09:00</option>
                    <option value="10">10:00</option><option value="11">11:00</option><option value="12">12:00</option>
                    <option value="13">13:00</option><option value="14">14:00</option><option value="15">15:00</option>
                    <option value="16">16:00</option><option value="17">17:00</option><option value="18">18:00</option>
                    <option value="19">19:00</option><option value="20">20:00</option><option value="21">21:00</option>
                    <option value="22">22:00</option><option value="23">23:00</option><option value="24">24:00</option>
                </select>
            </div>
            <div>
                <p>세부공간설명</p>
                <p class="room_detailCount">0/250자</p>
                <textarea name="room_detail" name="room_detail" class="room_detail" maxlength="250"></textarea>
            </div>
            <div>
                <p>공간사진</p>
                <input type="file" class="room_img" multiple>
            </div>
        </div>
        `)
    }
})

$('#roomAddSubmit').submit(function(event) {
    console.log("제출");
    let space_num = $("#space_num").val();
    let room_name = [];
    let room_type = [];
    let room_price = [];
    let room_detail = [];
    let work_start = [];
    let work_end = [];
    let room_img = [];

    $('input[name="room_name"]').each(function(i){
        room_name.push($(this).val());
        console.log("room_name : "+$(this).val());
    });
    $('input[name="room_type"]').each(function(i){
        room_type.push($(this).val());
        console.log("room_type : "+$(this).val());
    });
    $('input[name="room_price"]').each(function(i){
        room_price.push($(this).val());
        console.log("room_price : "+$(this).val());
    });
    $('textarea[name="room_detail"]').each(function(i){
        room_detail.push($(this).val());
        console.log("room_detail : "+$(this).val());
    });
    $('select[name="work_start"]').each(function(i){
        work_start.push($(this).val());
        console.log("work_start : "+$(this).val());
    });
    $('select[name="work_end"]').each(function(i){
        work_end.push($(this).val());
        console.log("work_end : "+$(this).val());
    });
    $('input[name="room_img"]').each(function(i){
        room_img.push($(this).val());
        console.log("room_img : "+$(this).val());
    });

    let multiroom = [];
    for (let i = 0; i < room_name.length; i++) {
        multiroom.push({
        	"space_num":space_num,
            "room_name":room_name[i],
            "room_type":room_type[i],
            "room_price":room_price[i],
            "room_detail":room_detail[i],
            "work_start":work_start[i],
            "work_end":work_end[i],
            "room_img":room_img[i],
        });
    }
    
    console.log(multiroom);
	console.log("여기");
    $.ajax({
         type : "post",
         url : "room_insertOK.do",
         dataType : "json",
         data : {multiroom : JSON.stringify(multiroom)},
         success : function() {
          	console.log("success");            
         }
    });
	return false;
});

// 입력 데이터 배열로 받기
// $('#roomAddSubmit').submit(function(event) {
    // $.ajax({
    //     type : "post",
    //     url : "insertOK",
    //     dataType : "json",
    //     data : objParams,
    //     success : function(data) {
    //         $(location).attr('href','/')
    //     }
    // })
// });

$('#bodyarea').on('change','.work_end',function(){
    let work_start = Number($(this).parent().children('.work_start').val());
    console.log(work_start);
    let work_end = Number($(this).val());
    console.log(work_end);
    if(work_start >= work_end) {
        alert('운영종료 시간이 시작시간과 같거나 작습니다.');
        $(this).val(work_start+1);
    }
})

// 입력창 삭제
$('#roomAddSubmit').on('click','.close',function(){
    $(this).parent().detach();
})

// 글자수 세기
$('#roomAddSubmit').on('keyup','.room_detail',function(e){
    console.log('글자입력중');
    const textCount = $(this).parent().children('.room_detailCount');
    let content = $(this).val();
    textCount.css('color','black');
    if(content.length == 0 || content == "") {
        textCount.text('0/250자');
    } else {
        textCount.text(content.length+'/250자');
    }
    if(content.length > 250) {
        $(this).val(content.substring(0, 250));
        textCount.css('color','red');
    }
});