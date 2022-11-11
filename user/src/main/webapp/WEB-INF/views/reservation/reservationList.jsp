<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워크토크</title>
    <link rel="stylesheet" href="../resources/css/header/header.css">
    <link rel="stylesheet" href="../resources/css/footer/footer.css">
    <link rel="stylesheet" href="../resources/css/reservation/reservationList.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <div id="header">
        <div id="headerarea">
            <a href="/user/main.do">
                <img id="logo" src="../resources/css/image/logo.png">
            </a>
            <form id="searchspace" action="검색.....">
                <input id="searchText" type="text" name="searchWord">
                <input id="searchIcon"  type="image" src="../resources/css/image/icon/search-removebg.png">
            </form>
            <img id="menutab_buttom" src="../resources/css/image/icon/catalog.png" onclick="menutabOpen()">
            <div id="menutab">
                <img id="menutab_close" src="../resources/css/image/icon/close.png" onclick="menutabClose()">
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
            <div id="reservationList_title">
                <p>예약리스트</p>
            </div>
            <div id="reservationList_sort">
                <select name="" id="reservationStatusSort">
                    <option hidden>예약상태별</option>
                    <option value="">전체</option>
                    <option value="승인대기">예약완료</option>
                    <option value="이용자취소">이용자취소</option>
                    <option value="호스트취소">호스트취소</option>
                </select>
            </div>
            <div id="reservations">
                <!-- 반복문 -->
                <c:forEach items="${reservation_list}" var="reserve">
	                <div class="reservation">
	                    <p class="reservationStatus">${reserve.status}</p>
	                    <a href="reservationDetail/room="+${reserve.room_name}>
	                        <P class="spaceName">${reserve.space_name} / ${reserve.room_name}</P>
	                    </a>
	                    <c:choose>
	                        <c:when test="${reserve.cancel_reason != null}">
                            	<p>${reserve.cancel_reason}</p>
	                        </c:when>
	                    </c:choose>
	                    <p class="reservationDate">신청일 : ${reserve.r_date} / 예약일 : ${reserve.r_start}</p>
	                    <input type="text" name="r_date" class="r_date" value="${reserve.r_date}" hidden>
	                    <input type="text" name="r_num" class="r_num" value="${reserve.r_num}" hidden>
	                </div>
                </c:forEach>
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
<script src="../resources/js/reservation/reservationList.js"></script>
<script>
// 정렬버튼 반응형
$('#reservationList_sort').on('change','#reservationStatusSort', function(){
    console.log($(this).val());
})
$('#reservationList_sort').on('change','#paymentStatusSort', function(){
    console.log($(this).val());
})
</script>
</html>