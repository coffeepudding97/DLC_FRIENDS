$(document).ready(function(){
	$('#login').on('keyup', function(key){
		if(key.keyCode==13){
			loginChk();
		}
	})
	
});

// 유효성 검사 메서드
function loginChk() {
	var id = $('#id').val();
	var password = $('#password').val();
	
	// 아이디 공백 확인
	if (id === "") {
		alert("아이디를 입력해 주세요");
		$("#id").focus();
		return false;
	}

	// 비밀번호 유효성 검사
	else if (password === "") {
		alert("비밀번호를 입력해주세요.");

		$("#password").focus();
		return false;
	}

	// 유효성 검사 통과

	$.ajax({
		type: 'POST',
		url: 'loginFormAction',
		data: data = {
			id: id,
			password: password
		},

		success: (response) => {
			// console.log(response.result);
			
			if(response.result === "loginTrue"){
				alert('로그인이 완료되었습니다.');
				window.location.href='/';
			}else if(response.result === "noUser"){
				alert('유저정보가 존재하지 않습니다.');
			}

		},

		error: () => {
			alert('로그인 오류');
		},
	});

};