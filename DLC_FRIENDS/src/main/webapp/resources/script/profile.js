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
	let check = true;
	
	if(check === true)
		htmlForm.submit();
}
