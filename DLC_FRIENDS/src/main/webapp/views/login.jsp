<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="../resources/style/login.css">
<!-- <script src="../resources/js/login.js"></script> -->
<title>Insert title here</title>
</head>
<body>
	<div class="login_container">
        <h2>Login</h2>
        <form onsubmit="return loginChk()" method="post" action="/login">
	        <div class="login-box">
	            <input type="text" name="id" id="id" placeholder="아이디" maxlength="30" autocapitalize="off">
	        </div>
	        <div class="login-box">
	            <input type="password" name="password" id="password" placeholder="비밀번호" maxlength="30" autocapitalize="off">
	        </div>
	        <button id="login_button" type="submit" value="login">로그인</button>
    	</form>
	    <div class="login_signup">
	        DLC.FRIENDS가 처음이신가요?
	        <span class="login__signup_link">   
	            <button class="join_button" id="join_button" type="button" onclick="join.jsp()">회원가입</a></button>
	        </span>
	    </div>
    </div>
</body>
</html>