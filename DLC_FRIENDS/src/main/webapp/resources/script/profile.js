function modify(){
	alert('회원정보가 변경되었습니다.');
}

function mypage() {
	try {
		window.location.href = 'ProfileForm'; // 이동할 페이지의 URL을 지정
	} catch (error) {
		console.error('오류 발생:', error);
	}
}

function test(button){
	alert(button);
}

function modify(htmlForm){
	// const nickname = htmlForm.nickname.value;
	const newPassword = htmlForm.newPw.value;
	// const birthday = htmlForm.birthday.value;
	
	// 비밀번호 유효성 검사(영문, 숫자, 특수기호 8자리 이상~15자리 이하)
	const PwdReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
	// YYYYMMDD 형식
	// const dateReg = /^\d{4}\d{2}\d{2}$/;
	
	let isValidPassword = PwdReg.test(newPassword);
	// let isValidBirth = dateReg.test(birthday);
	
	let check = true;
	
	if(newPassword === ""){
		console.log("비밀번호 값을 입력");
		check = false;
	}
	
	if(!isValidPassword){
		console.log("비밀번호 오류: 영문, 숫자, 특수기호를 조합하여 8~15자리수로 입력해주세요.");
		check = false;
	}
	/* 
	else if(!isValidBirth){
		alert("생년월일 오류: 형식에 맞게 입력해주세요. (ex)19901210");
		check = false;
	}
	*/
	
	if(check === true)
		htmlForm.submit();
}
