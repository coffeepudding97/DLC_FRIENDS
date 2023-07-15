$(document).ready(function() {
	/* 기능 */
	$('#uploadBtn').click(function() {
		var inputImage = document.getElementById('input_image');

		if (inputImage.files.length === 0) {
			alert('사진을 선택해주세요!');
		} else {
			uploadImage();
		}
	});

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