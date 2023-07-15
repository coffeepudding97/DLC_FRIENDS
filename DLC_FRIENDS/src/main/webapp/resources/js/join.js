var idDupl = false;
var ChkIdDupl = false;
var nickDupl = false;
var ChkNickDupl = false;
var verified = false;

$(document).ready(function(){
	$('#div_code').hide();
	$('.hint').hide();
	
	$('input').focusin(function(){
		$(this).parent().find('.hint').hide();
		//$('.hint').hide();
	});

	$('#id').keyup(function(){
		idDupl = false;
		ChkIdDupl = false;
		$("#hint_id").text("").css("color", "#ff3860");
		$("#hint_id").hide();
	});
	
	$('#nickName').keyup(function(){
		nickDupl = false;
		ChkNickDupl = false;
		$("#hint_nickName").text("").css("color", "#ff3860");
		$("#hint_nickName").hide();
	});
})

//유효성검사 메소드
function signupChk(){
	$('#submit_btn').prop("disabled", true);
	
	setTimeout(function(){
		$('#submit_btn').prop("disabled", false);
	}, 500);
	
    var getnickName = RegExp(/^[가-힣a-zA-Z0-9]{2,8}$/); // 한글, 영어 대소문자, 숫자 [ 2~8자리 까지 입력가능 ]
    var getid= RegExp(/^[a-z0-9]{4,16}$/); // 영어 소문자, 숫자 [ 4~16자리 까지 입력가능 ]
    var getpw= RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/); // 문자, 숫자, 특수문자 최소 1개씩 포함시켜야함. [ 최소 8자리이상 입력 ]
    var getMail = RegExp(/^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/); // 이메일 형식만 입력가능
    var getBirth = RegExp(/^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/); // 19991010 형식만 입력가능

    //아이디 유효성 검사
    if(!getid.test($("#id").val())){
        // alert("아이디는 4자리이상 입력해야합니다");
        $("#hint_id").text("최소 4자리 이상 16자리 이하 영소문자, 숫자만 입력해야 합니다.").css("color", "#ff3860");
        $("#hint_id").show();
        $("#id").val("");
        //$("#id").focus();
        return false;
    }
    
    if (!ChkIdDupl){
		$("#hint_id").text("아이디 중복체크를 해야합니다.");
		$("#hint_id").show();
		return false;
	}
	
	if (idDupl){
		$("#hint_id").text("중복된 아이디 입니다.");
		$("#hint_id").show();
		return false;
	}

    //비밀번호
    if(!getpw.test($("#password").val())){
        // alert("비밀번호 형식에 맞지 않습니다");
        $("#hint_password").text("최소 8자리 이상 영문, 숫자, 특수문자 최소 1글자 포함해 입력해야 합니다.");
        $("#hint_password").show();
        $("#password").val("");
        //$("#password").focus();
        return false;  
    }

    // 아이디랑 비밀번호랑 같은지 체크
    else if($("#id").val()==($("#password").val())){
        // alert("아이디와 비밀번호가 똑같습니다");
        $("#hint_password").text("아이디와 비밀번호를 같게 설정할 수 없습니다.");
        $("#hint_password").show();
        $("#password").val("");
        //$("$password").focus();
    }

    //비밀번호 똑같은지 재확인
    else if ($("#password").val() !== $("#passwordChk").val()) {
        if ($("#passwordChk").val() !== "") {
            // alert("비밀번호가 일치하지 않습니다.");
            $("#password").val("");
            //$("#password").focus();
        } else {
			//alert("비밀번호 확인을 입력해 주세요.");
            //$("#passwordChk").focus();
        }
        $("#hint_passwordChk").show();
        return false;
    }

    // 닉네임 유효성
    if (!getnickName.test($("#nickName").val())){
        // alert("사용할 수 없는 닉네임 입니다");
        $("#hint_nickName").text("최소 2자리 이상 8자리 이하 한글, 영문, 숫자만 입력해야 합니다.").css("color", "#ff3860");
        $("#hint_nickName").show();
        $("#nickName").val("");
        //$("#nickName").focus();
        return false;
    }

	if (!ChkNickDupl){
		$("#hint_nickName").text("닉네임 중복체크를 해야합니다.");
		$("#hint_nickName").show();
		return false;
	}
	
	if (nickDupl){
		$("#hint_nickName").text("중복된 닉네임 입니다.");
		$("#hint_nickName").show();
		return false;
	}

    //이메일 공백 확인
    if($("#email").val() == ""){
        // alert("이메일 형식에 맞게 작성해 주세요");
        $("#hint_email").text("이메일 형식에 맞지 않습니다.");
        $("#hint_email").show();
        //$("#email").focus();
        return false;
    }

    //이메일 유효성 검사
    if(!getMail.test($("#email").val())){
        //alert("이메일 형식에 맞게 입력해 주세요");
        $("#hint_email").text("이메일 형식에 맞지 않습니다.");
        $("#hint_email").show();
        $("#email").val("");
        //$("#email").focus();
        return false;
    }
    
    if(!verified){
		$("#hint_email").text("이메일이 인증되지 않았습니다.");
        $("#hint_email").show();
		return false;
	}

    // 생년월일 유효성 검사
    if (!getBirth.test($("#birth").val())) {
        //alert("생년월일을 형식에 맞게 입력해 주세요");
        $("#hint_birth").show();
        $("#birth").val("");
        //$("#birth").focus();
        return false;
    }
    
	var formData = {
		id: $("#id").val(),
		password: $("#password").val(),
		passwordChk: $("#passwordChk").val(),
		nickName: $("#nickName").val(),
		email: $("#email").val(),
		birth: $("#birth").val()
	};
	
	console.log("email:"+formData.email);

	//servlet에 ajax 요청 보내기
	$.ajax({
		type: "POST",
		url: "/joinForm", // 서블릿의 URL
		//contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data: formData,
		//dataType: "json",
		//complete: poll, timeout: 30000,
		/*success: function(response) {
			//console.log(response.message);
			// 서버로부터의 응답을 처리
			if (response.message == "Success") {
				alert("회원가입이 성공적으로 완료되었습니다.");
				location.href = '/login'; // 리다이렉트(회원가입 완료 시 로그인창으로 이동하도록 설정)
			} else {
				alert("회원가입에 실패했습니다.");
			}
		},
		error: function(xhr, status, error) {
			// 에러 처리
			console.error(error);
		}*/
	}).done(json => {
		if(json.result == true){
			alert("회원가입에 성공했습니다.");
			location.href = "/login";
		} else{
			alert("회원가입에 실패했습니다.");
		}
	});
}


function sendCode(){
	const addr = $('#email').val();
	$('#send_btn').prop("disabled", true);
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/SendCode?addr=${addr}`
	}).done(json => {
		$('#div_code').show();
		console.log("인증발송 코드 : " + json.code);
		console.log("인증발송 제한 시간 : " + json.time);
	})
	setTimeout(function(){
		$('#send_btn').prop("disabled", false)
	}, 3000);
}

function verify(){
	$('#hint_email').hide();
	$.ajax({
		"method":"POST",
		"url":"http://localhost:8080/GetCode"
	}).done(json => {
		let currentTime = new Date();
		let currentTimeMillis = currentTime.getTime();
		
		console.log("인증버튼 코드 : " + json.code);
		console.log("인증버튼 제한 시간 : " + json.time);
		console.log("인증버튼 현재 시간 : " + currentTimeMillis);
		
		if(currentTimeMillis > json.time){
			alert("유효하지 않은 코드 입니다.");
		} else {
			let code = $('#code').val();
			if(code == json.code){
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

function idDuplChk(){
	$("#hint_id").hide();
	let id = $('#id').val();
	$('#id_dup_btn').prop("disabled", true);
	$.ajax({
		"method":"POST",
		"url":`/DuplId?id=${id}`
	}).done(json => {
		console.log(json.check);
		if(json.check == true){
		console.log(json.check);
			idDupl = true;
			$("#hint_id").text("중복된 아이디 입니다.");
			$("#hint_id").show();
		}else{
			$("#hint_id").text("중복되는 아이디가 없습니다.").css("color", "green");
			$("#hint_id").show();
			idDupl = false;
			ChkIdDupl = true;
		}
	})
	setTimeout(function(){
		$('#id_dup_btn').prop("disabled", false)
	}, 3000);
}

function nickDuplChk(){
	$("#hint_nickName").hide();
	let nickname = $('#nickName').val();
	$('#nick_dup_btn').prop("disabled", true);
	$.ajax({
		"method":"POST",
		"url":`/DuplNickname?nickname=${nickname}`
	}).done(json => {
		console.log("nick:"+json.check);
		if(json.check == true){
			console.log(json.check);
			nickDupl = true;
			$("#hint_nickName").text("중복된 닉네임 입니다.");
			$("#hint_nickName").show();
		}else{
			$("#hint_nickName").text("중복되는 닉네임이 없습니다.").css("color", "green");
			$("#hint_nickName").show();
			nickDupl = false;
			ChkNickDupl = true;
		}
	})
	setTimeout(function(){
		$('#nick_dup_btn').prop("disabled", false)
	}, 3000);
}