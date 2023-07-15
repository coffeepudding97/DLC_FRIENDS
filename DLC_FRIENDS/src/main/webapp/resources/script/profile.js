var postPage = document.getElementById('post_page').innerText;
var commentPage = document.getElementById('comment_page').innerText;
var profileId = document.getElementById('profile_id').textContent;

$(document).ready(function() {
	/* 프로필 부분 */
	loadPosts();
	loadComments();
	
});


// 프로필 - 게시글 로딩
function loadPosts() {
	postPage = document.getElementById('post_page').innerText;
	$.ajax({
		type: 'GET',
		url: 'selectPosts',
		data: {
			startIdx: postPage,
			id: profileId,
		},

		success: (list) => {
			console.log(list.postList.length);
			if (list.postList.length !== 0) {

				// 기존 페이지 글 제거
				$('.posts').empty();

				list.postList.forEach((post) => {
					// timestamp -> date -> format 변환
					var timestamp = post.createdTime;
					var changeToDate = new Date(timestamp);
					var date = dateFormat(changeToDate);
					$('.posts').append(
						`
						<ul>
							<li>${post.gameTitle}</li>
							<li>${post.title}</li>
							<li>${date}</li>
							<br>
						</ul>
						`
					);
				});
			} else if (postPage === '1' && list.postList.length === 0) {
				$('.posts').append('<p>작성한 게시글이 없습니다..</p>')
			}
			else if (list.postList.length === 0) {
				alert('마지막 페이지입니다.');
				var element = document.getElementById('post_page');
				postPage = Number(postPage);
				postPage -= 1;
				element.innerText = postPage;
			}
		},

	});
}

// 프로필 게시글 +
function post_plus_btn() {
	var element = document.getElementById('post_page');
	postPage = Number(postPage);

	postPage += 1;
	element.innerText = postPage;
	loadPosts();
}

// 프로필 게시글 -
function post_minus_btn() {
	var element = document.getElementById('post_page');
	postPage = Number(postPage);
	if (postPage > 1) {

		postPage -= 1;
		element.innerText = postPage;
		console.log(postPage);
		loadPosts();
	} else {
		alert('첫 페이지입니다.');
	}
}

// 프로필 댓글 로딩
function loadComments() {
	commentPage = document.getElementById('comment_page').innerText;
	$.ajax({
		type: 'GET',
		url: 'selectComments',
		data: {
			startIdx: commentPage,
			id: profileId,
		},

		success: (list) => {

			if (list.commentList.length !== 0) {
				// 기존 페이지 글 제거
				$('.comments').empty();

				list.commentList.forEach((comment) => {
					// timestamp -> date -> format 변환
					var timestamp = comment.createdTime;
					var changeToDate = new Date(timestamp);
					var date = dateFormat(changeToDate);
					$('.comments').append(
						`
						<ul>
							<li>${comment.content}</li>
							<li>${date}</li>
							<br>
						</ul>
						`
					);
				});
			} else if (commentPage === '1' && list.commentList.length === 0) {
				$('.comments').append('<p>작성한 게시글이 없습니다..</p>')
			} 
			else {
				alert('마지막 페이지입니다.');
				var element = document.getElementById('comment_page');
				commentPage = Number(commentPage);
				commentPage -= 1;
				element.innerText = commentPage;
			}
		},

	});
}

function comment_plus_btn() {
	var element = document.getElementById('comment_page');
	commentPage = Number(commentPage);

	commentPage += 1;
	element.innerText = commentPage;
	loadComments();
}

function comment_minus_btn() {
	var element = document.getElementById('comment_page');
	commentPage = Number(commentPage);
	if (commentPage > 1) {

		commentPage -= 1;
		element.innerText = commentPage;
		console.log(commentPage);
		loadComments();
	} else {
		alert('첫 페이지입니다.');
	}
}


function dateFormat(date) {
	let month = date.getMonth() + 1;
	let day = date.getDate();
	let hour = date.getHours();
	let minute = date.getMinutes();
	let second = date.getSeconds();

	month = month >= 10 ? month : '0' + month;
	day = day >= 10 ? day : '0' + day;
	hour = hour >= 10 ? hour : '0' + hour;
	minute = minute >= 10 ? minute : '0' + minute;
	second = second >= 10 ? second : '0' + second;

	return month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}

