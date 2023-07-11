<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/style/header.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>헤더</title>
</head>
<body>
	<header>
		<nav>
			<div class=header_wrap>
			<div class=header_top_wrap>
					<div class =header_top>
					<div class = "logo_wrap">
					<a href="/main"><img src="http://localhost:8080/resources/images/main_logo.png"></a>
					</div>
					<a href="#">로그인</a>
					<a href="/main">DLC_FRIENDS</a>
					
					<c:choose>
						<c:when test="${empty sessionScope.log }">
							<a href="/login">로그인</a>
						</c:when>
						<c:otherwise>
							<form method="POST" action="/ProfileForm" style="display:inline;">
								<input type="hidden" name="id" value="${sessionScope.log}" />
      							<input type="submit" value="마이 페이지" />
      						</form>
      						<a href="/logoutAction">로그아웃</a>
						</c:otherwise>
					</c:choose>
					
					
					</div>
			</div>
			<div class=header_bottom_wrap>
				<div class=header_bottom>
					<a href="main" class="on">친구찾기</a> <a href="#">신고게시판</a> <a href="#">클랜찾기</a>
				</div>
				</div>
			</div>
		</nav>
	</header>
</body>
</html>