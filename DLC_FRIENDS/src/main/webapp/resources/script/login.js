// 유효성 검사 메서드
function loginChk() {
    // 아이디 공백 확인
    if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요");
		$("#id").focus();
		return false;
    }

    // 비밀번호 유효성 검사
    if ($("#password").val() == "") {
		alert("아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
    //   $("#pwd").val("");
	$("#password").focus();
	return false;
    }

    // 유효성 검사 통과
    return true;
}