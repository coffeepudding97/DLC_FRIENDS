let startIndex = 0;
let postCount = 5;

$(document).ready(function(){
	$("#gametitleToggle").click(function(){
		$("#gametitleList").slideToggle();
	});
	
	$("input[type='checkbox']").on("click", function(){
	  	var checkedCount = $("input:checkbox[name=selectGame]:checked").length;
		if(checkedCount > 5){
			$(this).prop("checked", false);
			alert("5개까지만 선택할 수 있습니다.");
		}
	});
	
	/* 프로필 토글 페이징 */
	$(".posts_title").click(function(){
		$('.posts').slideToggle();
	})
	$(".comments_title").click(function(){
		$('.comments').slideToggle();
	})
	
	/* 프로필 수정 부분 */
	$('#uploadBtn').click(function() {
		var inputImage = document.getElementById('input_image');

		if (inputImage.files.length === 0) {
			alert('사진을 선택해주세요!');
		} else {
			uploadImage();
		}
	});
});


$(document).ready(function(){
	$(".update_img_txt").click(function(){
		$(".upload_img").slideToggle();
	});	
	$(".update_nickname_title a").click(function(){
		$(".update_nickname, .update_password, .update_comment").slideToggle();
	});	
	$(".update_password_title a").click(function(){
		$(".update_nickname, .update_password, .update_comment").slideToggle();
	});	
	$(".update_comment_title a").click(function(){
		$(".update_nickname, .update_password, .update_comment").slideToggle();
	});	

});



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

function modify(htmlForm){
	let check = true;
	
	if(check === true)
		htmlForm.submit();
}


// 프로필 게시글 더보기




// 프로필 사진 수정
function uploadImage(){
    var inputImage = document.getElementById('input_image');
    var formData = new FormData();
    formData.append('input_image', inputImage.files[0]);
    
    $.ajax({
        type: 'POST',
        url: 'uploadImage',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response){
            alert(response);
            if(response === '프로필사진 업로드완료!'){
                location.href = 'profileUpdate';
            }
        },
        error: function(){
            alert('프로필사진 업로드 실패..');
        },
    });
}

// 비밀번호, 소개글 수정




