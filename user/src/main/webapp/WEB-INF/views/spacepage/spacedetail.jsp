<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워크토크</title>
    <link rel="stylesheet" href="resources/css/spacepage/spacedetail.css">
    <link rel="stylesheet" href="resources/css/header/header.css">
    <link rel="stylesheet" href="resources/css/footer/footer.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
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
                            <a href="http://localhost:8100/user/login.do">
					        	<p id="loginOK">로그인 / 회원가입</p>
					        </a>
                        </c:when>
                        <c:otherwise>
                            <a href="http://localhost:8100/user/mypage.do">
                                <p id="loginOK">${user_name}</p>
                            </a>
                            <a href="http://localhost:8100/user/mypage.do">
                                <p id="logout" style="margin-left:10px;">로그아웃</p>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <div id="body">
        <div id="bodyarea">
            <P id="spaceTitle">강남 공간 1호</P>
            <div id="detail_top">
                <div class="slides_img number_slides_img">
                    <div><img src="resources/css/image/space/dummy2.jpg"></div>
                    <div><img src="resources/css/image/space/dummy1.jpg"></div>
                    <div><img src="resources/css/image/space/dummy3.jpg"></div>
                </div>
                <div class="controller">
                    <button type="button" class="prev" onclick="prev('number');">&lang;</button>
                    <button type="button" class="next" onclick="next('number');">&rang;</button>
                </div>
                <div id="mapArea">
                    <div id="map" style="width:350px;height:350px;z-index: -1;"></div>
                    <div id="space_info">
                        <p>서울특별시 00구 00동 000</p>
                        <p>호스트 : 꾸러기</p>
                        <p>연락처? 이메일?</p>
                    </div>
                </div>
            </div>
            <div id="detail_bottom">
                <dvi id="detail_bottom_left">
                    <div id="navVar">
                        <ul>
                            <li onclick="nav('spaceItro')">공간소개</li>
                            <li onclick="nav('spaceNote')">유의사항</li>
                            <li onclick="nav('spaceRefund')">환불정책</li>
                            <li onclick="nav('spaceQNA')">Q&A</li>
                            <li onclick="nav('spaceReview')">이용후기</li>
                        </ul>
                    </div>
                    <div id="detailContent">
                        <div id="spaceItro">
                            <p>공간소개</p>
                            <textarea name="spcaeItro" class="spaceItroText" readonly>우리공간은 공간소개 강남1호점입니다.</textarea>
                        </div>
                        <div id="spaceNote">
                            <p>유의사항</p>
                            <textarea name="spaceNote" class="spaceItroText" readonly>유의사항</textarea>
                        </div>
                        <div id="spaceRefund">
                            <p>환불정책</p>
                            <textarea name="spaceRefund" class="spaceItroText" readonly>환불정책</textarea>
                        </div>
                        <div id="spaceQNA">
                            <p>Q&A</p>
                            <button id="qna_insert_btn">QNA문의작성</button>
                            <div id="QNA">
                                <!-- 출력문... -->
                                <c:forEach var="qna" items="${qna_list}">
	                                <div class="QNA_box">
	                                    <div class="QNA">
	                                        <p>${qna.writer}</p>
	                                        <p class="writeDate">${qna.q_date}</p>
	                                        <textarea name="QNAText" class="QNAText" readonly>${qna.content}</textarea>
                                    </div>
                                    <c:choose>
                                    	<c:when test="${qna.qc_comment != '0'}">
                                 			<div class="QNAAnswer">
                                            	<p class="reviewAnswer_title">호스트의 답글</p>
                                            	<p class="writeDate">${qna.qc_date}</p>
                                            	<button class="updateBtn" id="admin_update">수정</button>
                                            	<button class="deleteBtn" id="admin_delelte">삭제</button>
                                              	<textarea name="QNAText" class="QNAText" readonly>${qna.qc_comment}</textarea>
                                          	</div>
                                    	</c:when>
                                    </c:choose>
                                	</div>                              
                                </c:forEach>
                            </div>
                        </div>
                        <div id="spaceReview">
                            <p>이용후기</p>
                            <P id="reviewsGradeAvg" style="font-size: 15px;"></P>
                            <div id="reviews">
                                <!-- 출력문... -->
                                <div class="review_box">
                                    <div class="review">
                                        <p>작성자</p>
                                        <p class="writeDate">작성일</p>
                                        <div class="reviewGrade">
                                            <img src="./resources/css/image/icon/reveiwGrade.png"/>
                                        </div>
                                        <div>
                                            <button class="insertBtn" id="admin_update">답글</button>
                                        </div>
                                        <div class="reviewGradeColor"  value="3.5">
                                            <img src="./resources/css/image/icon/reveiwGradeColor.png" />
                                        </div>
                                        <!-- admin은 리뷰아이디로 계정으로 접근시만 보이도록... -->
                                        <textarea name="reviewText" class="reviewText" readonly>내용</textarea>
                                        <div class="reviewImg">
                                            <img src="./resources/css/image/space/dummy1.jpg">
                                            <img src="./resources/css/image/space/dummy2.jpg">
                                            <img src="./resources/css/image/space/dummy3.jpg">
                                        </div>
                                    </div>
                                    <div class="reviewAnser">
                                    </div>
                                </div>
                                <div class="review_box">
                                    <div class="review">
                                        <p>작성자</p>
                                        <p class="writeDate">작성일</p>
                                        <div class="reviewGrade">
                                            <img src="./resources/css/image/icon/reveiwGrade.png"/>
                                        </div>
                                        <div class="reviewGradeColor" value="1">
                                            <img src="./resources/css/image/icon/reveiwGradeColor.png" />
                                        </div>
                                        <!-- admin은 리뷰아이디로 계정으로 접근시만 보이도록... -->
                                        <textarea name="reviewText" class="reviewText" readonly>내용</textarea>
                                        <div class="reviewImg">
                                            <img src="./resources/css/image/space/dummy1.jpg">
                                            <img src="./resources/css/image/space/dummy2.jpg">
                                            <img src="./resources/css/image/space/dummy3.jpg">
                                        </div>
                                    </div>
                                    <div class="reviewAnser">
                                        <p class="reviewAnser_title">호스트의 답글</p>
                                        <p class="writeDate">작성일</p>
                                        <div>
                                            <button class="updateBtn" id="admin_update">수정</button>
                                            <button class="deleteBtn" id="admin_delelte">삭제</button>
                                        </div>
                                        <textarea name="reviewText" class="reviewText" readonly>내용</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </dvi>
                <dvi id="detail_bottom_right">
                    <div id="rBox">
                        <p>세부공간 선택</p>
                        <div class="room">
                            <label><input type="radio" name="room" id="21" class="reservationRoom">코알라</label>
                            <p class="roomType room1" value="회의실(4인)"></p>
                        </div>
                        <div class="room room2">
                            <label><input type="radio" name="room" id="19" class="reservationRoom">코끼리</label>
                            <p class="roomType" value="회의실(6인)"></p>
                        </div>
                        <div class="room room3">
                            <label><input type="radio" name="room" id="20" class="reservationRoom">기린</label>
                            <p class="roomType" value="회의실(8~10인)"></p>
                        </div>
                    </div>
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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0fd06a3065b01f5befa56232b116ea8d"></script>
<script src="resources/js/spacepage/spacedetail.js"></script>
<script src="resources/js/header/header.js"></script>
</html>