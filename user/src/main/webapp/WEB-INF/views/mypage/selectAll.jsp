<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>selectAll.jsp</h1>
	
	<!-- 문의글 insert -->
	<form action="/user/mypage/customercenter_insert.do" method="post" enctype="multipart/form-data">
	<select name="type" id="type">
            <option value="reservation">예약</option>
            <option value="account">계정</option>
            <option value="payment">결제</option>           
          </select>
	<input type="text" name="writer" value="kim">
	<input type="text" name="title" value="hi">
	<textarea type="text" placeholder="content" id="content" name="content" value="content" maxlength="500"></textarea>
	<input type="file" name="multipartFile"><br>
<!-- 	<input type="file" name="multipartFile[]" multiple"><br> -->
	<input type="submit">
	</form>
	
	 <!-- selectOne 앨범선택으로 자동으로 값을 가져와서 출력 -->
      <div id="selectOne" style="display: none;">
        <form action="/user/mypage/customercenter_update.do" method="post" enctype="multipart/form-data">
          <!-- 제목 자동으로 가져온다. -->
          <input type="text" id="selectOne_title" name="selectOne_title" value="">
          <!-- user의 id 가져오기 -->
<!--           <input type="text" id="selectOne_writer" name="selectOne_writer" value="" readonly> -->
          <!-- 작성일 -->
          <input type="text" value="" id="update_date" name="update_date" readonly>
          <!-- 앨범ID -->
          <input type="hidden" value="" id="update_id" name="update_id" readonly>
          <br>
          <ul style="padding: 0px; margin: 0px;">
            <!-- 수정 파일 업로드 공간 -->
            <li><img id="update_image" src=""></li>
            <li><input type="file" id="updateFile" name="updateFile" multiple></li>
          </ul>
          <!-- 수정, 삭제버튼 -->
          <label class="submit writer_power" for="update_submit" style="right: 145px;"><a>수 정</a></label>
          <input type="submit" id="update_submit" hidden>
          
          <label class="submit writer_power" for="album_delete_button" style="right: 90px;"><a id="delete_button" href="">삭 제</a></label>
          <input type="submit" id="album_delete_button" hidden>
          
          <label class="submit" for="update_cancle" style="right:35px" onclick="action('cancle_insert')"><a>닫 기</a></label>
          <input type="button" id="update_cancle" hidden>
          
          <input type="text" value="${param.cc_num}" name="cc_num" hidden>
          <input type="text" value="${param.writer}" name="writer" hidden>
        </form>
      </div>
      
	<form action="/user/mypage/customercenter_searchList.do" method="get">
		<table>
			<tr>
				<td><select name="searchKey" id="searchKey">
						<option value="title">title</option>
						<option value="content">content</option>
				</select></td>
				<td><input type="text" name="searchWord" id="searchWord"
					value="ki"> 
					
					</td>
					<td>
					<input type="submit">
					</td>
			</tr>
		</table>
	</form>
	
	<!-- selectAll -->
        <!-- 문의글 정렬 -->
	<table border="1">
	<thead>
		<tr>
			<th>num</th>
			<th>title</th>
			<th>type</th>
			<th>content</th>
			<th>writer</th>
			<th>date</th>
			<th>imgname</th>
		</tr>
		</thead>
		<tbody>
		 <!-- c문 출력 -->
		<c:forEach var="customer_center" items="${cc_boardlist}">
			<tr>
				<td><a href="selectOne.do?num=${customer_center.cc_num}">${customer_center.cc_num}</a></td>
				<td>${customer_center.title}</td>
				<td>${customer_center.type}</td>
				<td>${customer_center.content}</td>
				<td>${customer_center.writer}</td>
				<td>${customer_center.cc_date}</td>
				<td><img width="100" alt="${customer_center.imgname}" src="../resources/upload/customer_center/${customer_center.imgname}"></td>
			</tr>			
		</c:forEach>
		 <!-- c문 출력 끝 -->
		</tbody>
	</table>

</body>
</html>