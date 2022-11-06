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

// 공간종류 선택
$('.space_category_item').click(function(){
    $('.space_category_item').css('background-color','white');
    console.log('여기')
    $(this).css('background-color','rgba(155, 170, 209, 0.459)');
    console.log($(this).attr("value"))
    const value = $(this).attr("value");
    console.log(value)
    $('#spaceType').attr("value",value);
    
})

// 글자수 세기
$('#spaceName').keyup(function(e){
    let content = $(this).val();
    $('#spaceName_textCount').css('color','black');
    if(content.length == 0 || content == "") {
        $('#spaceName_textCount').text('0/20자');
    } else {
        $('#spaceName_textCount').text(content.length+'/20자');
    }
    if(content.length > 20) {
        $(this).val(content.substring(0, 20));
        $('#spaceName_textCount').css('color','red');
    }
})
$('#spaceInfo').keyup(function(e){
    let content = $(this).val();
    $('#spaceInfo_textCount').css('color','black');
    if(content.length == 0 || content == "") {
        $('#spaceInfo_textCount').text('0/100자');
    } else {
        $('#spaceInfo_textCount').text(content.length+'/100자');
    }
    if(content.length > 100) {
        $(this).val(content.substring(0, 100));
        $('#spaceInfo_textCount').css('color','red');
    }
})

// 주소
// 수업소스
    // 우편번호 찾기 화면을 넣을 element
    let element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.                
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        let width = 300; //우편번호서비스가 들어갈 element의 width
        let height = 400; //우편번호서비스가 들어갈 element의 height
        let borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }

    // 사업자번호
    function phoneFormat(target) {
        // 특수문자 제거
        target.value = target.value.replace(/[^0-9]/g, "");
      
        const value = target.value.split("");
      
        const textArr = [
          // 첫번째 구간 (00 or 000)
          [0, value.length > 9 ? 3 : 2],
          // 두번째 구간 (000 or 0000)
          [0, value.length > 10 ? 4 : 3],
          // 남은 마지막 모든 숫자
          [0, 4]
        ];
      
        // 총 3번의 반복 ({2,3}) - ({3,4}) - ({4})
        target.value = textArr
          .map(function(v)  { 
              return value.splice(v[0], v[1]).join("") 
          })
          .filter(function(text) { 
               return text 
          })
          .join("-");
      }