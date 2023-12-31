<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>평가게시판</title>
<link href="../resources/style/warningBoard.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="../resources/images/favicon.ico">
	<meta property="og:title" content="DLC_FRIENDS">
	<meta property="og:description" content="게임 친구 매칭 웹 플랫폼 DLC 프렌즈">
	<meta property="og:image" content="../resources/images/meta_img.jpg">
</head>
<body>
	<c:import url="/header"></c:import>
	<div id="wrap">
		<script src="../resources/script/warningBoard.js"></script>
		<div id="root">
			<section id="main-section">
				<div class="board_wrap">
					<ul class="board_title">
						<li>닉네임</li>
						<li>메모</li>
						<li>평점</li>
						<li>트롤태그</li>
					</ul>
					<ul id="ul-board">
					</ul>
				</div>
				<div class="view_more">
					<button id="more_btn" onclick="getMore()">
						<img src="../resources/images/arrow_down.png">
					</button>
				</div>
			</section>
		</div>
	</div>
	<c:import url="footer"></c:import>
</body>
</html>