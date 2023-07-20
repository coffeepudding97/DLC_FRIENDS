<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta property="og:title" content="DLC_FRIENDS">
<meta property="og:description" content="게임 친구 매칭 웹 플랫폼 DLC 프렌즈">
<meta property="og:image" content="../resources/images/meta_img.jpg">
<title>유저 평가</title>
<link rel="stylesheet" href="../resources/style/rating.css" />
<link rel="shortcut icon" href="../resources/images/favicon.ico">
</head>
<c:import url="/header"></c:import>
<body>
	<div id="wrap">
	<div id="root">
		<section id="main-section">
		<div class="rating">
			<h1 id="userId">${sessionScope.log }</h1>
			<ul id="ul-rating">
				
			</ul>
		</div>
		</section>
	</div>
	</div>
	<script src="../resources/script/rating.js"></script>
<c:import url="/footer"></c:import>
</body>
</html>