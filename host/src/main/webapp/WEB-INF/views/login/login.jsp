<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="resources/css/footer/footer.css">
    <link rel="stylesheet" href="resources/css/header/header.css">
    <link rel="stylesheet" href="resources/css/login/login.css">
</head>
<body>
    <div id="header">
        <div id="headerarea">
            <a href="/host/main.do">
                <img id="logo" src="resources/css/image/logo.png">
            </a>
            <form id="searchspace" action="검색.....">
                <input id="searchText" type="text" name="searchWord">
                <input id="searchIcon"  type="image" src="resources/css/image/icon/search-removebg.png">
            </form>
            <img id="menutab_buttom" src= "resources/css/image/icon/catalog.png" onclick="menutabOpen()">
            <div id="menutab">
                <img id="menutab_close" src="resources/css/image/icon/close.png" onclick="menutabClose()">
                <div id="login">
                	<c:choose>
                        <c:when test="${host_name == null}">
                            <a href="login.do">
                                <p id="loginOK">로그인 / 회원가입</p>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/host/mypage.do">
                                <p id="loginOK">${host_name}</p>
                            </a>
                            <a href="/host/logout.do">
                                <p id="logout" style="margin-left:10px">로그아웃</p>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <div id="body">
        <div id="bodyarea">
            <div id="loginBox">
                <form action="/host/loginOK.do" method="post"  onsubmit="return loginCheck()">
                    <div id="loginContentBox">
                        <p class="title">이용자 로그인</p>
                        <input class="loginContent" id="userID" name="email" type="text" placeholder="이메일을 입력해주세요.">
                        <input class="loginContent" id="userPW" name="pw" type="text" placeholder="비밀번호를 입력해주세요.">
                        <label class="submit_label" for="login_submit">로그인 </label>
                        <input type="submit" id="login_submit" hidden>
                        <p>워크토크 회원가입하기 <strong onclick="joinOpen()" id="strong_btn">회원가입</strong></p>
                    </div>
                </form>
            </div>
            <div id="joinBox"  style="display: none;">
                <img src="resources/css/image/icon/close.png" alt="close" id="close_img">
                <form action="/host/joinOK.do" method="post" onsubmit="return joinCheck()">
                    <p class="title">이용자 회원가입</p>
                    <div id="joinContentBox">
                        <div id="emailCheckBox">
                            <input type="text" class="emailIdInput inputBox" id="emailID" placeholder="이메일" maxlength="20" check_result="fail">
                            @
                            <select class="emailAdressInput inputBox" name="emailAdress" id="emailAdress" >
                                <option>naver.com</option>
                                <option>daum.net</option>
                                <option>gmail.com</option>
                                <option>hanmail.com</option>
                                <option>yahoo.co.kr</option>
                            </select>
                            <input type="text" id="user_email" name="email" value="" hidden>
                            <label for="mailCheckBtn" class="Check-Btn_label" id="mailCheck" check_result="fail">메일인증</label>
                            <button type="button" id="mailCheckBtn" hidden></button>
                        </div>
                        <div>
                            <input class="mail-check-input inputBox" id="mailCheckInput" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6" check_result="fail" hidden>
                            <span id="mail-check-warn"></span>
                        </div>
                        <div>
                            <input class="inputBox password" id="password" name="pw" type="password" placeholder="비밀번호" check_result="fail">
                            <span id="password-warn"></span>
                        </div>
                        <div>
                            <input class="inputBox password" id="passwordCheck" type="password" placeholder="비밀번호 확인">
                            <span id="passwordCheck-warn"></span>
                        </div>
                        <div>
                            <input class="inputBox" id="nameBox" name="name" type="text" placeholder="닉네임" maxlength="10" check_result="fail">
                            <label id="nameCheck" class="Check-Btn_label" for="nameCheckBtn">중복체크</label>
                            <span id="name-check-warn"></span>
                        </div>
                        <div>
                            <select class="inputBox telFirst">
                                <option>010</option>
                                <option>020</option>
                            </select>
                            -
                            <input class="inputBox telBox" type='number' id="tel_1" check_result="fail" oninput='handleOnInput(this, 4)'/>
                            -
                            <input class="inputBox telBox" type='number' id="tel_2" check_result="fail" oninput='handleOnInput(this, 4)'/>
                            <input type="text" name="tel" id="tel" value="" hidden>
                        </div>
                        <div>
                            <input type="number" name="role" value="1" hidden>
                            <label class="submit_label join_submit" for="join_submit" id="join_submit_btn">회원가입</label>
                            <input type="submit" id="join_submit" hidden>
                        </div>
                        <div id="test"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="footer">
        <div id="footer_area">
            <div>로고</div>
            <div>
                프로젝트명 : 워크토크
                팀원 : 이재석 이주희 최수연
                GIT : 주소....
            </div>
            <div>
                프로젝트이야기 : 노션?
            </div>
        </div>
    </div>
</body>
<script src="resources/js/header/header.js"></script>
<script src="resources/js/login/login.js"></script>
</html>