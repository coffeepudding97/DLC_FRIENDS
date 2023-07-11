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