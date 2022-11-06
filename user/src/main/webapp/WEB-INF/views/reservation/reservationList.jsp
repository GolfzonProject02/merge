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
// 후기작성
$('.reservation').on('click','.reviewInsert',function(){
    $('#review_update_box').detach();
    let room = $(this).parent().children('.grade_box').children('.user_review_room').text();
    let text = $(this).parent().children('.reviewText').text();
    let review_num = $(this).parent().children('.review_num').val();
    let grade = $(this).parent().children('.grade_box').children('.reviewGradeColor').attr('value');
    let input_radio = ""
    console.log(room,text,review_num,grade);
    $(this).after(`
        <div id="review_update_box">
            <p>후기수정</p>
            <form action="review_update.do" method="get" enctype="multipart/form-data">
                <input type="text" name="title" id="review_update_title" value="${room}" readonly>
                <div class="grade_wrap">
                    <div class="insert_grade_box">
                        <label for="grade_1" class="label_star" title="0.5"><span class="blind">0.5점</span></label>
                        <label for="grade_2" class="label_star" title="1"><span class="blind">1점</span></label>
                        <label for="grade_3" class="label_star" title="1.5"><span class="blind">1.5점</span></label>
                        <label for="grade_4" class="label_star" title="2"><span class="blind">2점</span></label>
                        <label for="grade_5" class="label_star" title="2.5"><span class="blind">2.5점</span></label>
                        <label for="grade_6" class="label_star" title="3"><span class="blind">3점</span></label>
                        <label for="grade_7" class="label_star" title="3.5"><span class="blind">3.5점</span></label>
                        <label for="grade_8" class="label_star" title="4"><span class="blind">4점</span></label>
                        <label for="grade_9" class="label_star" title="4.5"><span class="blind">4.5점</span></label>
                        <label for="grade_10" class="label_star" title="5"><span class="blind">5점</span></label>
                        <input type="radio" name="grade" value="0.5" id="grade_1" class="star_radio"/>
                        <input type="radio" name="grade" value="1" id="grade_2" class="star_radio"/>
                        <input type="radio" name="grade" value="1.5" id="grade_3" class="star_radio"/>
                        <input type="radio" name="grade" value="2" id="grade_4" class="star_radio"/>
                        <input type="radio" name="grade" value="2.5" id="grade_5" class="star_radio"/>
                        <input type="radio" name="grade" value="3" id="grade_6" class="star_radio"/>
                        <input type="radio" name="grade" value="3.5" id="grade_7" class="star_radio"/>
                        <input type="radio" name="grade" value="4" id="grade_8" class="star_radio"/>
                        <input type="radio" name="grade" value="4.5" id="grade_9" class="star_radio"/>
                        <input type="radio" name="grade" value="5" id="grade_10" class="star_radio"/>
                        <span class="grade_bg"></span>
                    </div>
                </div>
                <textarea name="content" id="review_update_content">${text}</textarea>
                <button id="review_update_close">닫기</button>
                <input type="file" name="multipartFile" id="review_imgname">
                <input type="submit" id="review_update_submit" value="수정">
                <input type="text" name="review_num" id="review_num" value="${review_num}" hidden>
                <input type="text' id="writer" name="writer" value="kim" hidden>
            </form>
        </div>
    `)
    $('input:radio[name=grade]:input[value=' + grade + ']').attr("checked", true);
})

$('#review_selectAll').on('click','#review_update_close',function(){
    console.log("클릭");
    $('#review_update_box').detach();
})
</script>
</html>