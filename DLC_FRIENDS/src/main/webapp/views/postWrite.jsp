<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta property="og:title" content="DLC_FRIENDS">
<meta property="og:description" content="게임 친구 매칭 웹 플랫폼 DLC 프렌즈">
<meta property="og:image" content="../resources/images/meta_img.jpg">
<title>글쓰기</title>
<link rel="stylesheet" href="../resources/style/header.css">
<link rel="stylesheet" href="../resources/style/footer.css">
<link rel="stylesheet" href="../resources/style/postWrite.css">
<link rel="shortcut icon" href="../resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<div id="root">
		<section id="main-section">
			<div id="wrap">
				<form onsubmit="return checkWrite()" method="post" action="/WritePost">
					<div class="write-wrap">
						<div id="div-title">
							<input type="text" id="title" name="title" placeholder="제목"
								autofocus> 
						</div>
						<div id="div-gametitle">
							<h2>게임타이틀</h2>
							<select id="gametitle" name="gametitle">
							</select>
						</div>
							<div id="favorite_game">
							<h2>선호게임</h2>
							<div class="favorite_game_list">
							</div>
							</div>
						<div id="div-meeting">
							<div class="member_cnt">
								<h2>참여인원</h2>
								<input type="number" id="recruitment-max" name="recruitment-max"
									min="2" step="1" value="2">
							</div>
							<div class="create_time">
								<h2>시작시간</h2>
								<input type="datetime-local" id="meettime" name="meettime">
							</div>
							<div class="end_time">
								<h2>종료시간</h2>
								<input type="datetime-local" id="leavetime" name="leavetime">
							</div>
						</div>
						<div id="div-content">
							<input type="text" id="content" name="content" placeholder="내용">
						</div>
						<div id="div-submit">
							<input id="submit_btn" type="submit" value="작성">
						</div>
					</div>
				</form>
				<%-- <button onclick="location.href='post.jsp'">작성</button> --%>
			</div>
		</section>
	</div>
	<jsp:include page="/footer"></jsp:include>
	<script src="../resources/script/postWrite.js"></script>
	<script src="../resources/script/getSessionLog.js"></script>
</body>
</html>