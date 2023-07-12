<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:import url="/header"></c:import>
<body>
	<div id="root">
		<section id="main-section">
			<h1 id="userId">${sessionScope.log }</h1>
			<ul id="ul-rating">
				
			</ul>
		</section>
	</div>
	<script src="../resources/script/rating.js"></script>
</body>
</html>