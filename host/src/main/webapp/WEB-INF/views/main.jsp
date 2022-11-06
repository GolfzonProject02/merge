<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워크토크</title>
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/header/header.css">
    <link rel="stylesheet" href="resources/css/footer/footer.css">
    <link rel="stylesheet" href="resources/css/home/home.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <div id="header">
        <div id="headerarea">
            <a href="/host/main.do">
                <img id="logo" src="resources/css/image/logo.png">
            </a>
            <form id="searchspace" action="space_searchList.do" method="get">
                <input id="searchText" type="text" name="searchWord">
                <input id="searchIcon"  type="image" src="resources/css/image/icon/search-removebg.png">
            </form>
            <img id="menutab_buttom" src="resources/css/image/icon/catalog.png" onclick="menutabOpen()">
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
            <div id="hostMain_title">
                <a href="/host/backoffice/space_insert.do"><p>새공간등록</p></a>
            </div>
            <div id="recommend_space_box">
                <div id="space_contant_all">
                    <!-- 공간 모듈 출력 공간 -->
                     <c:forEach var="space" items="${space_list}">
                    <div class="space_contant_item">
                        <div class="space_slide">
                            <div class="slides_img ${space.space_num}_slides_img">
                                <div><img src="/resources/upload/space/${space.space_img}"></div>
                            </div>
                        </div>
                        <a href="/host/backoffice/space_selectOne.do?space_num=${space.space_num}">
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
<script src="resources/js/home/home.js"></script>
</html>