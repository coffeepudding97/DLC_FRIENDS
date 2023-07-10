function viewPost() {
	const post_no = document.getElementById('post_no').value;
	
	if(parseInt(post_no) >= 1) {
		location.href = `/PostRead?post_no=${post_no}`;			
	}
}