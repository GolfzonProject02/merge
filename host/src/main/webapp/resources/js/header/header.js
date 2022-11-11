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

$(document).ready(function(){
	let user = $('#loginOK').text();
    $('#login').after(`
        <div id="mypage_package">
            <div>
                <a href="http://localhost:8200/host/backoffice/reservation/findByName.do">
                <img src="resources/css/image/icon/reservation.png"><br>
                <p>예약리스트</p>
                </a>
            </div>
            <div>
                <a href="http://localhost:8200/host/backoffice/payment/findByName.do">
                <img src="resources/css/image/icon/qna.png"><br>
                <p>이용후기<br>Q&A 관리</p>
                </a>
            </div>
            <div>
                <a href="http://localhost:8200/host/backoffice/payment/findByName.do">
                <img src="resources/css/image/icon/payment.png"><br>
                <p>결제내역<br>마일리지</p>
                </a>
            </div>
        </div>
        <div id="menutab_bottom">
            <div class="menutab_bottom_item">
                <a href="1:1문의 페이지">
                    1:1문의
                </a>
            </div>
            <div class="menutab_bottom_item">
                <a href="공지사항 페이지">
                    공지사항
                </a>
            </div>
            <div class="menutab_bottom_item">
                <a href="이용가이드">
                    이용가이드
                </a>
            </div>
            <div class="menutab_bottom_item">
                <a href="워크토크 소개">
                    워크토크 소개
                </a>
            </div>
            <div class="menutab_bottom_item">
                <a>공급자페이지로 이동</a>
            </div>
        </div>
    `)
    let loginOK = $('#loginOK').text()
    console.log(loginOK)
    if (loginOK == "로그인 / 회원가입") {
        $('#mypage_package').attr('style','pointer-events: none;');
        $('#menutab_bottom').attr('style','pointer-events: none;');
        $('.host_page').attr('style','pointer-events: all;');
    }
})