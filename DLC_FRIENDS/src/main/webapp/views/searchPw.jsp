<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="../resources/style/login.css">
<script src="../resources/script/searchPw.js"></script>
<link rel="shortcut icon" href="../resources/images/favicon.ico">
<title>비밀번호 찾기</title>
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
					<form>
						<h1>아이디찾기</h1>
						<div>
							<div class="id_txt">이메일</div>
							<div class="login-box">
								<ion-icon name="mail-outline"></ion-icon>
								<input type="text" name="id_email" id="id_email" maxlength="30"
									autocapitalize="off">
							</div>
							<div class="id_btn_box">
							<button id="search_id_btn" type="button" value="search_id"
								class="send_code" onclick="findId()">아이디 찾기</button>
							</div>
							<div><p id="found_id"></div>
						</div>
					</form>
					<form>
						<h1>비밀번호 찾기</h1>
						<div class="id_txt">아이디</div>
						<div class="login-box">
							<ion-icon name="mail-outline"></ion-icon>
							<input type="text" name="id" id="id" maxlength="30"
								autocapitalize="off">
						</div>
						
						<div class="id_txt">이메일</div>
						<div class="login-box">
							<ion-icon name="mail-outline"></ion-icon>
							<input type="text" name="email" id="email" maxlength="30"
								autocapitalize="off"> <small class="small hint"
								id="hint_email">이메일이 인증되지 않았습니다.</small>

							<button id="send_btn" type="button" value="send_code"
								class="send_code" onclick="sendCode()">인증번호발송</button>

						</div>

						<div class="input-control">
							<div id="div_code" class="input-control">
								<div class="pw_txt">코드입력</div>
								<div class="pw_content">
								<input id="code" name="code" type="number" maxlength="6">
								<button id="verify_btn" type="button" value="verify"
									class="verify" onclick="verify()">인증하기</button>
								</div>
							</div>
						</div>

						<div id="pw-change">
							<div class="pw_txt">새로운 비밀번호</div>
							<div class="login-box">
								<ion-icon name="lock-closed-outline"></ion-icon>
								<input type="password" name="password" id="password"
									maxlength="30" autocapitalize="off">
							</div>

							<div class="pw_txt">새로운 비밀번호 확인</div>
							<div class="login-box">
								<ion-icon name="lock-closed-outline"></ion-icon>
								<input type="password" name="passwordChk" id="passwordChk"
									maxlength="30" autocapitalize="off">
							</div>
							<div class="button_wrap">
							<input type="button" id="change_pw" value="비밀번호 변경"
								onclick="changePwd()">
							</div>
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