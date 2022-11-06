<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워크토크</title>
    <link rel="stylesheet" href="resources/css/spacepage/spacepage.css">
    <link rel="stylesheet" href="resources/css/header/header.css">
    <link rel="stylesheet" href="resources/css/footer/footer.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
</head>
<body>
    <div id="header">
        <div id="headerarea">
            <a href="/user/main.do">
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
                	<c:choose>
                        <c:when test="${user_name == null}">
                            <a href="login.do">
                                <p id="loginOK">로그인 / 회원가입</p>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/user/mypage.do?=${user_name}">
                                <p id="loginOK">${user_name}</p>
                            </a>
                            <a href="/user/logout.do?=${user_name}">
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
            <div id="searchArea">
                <form action="검색" id="searchBox">
                    <select name="spaceType" id="spaceType">
                        <option value="1" hidden>데스크</option>
                        <option value="1">데스크</option>
                        <option value="2">회의실</option>
                        <option value="3">오피스</option>
                    </select>
                    <select name="addressRegion" id="addressRegion"></select>
                    <select name="addressDo" id="addressDo"></select>
                    <select name="addressSiGunGu" id="addressSiGunGu"></select>
                    <input type="text" id="day" name="day" value="" />
                    <input type="image" src="resources/css/image/icon/search.png" alt="search" id="searchBtn" class="button">
                </form>
                <a href="spacemap.html">
                    <img src="resources/css/image/icon/map.png" alt="map" id="mapBtn" class="button">
                </a>
            </div>
            <div id="search_space_box">
                <div id="space_contant_all">
                    <!-- 공간 모듈 출력 공간 -->
                    <c:forEach var="space" items="${space_list}">
                    <div class="space_contant_item">
                        <div class="space_slide">
                            <div class="slides_img ${space.space_num}_slides_img">
                                <div><img alt="${space.space_img}" src="http://localhost:8200/host/resources/upload/space/${space.space_img}"></div>
                    			<div><img src="resources/css/image/space/dummy1.jpg"></div>
                                <div><img src="resources/css/image/space/dummy3.jpg"></div>
                            </div>
                        </div>
                         <a href="/user/space_selectOne.do?space_num=${space.space_num}">
                         
                            <div class="space_contant_text">
                                    <p class="space_name">${space.space_name}</p><br>
                                    <img class="space_adress" src="resources/css/image/icon/location.png">
                                    <p class="space_adress">${space.address}</p><br>
                                    <img src="resources/css/image/icon/star.png">
                                    <p class="space_grade">평점</p>
                                    <img src="resources/css/image/icon/comment.png">
                                    <p class="space_review">후기</p>
                            </div>
                        </a>
                        <div class="controller">
                            <button type="button" class="prev" onclick="prev('number');">&lang;</button>
                            <button type="button" class="next" onclick="next('number');">&rang;</button>
                        </div>
                    </div>
                    </c:forEach>
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
<script src="resources/js/header/header.js"></script>
<script src="resources/js/spacepage/spacepage.js"></script>
</html>