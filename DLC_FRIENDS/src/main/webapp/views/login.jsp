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
	<div class="login_container">
        <h2>Login</h2>
        <form method="post" acrion="url" id="login_form">
        <div class="login-box">
            <input type="text" name="id" id="id" placeholder="아이디"  value maxlength="20" autocapitalize="off" required="">
            <label>Username</label>
        </div>
        <div class="login-box">
            <input type="password" name="" required="">
            <label>Password</label>
        </div>
        <div class="login_button" type="submit" value="Login">로그인</div>
    </form>
    <div class="signup_link">
        <a href="signup.html" type="button">회원가입</a>
    </div>
    </div>
    </section>
    </div>
</body>
</html>