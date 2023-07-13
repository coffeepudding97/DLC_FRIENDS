$(document).ready(function(){
	$("#gametitleToggle").click(function(){
		$("#gametitleList").toggle();
	});
	
	$("input[type='checkbox']").on("click", function(){
	  	var checkedCount = $("input:checkbox[name=selectGame]:checked").length;
		if(checkedCount > 5){
			$(this).prop("checked", false);
			alert("5개까지만 선택할 수 있습니다.");
		}
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
