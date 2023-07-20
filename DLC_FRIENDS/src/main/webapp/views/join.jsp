<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta property="og:title" content="DLC_FRIENDS">
<meta property="og:description" content="게임 친구 매칭 웹 플랫폼 DLC 프렌즈">
<meta property="og:image" content="../resources/images/meta_img.jpg">
<link rel="stylesheet" href="../resources/style/join.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="../resources/script/join.js" ></script>
<link rel="shortcut icon" href="../resources/images/favicon.ico">
<title>회원가입</title>
</head>
<body>
	<div id="wrap">
	<section id="main-section">
	<div class="sign_container">
	<div class="logo"><a href="/index"><img src="../resources/images/main_logo_black.png"></a></div>
        <form id="form" class="sign">
            <div class="input-control">
                <label class="label" for="id">아이디</label>
                <div class="input_and_button">
                <input id="id" name="id" type="text" placeholder="최소 4자리 이상 16자리 이하 영소문자, 숫자" maxlength="40">
                <button id="dup_btn" type="button" value="중복확인" class="dup" onclick="idDuplChk()">중복 확인</button>
                </div>
                <small class="small hint" id="hint_id">최소 4자리 이상 16자리 이하 영소문자, 숫자만 입력해야 합니다.</small>
            </div>
            <div class="input-control">
                <label class="label" for="password">비밀번호</label>
                <input id="password" name="password" type="password" maxlength="40" placeholder="최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함">
                <small class="small hint" id="hint_password">최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함해 입력해야 합니다.</small>
            </div>
            <div class="input-control">
                <label class="label" for="passwordChk">비밀번호 확인</label>
                <input id="passwordChk" name="passwordChk" type="password" maxlength="40">
                <small class="small hint" id="hint_passwordChk">비밀번호가 일치하지 않습니다.</small>
            </div>
            <div class="input-control">
                <label class="label" for="nickName">닉네임</label>
                <div class="input_and_button">
                <input id="nickName" name="nickName" type="text" maxlength="8" placeholder="최소 2자리 이상 8자리 이하 한글, 영문, 숫자">
                <button id="dup_btn" type="button" value="중복확인" class="dup" onclick="nickDuplChk()">중복 확인</button>
                </div>
                <small class="small hint" id="hint_nickName">최소 2자리 이상 8자리 이하 한글, 영문, 숫자만 입력해야 합니다.</small>
            </div>
        <!--<div class="input-control">
                <label for="email">E-mail</label>
                <input id="email" name="email" type="email" maxlength="40">
                <small class="small">예)email2023@domain.com</small>
            </div>-->
			<div class="input-control">
				<label class="label" for="email">E-mail</label>
				<div class="input_and_button">
				<input id="email" name="email" type="email" placeholder="예)email2023@domain.com" maxlength="40">
				<button id="send_btn" type="button" value="send_code" class="send_code" onclick="sendCode()">인증번호 발송</button>
				</div>
				<small class="small hint" id="hint_email">이메일이 인증되지 않았습니다.</small>
			</div>
			<div id="div_code" class="input-control">
				<label class="label" for="verify_btn">인증번호 입력</label>
				<input id="code" name="code" type="number" maxlength="6">
				<button id="verify_btn" type="button" value="verify" class="verify" onclick="verify()">인증하기</button>
			</div>
			<div class="input-control">
                <label class="label" for="birth">생년월일</label>
                <input id="birth" name="birth" type="text" placeholder="19YYMMDD" maxlength="8">
                <small class="small hint" id="hint_birth">생년월일을 19YYMMDD 형식으로 입력해야 합니다.</small>
            </div>
            <div class="sumit_and_cancel">
            <button id="submit_btn" type="button" onclick="signupChk()">가입하기</button>
            </div>
        </form>
    </div>
	</section>
 	</div>
</body>
</html>