<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워크토크</title>
    <link rel="stylesheet" href="../../resources/css/header/header.css">
    <link rel="stylesheet" href="../../resources/css/footer/footer.css">
    <link rel="stylesheet" href="../../resources/css/reservation/reservationList.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
    <div id="header">
        <div id="headerarea">
            <a href="main.html">
                <img id="logo" src="../../resources/css/image/logo.png">
            </a>
            <form id="searchspace" action="검색.....">
                <input id="searchText" type="text" name="searchWord">
                <input id="searchIcon"  type="image" src="../../resources/css/image/icon/search-removebg.png">
            </form>
            <img id="menutab_buttom" src="../../resources/css/image/icon/catalog.png" onclick="menutabOpen()">
            <div id="menutab">
                <img id="menutab_close" src="../../resources/css/image/icon/close.png" onclick="menutabClose()">
                <div id="login">
                </div>
                <div id="mypage_package">
                    <div>
                        <a href="예약리스트 페이지">
                        <img src="../../resources/css/image/icon/reservation.png"><br>
                        <p>예약리스트</p>
                        </a>
                    </div>
                    <div>
                        <a href="이용후기 Q&A관리 페이지">
                        <img src="../../resources/css/image/icon/qna.png"><br>
                        <p>이용후기<br>Q&A 관리</p>
                        </a>
                    </div>
                    <div>
                        <a href="결제내역 마일리지 페이지">
                        <img src="../../resources/css/image/icon/payment.png"><br>
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
            <div id="reservationList_title">
                <p>예약리스트</p>
            </div>
            <div id="reservationList_sort">
                <select name="" id="reservationStatusSort">
                    <option hidden>예약상태별</option>
                    <option value="">전체</option>
                    <option value="승인대기">승인대기</option>
                    <option value="승인완료">승인완료</option>
                    <option value="예약취소">예약취소</option>
                </select>
            </div>
            <div id="reservations">
                <!-- 반복문 -->
                <c:forEach items="${host_reservation_list}" var="host_reserve">
	                <div class="reservation">
	                    <p class="reservationStatus">${host_reserve.status}</p>
	                    <a href="reservationDetail/room="+${host_reserve.room_name}>
	                        <P class="spaceName">${host_reserve.space_name} / ${host_reserve.room_name}</P>
	                    </a>
	                    <c:choose>
	                        <c:when test="${reserve.cancel_reason != null}">
                            	<p>${reserve.cancel_reason}</p>
	                        </c:when>
	                    </c:choose>
	                    <p class="reservationDate">신청일 : ${host_reserve.r_date} / 예약일 : ${host_reserve.r_start}</p>
	                    <input type="text" name="r_date" class="r_date" value="${host_reserve.r_date}" hidden>
	                    <input type="text" name="r_num" class="r_num" value="${host_reserve.r_num}" hidden>
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
<script src="../../resources/js/reservation/reservationList.js"></script>
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