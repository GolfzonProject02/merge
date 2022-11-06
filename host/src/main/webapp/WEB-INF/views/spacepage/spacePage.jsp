<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워크토크</title>
    <link rel="stylesheet" href="../resources/css/spacepage/spacePage.css">
    <link rel="stylesheet" href="../resources/css/header/header.css">
    <link rel="stylesheet" href="../resources/css/footer/footer.css">
    <link rel="stylesheet" href="../resources/css/home/home.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <div id="header">
        <div id="headerarea">
            <a href="/host/main.do">
                <img id="logo" src="../resources/css/image/logo.png">
            </a>
            <form id="searchspace" action="space_searchList.do" method="get">
                <input id="searchText" type="text" name="searchWord">
                <input id="searchIcon"  type="image" src="../resources/css/image/icon/search-removebg.png">
            </form>
            <img id="menutab_buttom" src="../resources/css/image/icon/catalog.png" onclick="menutabOpen()">
            <div id="menutab">
                <img id="menutab_close" src="../resources/css/image/icon/close.png" onclick="menutabClose()">
                <div id="login">
                	<c:choose>
                        <c:when test="${host_name == null}">
                            <a href="login.do">
                                <p id="loginOK">로그인 / 회원가입</p>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/user/mypage.do?=${host_name}">
                                <p id="loginOK">${host_name}</p>
                            </a>
                            <a href="/user/logout.do?=${host_name}">
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
            <form action="/host/backoffice/space_insertOK.do" method="post" enctype="multipart/form-data">
   			 <input type="text" name="space_type" id="spaceType" value="" hidden readonly>
              <input type="text" name="host" value="${host_name}" hidden>
                <p id="spaceTypeTitle">공간종류</p>
                <div id="space_category">
                    <div class="space_category_item" value="1">
                        <img class="spaceType" src="../resources/css/image/icon/table.png" alt="데스크공간">
                        <p>데스크</p>
                    </div>
                    <div class="space_category_item" value="2">
                        <img class="spaceType" src="../resources/css/image/icon/meeting2.png" alt="회의실공간">
                        <p>회의실</p>
                    </div>
                    <div class="space_category_item" value="3">
                        <img class="spaceType" src="../resources/css/image/icon/office.png" alt="오피스공간">
                        <p>오피스</p>
                    </div>
                </div>
                <div class="box">
                    <p>공간명</p>
                    <p id="spaceName_textCount">0/20자</p>
                    <input type="text" name="space_name" id="spaceName" placeholder="공간의 이름을 작성해주세요." value="">
                </div>
                <div class="box">
                    <p>공간소개</p>
                    <p id="spaceInfo_textCount">0/100자</p>
                    <textarea name="space_detail" id="spaceInfo" cols="30" rows="10" placeholder="공간을 소개해 주세요."></textarea>
                </div>
                <div>
                    <p>공간주소</p>
                    <input type="text" name="postcode" id="postcode" placeholder="우편번호" value="">
                    <input type="button" id="postcodeBtn" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
                    <input type="text" name="address" id="address" placeholder="주소" value=""><br>
                    <input type="text" name="detail_address" id="detailAddress" placeholder="상세주소" value="">
                </div>
                <div>
                    <p>사업자번호</p>
                    <input type="text" name="reg_code" id="reg_code" oninput="phoneFormat(this)" maxlength="13" placeholder="예) 132-45-67890" value="">
                </div>
                <div>
                    <p>대표이미지</p>
                    <input type="file" id="spaceImg" style="border: 0; font-size: 15px;" name="multipartFile">
                </div>
                <input type="submit" value="등록">
            </form>
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
    <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
    <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../resources/js/spacepage/spacePage.js"></script>
<script src="../resources/js/header/header.js"></script>
</html>