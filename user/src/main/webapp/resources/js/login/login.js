function loginCheck() {
    if($('#userID').val() == '') {
        alert('아이디를 입력해주세요');
        return false;
    };
    if($('#userPW').val() == '') {
        alert('비밀번호를 입력해주세요');
        return false;
    };
}
function joinOpen() {
    $('#joinBox').css('display','flex');
    $('#loginBox').css('display','none');
}
$('#close_img').click(function(){
    console.log('클릭');
    $('#loginBox').css('display','block');
    $('#joinBox').css('display','none');
});
// 번호 4자리로 막는 함수
function handleOnInput(el, maxlength) {
    if(el.value.length > maxlength)  {
        el.value 
        = el.value.substr(0, maxlength);
    }
}
// 이메일 중복 및 인증
$('#mailCheck').click(function() {
    console.log($('#emailID').val());
    if($('#emailID').val() == '') {
        return alert('메일을 작성해 주세요');
    }
    const email = $('#emailID').val()+'@'+$('#emailAdress').val();
    console.log('완성된 이메일 : ' + email);
    console.log($("#user_email").val());
    document.getElementById('user_email').setAttribute('value',`${email}`);
    const checkInput = $('.mail-check-input');
    console.log(checkInput);
    $.ajax({
        type : 'get',
        url : 'mailCheck.do?email='+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
        success : function (data) {
            console.log("data : " +  data);
            checkInput.attr('hidden',false);
            $('#emailID').attr('check_result','success');
            code =data;
            alert('작성하신 메일로 인증번호가 전송되었습니다.')
        }         
    }); // end ajax
}); // end send eamil
// 인증, 인증번호 입력창 출력
$('#mailCheckInput').blur(function () {
    const inputCode = $(this).val();
    console.log("this : " + inputCode);
    const $resultMsg = $('#mail-check-warn');
    
    if(inputCode === code){
        $resultMsg.html('인증완료');
        $resultMsg.css('color','green');
        $('#mailCheck').css('pointer-events','none');
        $('#mailCheck').attr('check_result','success');
        $('#emailID').attr('readonly',true);
        $('#emailAdress').attr('readonly',true);
        $('#emailAdress').attr('onFocus', 'this.initialSelect = this.selectedIndex');
        $('#emailAdress').attr('onChange', 'this.selectedIndex = this.initialSelect');
        $('#mailCheckInput').attr('check_result','success');
    }else{
        $resultMsg.html('인증번호 불일치');
        $resultMsg.css('color','red');
    }
});
// 비밀번호 확인
$('#password').blur(function() {
    const pw = $('#password').val();
    const pwcheck = $('#passwordCheck').val();
    if(pw != '') {
        if(pw == pwcheck) {
            $('#passwordCheck-warn').html('일치');
            $('#passwordCheck-warn').css({'color':'green', 'font-weight':'blod'});
            $('#password').attr('check_result','success');
        } else if(pw != pwcheck) {
            if(pwcheck != '') {
                $('#passwordCheck-warn').html('불일치');
                $('#passwordCheck-warn').css({'color':'red', 'font-weight':'blod'});
                $('#password').attr('check_result','fail');
            }
        }
    }
});
$('#passwordCheck').blur(function() {
    const pw = $('#password').val();
    const pwcheck = $('#passwordCheck').val();
    if(!pw) {
        $('#password').attr('placeholder','비밀번호를 입력해주세요.');
        $('#password-warn').html('미입력');
        $('#password-warn').css('color','red');
    }
    if(pw != '') {
        if(pw == pwcheck) {
            $('#passwordCheck-warn').html('일치');
            $('#passwordCheck-warn').css({'color':'green', 'font-weight':'blod'});
            $('#password').attr('check_result','success');
        } else if(pw != pwcheck) {
            $('#passwordCheck-warn').html('불일치');
            $('#passwordCheck-warn').css({'color':'red', 'font-weight':'blod'});
            $('#password').attr('check_result','fail');
        }
    }
});
$('#password').focus(function(){
    $('#password-warn').html('');
    $('#passwordCheck-warn').html('');
    $('#password').attr('placeholder','비밀번호');
})
$('#passwordCheck').focus(function(){
    $('#password-warn').html('');
    $('#passwordCheck-warn').html('');
})
// 닉네임 중복체크
$('#nameBox').keyup(function () {
  $('#name-check-warn').html('');
  $('#nameCheck').show();
  $('#nameBox').attr("check_result", "fail");
})
$('#nameCheck').click(function() {
	if ($('#nameBox').val() == "") {
        alert('닉네임을 입력해주세요.')
        return false;
    }
    const msg = $('#name-check-warn');
    $.ajax({
        type : 'post',
        data : {
            'name' : $('#nameBox').val(),
        },
        url : 'checkDuplicatedName.do',
        success : function(data){
            if(data == '1') {
                msg.html('사용중');
                msg.css('color','red');
            } else {
                $('#name-check-warn').html('사용가능');
                console.log(msg.val());
                msg.css('color','green');
                $('#nameBox').attr('check_result','success');
                $('#nameCheck').hide();
            }
        }
    });
})
$('#nameBox').click(function(){
    $('#name-check-warn').html('');
});
// 전화번호 입력체크
$('#tel_1').blur(function(){
    if($('#tel_1').val() != '') {
        $('#tel_1').attr('check_result','seccess');
    }
})
$('#tel_2').blur(function(){
    if($('#tel_2').val() != '') {
        $('#tel_2').attr('check_result','seccess');
    }
})
// 회원가입
function joinCheck() {
    if($('#emailID').val() == ''){
        $('#emailID').focus();
        alert('메일을 입력해주세요');
        return false;   
    }
    if($('#emailID').attr('check_result') == 'fail'){
        alert('메일인증을 해주세요');
        return false;   
    }
    if($('#mailCheck').attr('check_result') == 'fail'){
        $('#mailCheck').focus();
        alert('인증번호를 입력해주세요');
        return false;   
    }
    if($('#password').val() == ''){
        $('#password').focus();
        alert('비밀번호를 입력해주세요');
        return false;   
    }
    if($('#passwordCheck').val() == ''){
        $('#passwordCheck').focus();
        alert('비밀번호 확인을 입력해주세요');
        return false;   
    }
    if($('#passwordCheck-warn').html() == '불일치'){
        console.log($('#passwordCheck-warn').html())
        $('#password').focus(); 
        alert('비밀번호가 서로 다릅니다');
        return false;   
    }
    if($('#nameBox').val() == ''){
        $('#nameBox').focus();
        alert('닉네임을 입력해주세요');
        return false;   
    }
    if($('#nameBox').attr('check_result') == 'fail'){
        $('#nameBox').focus();
        alert('닉네임 중복체크를 해주세요');
        return false;   
    }
    if($('#tel_1').attr('check_result') == 'fail'){
        $('#tel_1').focus();
        alert('전화번호를 입력해주세요');
        return false;
    }
    if($('#tel_2').attr('check_result') == 'fail'){
        $('#tel_2').focus();
        alert('전화번호를 입력해주세요');
        return false;
    }
}