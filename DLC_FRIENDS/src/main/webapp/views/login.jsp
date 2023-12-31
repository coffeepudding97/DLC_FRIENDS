<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta property="og:title" content="DLC_FRIENDS">
<meta property="og:description" content="게임 친구 매칭 웹 플랫폼 DLC 프렌즈">
<meta property="og:image" content="../resources/images/meta_img.jpg">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="../resources/style/login.css">
<script src="../resources/script/login.js"></script>
<link rel="shortcut icon" href="../resources/images/favicon.ico">
<title>로그인</title>
</head>
<body>
	<div id="wrap">
		<div id="root">
			<%-- <jsp:include page="../header.jsp"></jsp:include> --%>
			<section id="main-section">
				<div class="login_container">
					<div class="logo">
						<a href="/index"><img
							src="../resources/images/main_logo_black.png"></a>
					</div>
					<form id="login">
						<div class="id_txt">아이디</div>
						<div class="login-box">
							<ion-icon name="person-outline"></ion-icon>
							<input type="text" name="id" id="id" maxlength="30"
								autocapitalize="off">
						</div>
						<div class="pw_txt">비밀번호</div>
						<div class="login-box">
							<ion-icon name="lock-closed-outline"></ion-icon>
							<input type="password" name="password" id="password"
								maxlength="30" autocapitalize="off">
						</div>
						<input type="button" id="submit_button" value="login"
							onclick="loginChk()">
						<div class="login_signup">
							<a href="finduser" class="forget_password">아이디 / 비밀번호 찾기</a>
							<a href="/join">회원가입</a>
						</div>
					</form>
				</div>
			</section>
			<script type="module"
				src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
			<script nomodule
				src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
			<%-- <jsp:include page="../footer.jsp"></jsp:include> --%>
		</div>
	</div>
</body>
</html>