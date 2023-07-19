<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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