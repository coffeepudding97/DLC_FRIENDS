function post_comment() {
	const user_id = $('#userId').val();
	const content = $('#comment').val();
	const post_no = $('#postNo').val();
	// const comment_no;

	$.ajax({
		"method": "POST",
		"url": `http://localhost:8080/comment?postNo=${post_no}&userId=${user_id}&comment=${content}`,
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
	$('#ul-comment').empty();
	list.forEach(comment => {
		let tag = '';
		if (comment.userId === user_id) {
			tag = `<form><input type="hidden" name="cmtNo" value="${comment.cmtNo}"><input type="button" value="삭제" onclick="delete_comment(this)"></form>`
		}
		$('#ul-comment').append(`
						<li>
							<p>
								<strong>${comment.userId}</strong>
							</p>
							<p>
								${comment.content}
							</p>
							<p>
								${comment.createdTime}
							</p>
							${tag}
						</li>
					`);

	})
}