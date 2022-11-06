<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워크토크</title>
    <link rel="stylesheet" href="resources/css/mypage/profill.css">
    <link rel="stylesheet" href="resources/css/header/header.css">
    <link rel="stylesheet" href="resources/css/footer/footer.css">
    <link rel="stylesheet" href="resources/css/home/home.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <div id="header">
        <div id="headerarea">
            <a href="main.html">
                <img id="logo" src="resources/css/image/logo.png">
            </a>
            <form id="searchspace" action="검색.....">
                <input id="searchText" type="text" name="searchWord">
                <input id="searchIcon"  type="image" src="resources/css/image/icon/search-removebg.png">
            </form>
            <img id="menutab_buttom" src="resources/css/image/icon/catalog.png" onclick="menutabOpen()">
            <div id="menutab">
                <img id="menutab_close" src="resources/css/image/icon/close.png" onclick="menutabClose()">
                <div id="login">
                </div>
                <div id="mypage_package">
                    <div>
                        <a href="예약리스트 페이지">
                        <img src="resources/css/image/icon/reservation.png"><br>
                        <p>예약리스트</p>
                        </a>
                    </div>
                    <div>
                        <a href="이용후기 Q&A관리 페이지">
                        <img src="resources/css/image/icon/qna.png"><br>
                        <p>이용후기<br>Q&A 관리</p>
                        </a>
                    </div>
                    <div>
                        <a href="결제내역 마일리지 페이지">
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
            </div>
        </div>
    </div>
    <div id="body">
        <div id="bodyarea">
            <p id="pofill_title">프로필 관리</p>
            <div id="profill_contain">
                <div class="profill_img_box">
                    <img src="resources/css/image/icon/${Host_profile.imgname}" id="profill">
                    <label for="profill_img_update" class="profill_update_btn" id="profill_img_update_label">프로필수정</label>
                    <input type="file" class="profill_update_btn" id="profill_img_update" hidden>
                </div>
                <div class="profill_box" id="member_name_all">
                    <p class="profill_category">닉네임</p>
                    <p class="profill_result" id="member_name">${Host_profile.name}</p>
                    <button class="profill_update_btn" id="memeber_name_btn">닉네임수정</button>
                </div>
                <div class="profill_box" id="member_email_all">
                    <p class="profill_category">이메일</p>
                    <p class="profill_result" id="member_email">${Host_profile.email}</p>
                    <button class="profill_update_btn" id="member_email_btn">이메일수정</button>
                </div>
                <div class="profill_box" id="member_tel_all">
                    <p class="profill_category">연락처</p>
                    <p class="profill_result" id="member_tel">${Host_profile.tel}</p>
                    <button class="profill_update_btn" id="member_tel_btn">연락처수정</button>
                </div>
                <div class="profill_box"  id="member_pw_all">
                    <button class="profill_update_btn" id="member_pw_btn">비밀번호 변경</button>
                    <input type="text" name="num" id="member_num" value="${Host_profile.num}" hidden>
                </div>
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
<script src="resources/js/mypage/profill.js"></script>
</html>