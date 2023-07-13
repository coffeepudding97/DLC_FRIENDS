
//유효성검사 메소드
function signupChk(){
    var getnickName = RegExp(/^[가-힣a-zA-Z0-9]{2,8}$/); // 한글, 영어 대소문자, 숫자 [ 2~8자리 까지 입력가능 ]
    var getid= RegExp(/^[a-z0-9]{4,30}$/); // 영어 소문자, 숫자 [ 4~30자리 까지 입력가능 ]
    var getpw= RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/); // 문자, 숫자, 특수문자 최소 1개씩 포함시켜야함. [ 최소 8자리이상 입력 ]
    var getMail = RegExp(/^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/); // 이메일 형식만 입력가능
    var getBirth = RegExp(/^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/); // 19991010 형식만 입력가능

    //아이디 유효성 검사
    if(!getid.test($("#id").val())){
        alert("아이디가 형식에 맞지 않습니다");
        $("#id").val("");
        $("#id").focus();
        return false;
    }

    //비밀번호
    if(!getpw.test($("#password").val())){
        alert("비밀번호 형식에 맞지 않습니다");
        $("#password").val("");
        $("#password").focus();
        return false;  
    }

    // 아이디랑 비밀번호랑 같은지 체크
    else if($("#id").val()==($("#password").val())){
        alert("아이디와 비밀번호가 똑같습니다");
        $("#password").val("");
        $("$password").focus();
    }

    //비밀번호 똑같은지 재확인
    else if ($("#password").val() !== $("#passwordChk").val()) {
        if ($("#passwordChk").val() !== "") {
            alert("비밀번호가 일치하지 않습니다.");
            $("#password").val("");
            $("#password").focus();
        } else {
            alert("비밀번호 확인을 입력해 주세요.");
            $("#passwordChk").focus();
        }
        return false;
    }

    // 닉네임 유효성
    if (!getnickName.test($("#nickName").val())){
        alert("사용할 수 없는 닉네임 입니다");
        $("#nickName").val("");
        $("#nickName").focus();
        return false;
    }

    //이메일 공백 확인
    if($("#email").val() == ""){
        alert("이메일 형식에 맞게 작성해 주세요");
        $("#email").focus();
        return false;
    }

    //이메일 유효성 검사
    if(!getMail.test($("#email").val())){
        alert("이메일 형식에 맞게 입력해 주세요");
        $("#email").val("");
        $("#email").focus();
        return false;
    }

    // 생년월일 유효성 검사
    if (!getBirth.test($("#birth").val())) {
        alert("생년월일을 형식에 맞게 입력해 주세요");
        $("#birth").val("");
        $("#birth").focus();
        return false;
    }
    
    // 데이터 전송을 위한 Ajax 요청
  $.ajax({
    url: "",
    type: "POST",
    data: {
      id: id,
      password: password,
      nickName: nickName,
      email: email,
      birth: birth
    },
    success: function (response) {
      // 응답 처리
      if (response.success) {
        // 회원 가입 성공
        alert("회원 가입이 완료되었습니다.");
        location.href = "/login"; // 로그인 페이지로 이동
      } else {
        // 회원 가입 실패
        alert("회원 가입에 실패했습니다. 다시 시도해주세요.");
      }
    },
    error: function () {
      // 에러 처리
      alert("서버와의 통신 중 오류가 발생했습니다.");
    }
  });

    // 유효성 검사 통과 시 폼 제출
    document.getElementById("form").submit();
    
}