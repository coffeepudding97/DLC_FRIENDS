<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/style/header.css">
<link rel="stylesheet" href="../resources/style/footer.css">
<link rel="stylesheet" href="../resources/style/postWrite.css">
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<div id="root">
		<section id="main-section">
			<div id="wrap">
			<form method="post" action="/UpdatePostFormAction">
				<div class="write-wrap">
					<input type="hidden" name="postNo" value="${requestScope.post.postNo }">
					<div id="div-title">
						<input type="text" id="title" name="title" value="${requestScope.post.title}" placeholder="제목">
						<!-- <input type="hidden" id="user_id"
								name="user_id" placeholder="아이디" value="${sessionScope.log }"> -->
						<%-- <input type="text" id="user_id" name="user_id" placeholder="아이디"> --%>
					</div>
					<div id="div-gametitle">
						<h2>게임타이틀</h2>
						<input id="selected" type="hidden" value="${requestScope.post.gameTitle }">
						<select id="gametitle" name="gametitle">
							
						</select>
						<h2>선호게임</h2>
					</div>
					<div id="div-meeting">
						<div class="member_cnt">
							<h2>참여인원</h2>
							<input type="number" id="recruitment-max" name="recruitment-max" min="2" step="1" value="${requestScope.post.recruitMax }">
						</div>
						<div class="create_time">
							<h2>시작시간</h2>
							<input type="datetime-local" id="meettime" name="meettime" value="${requestScope.post.meetTime }">
						</div>						
						<div class="end_time">
							<h2>종료시간</h2>
							<input type="datetime-local" id="leavetime" name="leavetime" value="${requestScope.post.leaveTime }">
						</div>
					</div>
					<div id="div-content">
						<input type="text" id="content" name="content" value="${requestScope.post.content }" placeholder="내용" autofocus>
					</div>
					<input type="submit" value="작성">
				</div>
			</form>
			<%-- <button onclick="location.href='post.jsp'">작성</button> --%>
			</div>
		</section>
	</div>
	<script src="../resources/script/postUpdate.js"></script>
</body>
</html>