$(document).ready(function() {
	/* 기능 */
	$("input[type='checkbox']").on("click", function() {
		var checkedCount = $("input:checkbox[name=selectGame]:checked").length;
		if (checkedCount > 5) {
			$(this).prop("checked", false);
			alert("5개까지만 선택할 수 있습니다.");
		}
	});

	/* 프론트 */
	$("#gametitleToggle").click(function() {
		$("#gametitleList").slideToggle();
	});
	$(".update_img_txt").click(function() {
		$(".upload_img").slideToggle();
	});
	$(".update_nickname_title a").click(function() {
		$(".update_nickname, .update_password, .update_comment").slideToggle();
	});
	$(".update_password_title a").click(function() {
		$(".update_nickname, .update_password, .update_comment").slideToggle();
	});
	$(".update_comment_title a").click(function() {
		$(".update_nickname, .update_password, .update_comment").slideToggle();
	});
});

$('#uploadBtn').click(function() {
	var inputImage = document.getElementById('input_image');

	if (inputImage.files.length === 0) {
		alert('사진을 선택해주세요!');
	} else {
		uploadImage();
	}
});

// 선호게임설정 /SelectGames
function selectGames() {
	var selGamesList = [];
	
	$('input:checkbox[name=selectGame]:checked').each(function(i){
		selGamesList.push($(this).val());
		console.log($(this).val());
	});
	
	var data = {selectGame:selGamesList };
	$.ajax({
		type: 'POST',
		url: 'SelectGames',
		data: data,
		traditional: true,
		success: (response) => {
			console.log(response.result);
			if(response.result === true){
				alert('선호게임 설정 완료!');
			}else{
				alert('선호게임 설정 실패ㅠ')
			}
		}
	});
}



// 프로필 사진 수정
function uploadImage() {
	var inputImage = document.getElementById('input_image');
	var formData = new FormData();
	formData.append('input_image', inputImage.files[0]);

	$.ajax({
		type: 'POST',
		url: 'uploadImage',
		data: formData,
		processData: false,
		contentType: false,
		success: function(response) {
			alert(response);
			if (response === '프로필사진 업로드완료!') {
				location.href = 'profileUpdate';
			}
		},
		error: function() {
			alert('프로필사진 업로드 실패..');
		},
	});
}

// 비밀번호, 소개글 수정
function modifyPwAndInfo() {
    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;
    var newPassword = document.getElementById("newPw").value;
    var info = document.getElementById("info").value;
    
    // 비밀번호는 8자리 이상 문자, 숫자, 특수문자 최소 1개 이상 포함
    var getpw = RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/);
  
    var data = {
        id: id,
    };
    
    // 기존 비밀번호, 새로운 비밀번호 같이 입력되면 data에 값 입력
	if (password !== '' && !getpw.test(newPassword)) {
		console.log(newPassword);
		alert('비밀번호는 8자리 이상 문자, 숫자, 특수문자 최소 1개 이상 포함해주세요. ');
		return false;
		// 조건에 맞으면 값 넘겨주기
	} else {
		data.password = password;
		data.newPw = newPassword;
	}
  
    if (info) {
        data.info = info;
    }
  
    $.ajax({
        type: 'POST',
        url: 'memberInfoModify',
        data: data,
        success: (response) => {
            console.log('값: '+response.result);
            if (response.result === 'pwChange') {
                alert('비밀번호가 변경되었습니다!');
                location.reload();
            } else if (response.result === 'pwWrong') {
                alert('기존 비밀번호가 틀렸습니다.');
                //location.reload();
            } else if (response.result === 'infoChange' || response.result === '') {
                alert('소개글이 수정되었습니다!');
                //window.location.href='/';
            }
        },
        error: function () {
            alert(error);
        },
    });
}