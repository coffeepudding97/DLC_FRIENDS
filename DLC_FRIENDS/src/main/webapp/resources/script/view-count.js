function viewPost() {
	const post_no = document.getElementById('post_no').value;
	
	if(parseInt(post_no) >= 1) {
		location.href = `/PostRead?post_no=${post_no}`;			
	}
}

function readPost(ul){
	// const post_no = $(ul).closest('li[name="post_no"]').val();
	const post_no = $(ul).find('li[name="post_no"]').val();
	console.log(post_no);
	
	if(parseInt(post_no) >= 1) {
		location.href = `/PostRead?post_no=${post_no}`;			
	}
}

$(document).ready(function(){
	$.ajax({
		"method":"GET",
		"url":`http://localhost:8080/main`
	}).done(list => {
		$('#lines').empty();
		list.forEach(post =>{
			$('#lines').append(`
				<ul class="table_content" onclick="readPost(this)">
					<li name="post_no" value="${post.postNo }">${post.postNo}</li>
					<li>${post.gameTitle}</li>
					<li>${post.title}</li>
					<li>${post.userId}</li>
					<li>${post.createdTime}</li>
					<li>${post.recruitMax}</li>
					<li>${post.viewCount}</li>
				</ul>
			`)
		})
	})
})