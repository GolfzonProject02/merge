<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../resources/css/reviewqna/reviewqna.css">
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
            <div id="pageTitle">
                <a href="/user/mypage/reviewqna.do?writer=${user_name}"><p>후기관리</p></a>
                <a href="/user/mypage/qna.do?writer=${user_name}"><p>Q&A 관리</p></a>
            </div>
            <div id="review">
                <div id="review_pageTop">
                    <select name="QNA" class="sort_box" id="grade">
                        <option value="0" hidden>문의종류</option>
                        <option value="1">전체</option>
                        <option value="2">예약</option>
                        <option value="3">계정</option>
                        <option value="4">결제</option>
                    </select>
                    <select name="답글상태" class="sort_box" id="review_anser">
                        <option value="0" hidden>답글상태</option>
                        <option value="1">전체</option>
                        <option value="2">답글등록</option>
                        <option value="3">답글미등록</option>
                    </select>
                </div>
                <div id="review_selectAll">
                    <!-- c문 출력 -->
                    <c:forEach var="review" items="${review_list}">
                    <div class="review_box">
                        <div class="user_review">
                            <div class="grade_box">
                                <p class="user_review_room">사용한 세부공간 ${review.r_num}</p>
                                <div class="reviewGrade">
                                    <img src="../resources/css/image/icon/reveiwGrade.png"/>
                                </div>
                                <div class="reviewGradeColor" value="3">
                                    <img src="../resources/css/image/icon/reveiwGradeColor.png"/>
                                </div>
                            </div>
                            <p class="user_review_writer">${review.writer}</p>
                            <p class="user_review_Date">${review.rc_date}</p>
                            <button class="review_updateBtn" id="글번호">수정</button>
                            <button class="review_deleteBtn" id="글번호">삭제</button>
                            <textarea name="reviewText" class="reviewText" readonly>내용</textarea>
                            <input type="text" class="review_num" value="리뷰번호" hidden>
                        </div>
                        <!-- c-if문 출력 -->
                        <div class="master_review_Anser">
                            <p class="master_review_Anser_title">[ 호스트의 답글 ]</p>
                            <p class="master_review_Anser_date">00.00.00</p>
                            <textarea name="reviewText" class="AnserText" readonly>내용</textarea>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            <div id="qna">
                <div id="qna_pageTop">
                    <select name="QNA" class="sort_box" id="reservation">
                        <option value="0" hidden>문의종류</option>
                        <option value="1">전체</option>
                        <option value="2">예약</option>
                        <option value="3">계정</option>
                        <option value="4">결제</option>
                    </select>
                    <select name="답글상태" class="sort_box" id="qna_anser">
                        <option value="0" hidden>답글상태</option>
                        <option value="1">전체</option>
                        <option value="2">답글등록</option>
                        <option value="3">답글미등록</option>
                    </select>
                </div>
                <div id="qna_selectAll">
                    <!-- c문 출력 -->
                      <c:forEach var="qna" items="${qna_list}">
                    <div class="qna_box">
                        <div class="user_qna">
                            <p class="user_qna_type">${qna.type}</p>
                            <p class="user_qna_Date">${qna.q_date}</p>
                            <button class="qna_updateBtn" id="글번호">수정</button>
                            <button class="qna_deleteBtn" id="글번호">삭제</button>
                            <textarea name="qnaText" class="qnaText" readonly>${qna.content}</textarea>
                            <input type="text" class="qna_num" value="${qna.q_num}" hidden>
                        </div>
                        <!-- c-if문 출력 -->
                        <c:choose>
                                    	<c:when test="${qna.qc_comment != '0'}">
											<div class="QNAAnswer">
		                                        <p class="reviewAnswer_title">호스트의 답글</p>
		                                        <p class="writeDate">${qna.qc_date}</p>
		                                        <div>
		                                            <button class="updateBtn" id="admin_update">수정</button>
		                                            <button class="deleteBtn" id="admin_delelte">삭제</button>
		                                        </div>
		                                        <textarea name="QNAText" class="QNAText" readonly>${qna.qc_comment}</textarea>
		                                    </div>
                                    	</c:when>
                                    </c:choose>
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
<script src="../resources/js/reviewqna/reviewqna.js"></script>
</html>