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
					<a href="/index"><img src="/resources/images/main_logo.png"></a>
					</div>
					<div class="logo_right">
					<c:choose>
						<c:when test="${empty sessionScope.log }">
							<a href="/login"><img src="../resources/images/login.png"></a>
						</c:when>
						<c:otherwise>
							<form method="POST" action="/ProfileForm" style="display:inline;">
								<!-- <input type="hidden" name="id" value="${sessionScope.log}" />  -->
      							<input type="submit" class="my_page"/>
      						</form>
      						<a href="/logoutAction"><img src="../resources/images/logout.png"></a>
						</c:otherwise>
					</c:choose>
					</div>
					
					
					</div>
			</div>
			<div class=header_bottom_wrap>
				<div class=header_bottom>
					<a href="/index" class="on">친구찾기</a> <a href="/warningBoard">평가게시판</a> <a id="rating_link" href="/rating">유저평가</a>
				</div>
				</div>
			</div>
		</nav>
	</header>
	<script src="/resources/script/getSessionLog.js"></script>
</body>
</html>