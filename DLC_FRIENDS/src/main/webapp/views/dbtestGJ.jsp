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
			<button onclick="location.href='views/postWrite.jsp'">글쓰기</button>
			<form method="get" action="PostRead">
				<input type="number" id="post_no" name="post_no">
				<input type="submit" value="조회">
			</form>
		</section>
	</div>
</body>
</html>