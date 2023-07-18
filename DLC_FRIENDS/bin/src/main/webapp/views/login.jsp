<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="../resources/style/login.css">
<script src="../resources/js/login.js"></script>
<title>로그인</title>
</head>
<body>
	<div id="root">
		<%-- <jsp:include page="../header.jsp"></jsp:include> --%>
		<section id="main-section">
			<div class="login_container">
				<h1>Login</h1>
				<form>
					<div class="login-box">
						<ion-icon name="mail-outline"></ion-icon>
						<input type="text" name="id" id="id" maxlength="30"
							autocapitalize="off"> <label for="id">아이디</label>
					</div>
					<div class="login-box">
						<ion-icon name="lock-closed-outline"></ion-icon>
						<input type="password" name="password" id="password"
							maxlength="30" autocapitalize="off"> <label
							for="password">비밀번호</label>
					</div>
					<input type="button" id="login_button" value="login"
						onclick="loginChk()">
				</form>
				<a href="#" class="forget_password">비밀번호를 잊어버리셨나요?</a>
				<div class="login_signup">
					<p>
						DLC.FRIENDS가 처음이신가요? <a href="/join">회원가입</a>
					</p>
				</div>
			</div>
		</section>
		<script type="module"
			src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
		<script nomodule
			src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
		<%-- <jsp:include page="../footer.jsp"></jsp:include> --%>
	</div>
</body>
</html>