<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../resources/css/customerCenter/customerCenter.css">
    <link rel="stylesheet" href="../resources/css/header/header.css">
    <link rel="stylesheet" href="../resources/css/footer/footer.css">
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
                </div>
                <div id="mypage_package">
                    <div>
                        <a href="예약리스트 페이지">
                        <img src="../resources/css/image/icon/reservation.png"><br>
                        <p>예약리스트</p>
                        </a>
                    </div>
                    <div>
                        <a href="이용후기 Q&A관리 페이지">
                        <img src="../resources/css/image/icon/qna.png"><br>
                        <p>이용후기<br>Q&A 관리</p>
                        </a>
                    </div>
                    <div>
                        <a href="결제내역 마일리지 페이지">
                        <img src="../resources/css/image/icon/payment.png"><br>
                        <p>결제내역<br>마일리지</p>
                        </a>
                    </div>
                </div>
                <div id="menutab_bottom">
                    <div class="menutab_bottom_item">
                        <a href="/user/mypage/customercenter.do">
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
            <div id="pageTitle">
                <p>1:1문의 관리</p>
            </div>
            <div id="pageTop">
                <select name="답글상태" id="sort_box">
                    <option value="1">답글상태</option>
                    <option value="4">답글등록</option>
                    <option value="4">답글미등록</option>
                </select>
                <button id="QNA_insert_btn">1:1문의작성</button>
            </div>
            <div id="QNA_selectAll">
                <!-- c문 출력 -->
                <c:forEach var="cc" items="${cc_boardlist}">
                <div class="QNA_box">
                    <div class="user_QNA">
                    	<p class="user_QNA_title">[ ${cc.title} ]</p>
                    	<p class="user_QNA_type">${cc.type}</p>
                        <p class="user_QNA_Date">${cc.cc_date}</p>
                        <button class="updateBtn" id="글번호">수정</button>
                        <button class="deleteBtn" id="글번호">삭제</button>
                        <textarea name="QNAText" class="QNAText" readonly>${cc.content}</textarea>
                        <input type="text" class="cc_num" value="${cc.cc_num}" hidden>
                        <img alt="${cc.imgname}" src="../resources/upload/customer_center/${cc.imgname}">
                    </div>
                    <!-- c-if문 출력 -->
                    <c:choose>
                        <c:when test="${master} != null}">
                            <div class="master_Anser">
		                        <p class="master_Anser_title">[ 호스트의 답글 ]</p>
		                        <p class="master_Anser_date">00.00.00</p>
		                        <textarea name="QNAText" class="AnserText" readonly>내용</textarea>
		                    </div>
                        </c:when>
                    </c:choose>
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
<script src="../resources/js/customerCenter/customerCenter.js"></script>
</html>