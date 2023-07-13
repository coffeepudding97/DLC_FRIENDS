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

function test(button){
	alert(button);
}

function modify(htmlForm){
	let check = true;
	
	if(check === true)
		htmlForm.submit();
}
