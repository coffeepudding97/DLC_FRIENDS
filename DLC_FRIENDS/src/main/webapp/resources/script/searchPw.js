$(document).ready(function() {
	$('#pw-change').hide();
	$('.hint').hide();

});


var verified = false;

function findId(){
	var email = $('#id_email').val();
	console.log(email);
	$.ajax({
		"method":"POST",
		"url":`/FindId?email=${email}`,
	}).done(json => {
		$('#found_id').text(`${json.userId}`);
	})
}

function sendCode() {
	var id = $("#id").val();
	var email = $("#email").val();
	
	if ( email === "") {
		$("#hint_email").text("이메일 형식에 맞지 않습니다.");
		$("#hint_email").show();
		return false;
	}
	var addr = email;
	$('#send_btn').prop("disabled", true);
	
	$.ajax({
		"method": "POST",
		"url": `checkAndSendCode?addr=${addr}&id=${id}`,
	}).done(json => {
		if(json.code !== undefined && json.userId !== ""){
			$('#div_code').show();
			alert('이메일로 코드가 전송되었습니다.');
			console.log("인증발송 코드 : " + json.code);
			console.log("인증발송 제한 시간 : " + json.time);
			
		} else{
			alert('코드 전송이 실패하였습니다. 이메일이나 아이디를 다시 확인해주세요.');
			return false;
		}
	})
	setTimeout(function() {
		$('#send_btn').prop("disabled", false)
	}, 3000);
}

function verify() {
	$('#hint_email').hide();
	
	$.ajax({
		"method": "POST",
		"url": "GetCode"
	}).done(json => {

		let currentTime = new Date();
		let currentTimeMillis = currentTime.getTime();

		console.log("인증버튼 코드 : " + json.code);
		console.log("인증버튼 제한 시간 : " + json.time);
		console.log("인증버튼 현재 시간 : " + currentTimeMillis);
		
		
		if (currentTimeMillis > json.time) {
			alert("유효하지 않은 코드 입니다.");
		} else {
			let code = $('#code').val();
			if (code == json.code) {
				alert('이메일 인증이 완료되었습니다.');
				$('#pw-change').show();
				$('#email').prop("disabled", true);
				$('#send_btn').prop("disabled", true);
				$('#code').prop("disabled", true);
				$('#verify_btn').prop("disabled", true);
				verified = true;
				
			} else {
				alert("유효하지 않은 코드 입니다.");
			}
		}
	})
}

function changePwd(){
	var pwd = $('#password').val();
	var pwdChk = $('#passwordChk').val();
	var id = $('#id').val();
	
	if(verified === true){
		if(pwd === pwdChk){
			$.ajax({
				type: 'POST',
				url: 'changePwd',
				data: {
					pwd: pwd,
					id: id
				},
				
				success: (json) => {
					if(json.result){
						alert('비밀번호 변경 성공!');
						window.location.href='login';
					}else{
						alert('비밀번호 변경 실패.. 다시 시도해주세요');
					}
				}
			})
		}
		
	}
}