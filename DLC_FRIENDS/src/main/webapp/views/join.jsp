<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/style/join.css"/>
<title>Insert title here</title>
</head>
<body>
	<div id="root">
	<%-- <jsp:include page="../header.jsp"></jsp:include> --%>
	<section id="main-section">
	<div class="wrap">
    <div class="join_wrap">
    <h2 class="signup_title">회원가입</h2>
    <form onsubmit="return checks()" method="post" action="joinForm" class="signup_form">
        <div class="signup">
            <div class="signup_Box">
                <label for="id" class="signup_label"> ID : </label>
                <input class="signup_input" type="text" name="id" id="id" placeholder="아이디" 
                value maxlength="20" autocapitalize="off" required>
                <span class="not-valid_input"></span>
            </div>
            <div class="signup_Box">
                <label for="password" class="signup_label">PW : </label>
                <input class="signup_input" type="password" name="password" id="password" value maxlength="30" 
                autocomplete="off" placeholder="비밀번호" required>
                <span class="not_valid_input"></span>
            </div>
            <div class="signup_Box">
                <label for="confirm_password" class="signup_label">PW 확인 : </label>
                <input class="signup_input" type="password" name="confirm_password" id="confirm_password" 
                value maxlength="30" placeholder="PW 확인" autocomplete="off" value required >
            </div>
            <div class="signup_Box">
                <label for="nickName" class="signup_label">닉네임 : </label>
                <input class="signup_input" type="text" name="nickName" id="nickName" value maxlength="7" 
                autocomplete="off" placeholder="한글로만 입력" value required>
                <span class="not_valid_input"></span>
            </div>
            <div class="signup_Box">
                <label for="email" class="signup_label">E-mail : </label>
                <input class="signup_input" type="email" name="email" id="email" placeholder="이메일 입력" required>
            </div>
            <div class="signup_Box">
                <label for="birth" class="signup_label">생년월일 : </label>
                <input class="signup_input" type="text" name="birth" id="birth" placeholder="생년월일 8자리" required>
            </div>
            <div>
                <button type="submit" class="signup_btn" >가입하기</button>
                <!-- disabled => 조건이 충족될 때까지 사용자가 입력 필드를 클릭할 수 없도록 설정, 로그인 페이지로 이동.-->
            </div>
            <div>
                <button type="button" class="signup_cancel_button"><a href="/login"></a>취소</button> <!-- 로그인 페이지로 돌아감 -->
            </div>
        </div>
    </form>
    <div>
        이미 회원이신가요?
        <a href="/login" type="button" class="signup_go_to_login">로그인하기</a> <!-- 이미 계정이 있는 경우 클릭 시 로그인 페이지로 이동.-->
    </div>
</div>
</div>
	</section>
	<%-- <jsp:include page="../footer.jsp"></jsp:include> --%>
 	</div>
</body>
</html>