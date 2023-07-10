<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="root">
		<section id="main-section">
			<button onclick="location.href='/views/postWrite.jsp'">글쓰기</button>
			<!--  
			<form id="viewForm" method="get" action="/PostRead">
				<input type="number" id="post_no" name="post_no">
				<input type="submit" value="조회">
			</form>
			-->
			<input type="number" id="post_no" name="post_no">
			<button onclick="viewPost()">조회</button>
		</section>
	</div>
	<script src="../resources/script/view-count.js"></script>
</body>
</html>