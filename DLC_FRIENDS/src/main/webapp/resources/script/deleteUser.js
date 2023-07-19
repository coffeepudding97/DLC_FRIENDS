function deleteChk(){
	$('#submit_button').prop("disabled", true);
	setTimeout(function(){
		$('#submit_button').prop("disabled", false);
	}, 1000);
	
	var password = $('#password').val();
	
	data = {
		password: password
	}
	
	$.ajax({
		type: 'POST',
		url: 'deleteUserForm',
		data: data,
		
		success: (response) => {
			if(response.result === "success"){
				alert('회원 탈퇴가 완료되었습니다.');
				window.location.href="/";
			} else {
				alert('회원정보가 일치하지 않습니다.');
			}
		}
	})
}

