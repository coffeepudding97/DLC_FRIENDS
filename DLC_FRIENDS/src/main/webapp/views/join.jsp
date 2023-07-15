<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/style/join.css"/>
<script src="../resources/js/join.js" ></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<div id="root">
	<section id="main-section">
	<div class="sign_container">
        <h1>회원가입</h1>
        <form onsubmit="return signupChk()" id="form" method="post" action="joinForm" class="sign">
            <div class="input-control">
                <label for="id">아이디</label>
                <input id="id" name="id" type="text" placeholder="" maxlength="40">
                <button type="button" class="id_check_button" onclick="id_check_check()">중복검사</button>
				<img id="id_check_sucess" style="display: none;">
            </div>
            <div class="input-control">
                <label for="password">비밀번호</label>
                <input id="password" name="password" type="password" maxlength="40">
                <small class="small">* 영문, 숫자, 특수문자 최소 1개씩 포함</small>
            </div>
            <div class="input-control">
                <label for="passwordChk">비밀번호 확인</label>
                <input id="passwordChk" name="passwordChk" type="password" maxlength="40">
                <input type="hidden" value="passwordUncheck" class="small">* 비밀번호를 다시 한번 입력해 주세요</input>
            </div>
            <div class="input-control">
                <label for="nickName">닉네임</label>
                <input id="nickName" name="nickName" type="text" maxlength="8">
                <small class="small">* 최소 2자리 이상 8자리 이하 한글, 영문, 숫자 </small>
            </div>
            <div class="input-control">
                <label for="email">E-mail</label>
                <input id="email" name="email" type="email" maxlength="40">
                <small class="small">예)email2023@domain.com</small>
            </div>
            <div class="input-control">
                <label for="birth">생년월일</label>
                <input id="birth" name="birth" type="text" placeholder="YYYYMMDD" maxlength="8">
                <small class="small">예)19991010</small>
            </div>
            <button type="submit">가입하기</button>
            <button type="button" class="signup_cancel-button">취소</button>
        </form>
    </div>
	</section>
 	</div>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>