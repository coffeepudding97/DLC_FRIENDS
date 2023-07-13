function post_comment() {
	const user_id = $('#userId').val();
	const content = $('#comment').val();
	const post_no = $('#postNo').val();
	const reply_No = $('#rpNo').val();
	// const comment_no;

	$.ajax({
		"method": "POST",
		"url": `http://localhost:8080/comment?postNo=${post_no}&userId=${user_id}&comment=${content}&rpNo=${reply_No}`,
	}).done(result => {
		if (result === 'true') {
			// 댓글 영역 갱신 
			$.ajax({
				"method": "GET",
				"url": `http://localhost:8080/CommentAllAction?postNo=${post_no}`
			}).done(list => append_comment(list))

		} else {
			alert('댓글 등록 처리실패');
		}
	})
}

function delete_comment(button) {
	const user_id = $('#userId').val();
	const content = $('#comment').val();
	const post_no = $('#postNo').val();
	const form = $(button).closest('form');
	const cmtNo = form.find('input[name="cmtNo"]').val();
	// const comment_no;
	console.log(button);
	console.log(cmtNo);

	$.ajax({
		"method": "POST",
		"url": `http://localhost:8080/deleteCmt?cmtNo=${cmtNo}`,
	}).done(result => {
		if (result === 'true') {
			// 댓글 영역 갱신 
			$.ajax({
				"method": "GET",
				"url": `http://localhost:8080/CommentAllAction?postNo=${post_no}`
			}).done(list => append_comment(list))

		} else {
			alert('댓글 삭제 처리실패');
		}
	})
}

function append_comment(list) {
	const user_id = $('#userId').val();
	let color = "orange";
	let cls = "cmt";
	$('#ul-comment').empty();
	
	/*<li id="cmt${comment.cmtNo}" class="${cls}">*/
	
	list.forEach(comment => {
		let tag = '';
		if (comment.userId === user_id) {
			tag = `<input type="button" value="삭제" onclick="delete_comment(this)">`
		}
		
		if(comment.rpNo!=0){
			color="blue";
			cls = "rp";
		}else{
			color = "orange";
			cls = "cmt";
		}
		
		let li = `
			<li class="${cls}">
				<p>
					<strong name="userId" style="color:${color}">${comment.userId}</strong>
				</p>
				<p>
					${comment.content}
				</p>
				<p>
					${comment.createdTime}
				</p>
				<form>
				<div>
					<input type="hidden" class="cmtNo" name="cmtNo" value="${comment.cmtNo}">
					<input type="button" class="comment_answer" value="답글 쓰기" onclick="setRpNo(this)">
					${tag}
				 <div>
				</form>
			</li>
		`;
		
		/*if(comment.rpNo!=0){
			$(`#cmt${comment.rpNo}`).after(li);
		}else{
			$('#ul-comment').append(li);
		}*/
		$('#ul-comment').append(li);
	})
}



function profileClick(button){
	const postNo = $('#postNo').val();
	const userId = $('#userId').val();
	const btn = $(button);
	const memberId = btn.find('.memberId').text();
	console.log(userId);
	console.log(memberId);
	console.log(userId === memberId);
	
	
	if(userId === memberId){
		if($('#isEnd').text()=="기간초과"){
			alert("기간초과");
		}else{
			$.ajax({
				"method":"POST",
				"url":`http://localhost:8080//PartyLeaveAction?postNo=${postNo}&userId=${userId}`
			}).done(party => {
				btn.empty();
				btn.append(`<span class="memberId">+</span>`);
				btn.removeAttr("onclick");
				btn.attr("onclick", "blankClick(this)");
				let member_count = $("#party_member_count").text();
				$("#party_member_count").text(parseInt(member_count) - 1);
			})
		}
		
	} else{
		alert("프로필");
	}
}

function blankClick(button){
	const postNo = $('#postNo').val();
	const userId = $('#userId').val();
	let log = true;
	let res = false;
	
	
	if(userId == ""){
		log=false;
	}
	
	if($('#isEnd').text()=="기간초과"){
		alert("기간초과");
	} else{
		if(log){
			$('.memberId').each(
				function(){
					if($(this).text() === userId){
						res = true;
						return false;
					}
				}
			);
		
			if(!res){
				$.ajax({
					"method":"POST",
					"url":`http://localhost:8080/PartyJoinAction?postNo=${postNo}&userId=${userId}`,
					"dataType":"json"
				}).done(profile => {
					$(button).empty();
					$(button).append(`${profile.profileImg}<span class="memberId">${profile.id }</span>`);
					$(button).removeAttr("onclick");
					$(button).attr("onclick", "profileClick(this)");
					let member_count = $("#party_member_count").text();
					$("#party_member_count").text(parseInt(member_count) + 1);
				})
				
				
			} else {
				alert("이미 참가한 유저");
			}
		} else {
			alert("로그인");
		}
	}
	
	
	
}

function setRpNo(button){
	const form = $(button).closest('form');
	const li = $(button).closest('li');
	const userId = li.find('strong[name="userId"]').text();
	const cmtNo = form.find('input[name="cmtNo"]').val();
	
	$('#rpNo').val(cmtNo);
	$('#replyName').text("to." + userId + "/" + cmtNo);
	console.log($('#rpNo').val());
}

function resetRpNo(){
	$('#rpNo').val(0);
	$('#replyName').text("");
}

$(document).ready(function(){
	const postNo = $('#postNo').val();
	isEnd();
	
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/PostViewUpdateAction?postNo=${postNo}`
	})
	
	$.ajax({
		"method":"GET",
		"url":`http://localhost:8080/CommentAllAction?postNo=${postNo}`
	}).done(list => append_comment(list))
})

function delPost(){
	const postNo = $('#postNo').val();
	
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/DeletePostFormAction?postNo=${postNo}`
	}).done(result => {
		if(result==="true"){
			location.href = "/dbtestGJ"
		}else{
			alert("삭제 실패");
		}
	})
}

function isEnd(){
	var currentTime = new Date();
	var meetTime = $('#meetTime').text();
	var meetTimeMillis = new Date(meetTime).getTime();
	var currentTimeMillis = currentTime.getTime();
	
	if(meetTimeMillis>currentTimeMillis){
		$('#isEnd').text("모집기간");
	}else{
		$('#isEnd').text("기간초과");
	}
}