var cnt = 1;
var search = "";
var select = "";

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
	console.log(`${cnt}`);
	$.ajax({
		"method":"GET",
		"url":`http://localhost:8080/main?cnt=${cnt}`
	}).done(list => {
		cnt = cnt + 1;
		$('#lines').empty();
		list.forEach(post =>{
			var data = splitTimeStamp(post.createdTime);
			$('#lines').append(`
				<ul class="table_content" onclick="readPost(this)">
					<li name="post_no" value="${post.postNo }">${post.postNo}</li>
					<li>${post.gameTitle}</li>
					<li>${post.title}</li>
					<li>${post.userId}</li>
					<li>${data.MM}-${data.DD} ${data.hh}:${data.mm}</li>
					<li>${post.recruitMax}</li>
					<li>${post.viewCount}</li>
				</ul>
			`)
		})
	})
})

/*$(window).on('scroll', function() {
  var windowHeight = $(window).height();
  var documentHeight = $(document).height();
  var scrollTop = $(window).scrollTop();

  if (scrollTop + windowHeight >= documentHeight) {
	getMorePosts();
  }
});*/

function getMorePosts(){
	$.ajax({
		"method":"GET",
		"url":`http://localhost:8080/main?cnt=${cnt}`
	}).done(list => {
		cnt = cnt + 1;
		list.forEach(post =>{
			var data = splitTimeStamp(post.createdTime);
			$('#lines').append(`
				<ul class="table_content" onclick="readPost(this)">
					<li name="post_no" value="${post.postNo }">${post.postNo}</li>
					<li>${post.gameTitle}</li>
					<li>${post.title}</li>
					<li>${post.userId}</li>
					<li>${data.MM}-${data.DD} ${data.hh}:${data.mm}</li>
					<li>${post.recruitMax}</li>
					<li>${post.viewCount}</li>
				</ul>
			`)
		})
	})
}

function getMoreSearchs(){
	let data = {
		"cnt":cnt,
		"search":search,
		"select":select
	}
	
	$.ajax({
		"method":"GET",
		"url":``,
		"data":data
	}).done(list => {
		cnt = cnt + 1;
		list.forEach(post =>{
			var data = splitTimeStamp(post.createdTime);
			$('#lines').append(`
				<ul class="table_content" onclick="readPost(this)">
					<li name="post_no" value="${post.postNo }">${post.postNo}</li>
					<li>${post.gameTitle}</li>
					<li>${post.title}</li>
					<li>${post.userId}</li>
					<li>${data.MM}-${data.DD} ${data.hh}:${data.mm}</li>
					<li>${post.recruitMax}</li>
					<li>${post.viewCount}</li>
				</ul>
			`)
		})
	})
}

function search(){
	let backup = cnt;
	cnt = 1;
	search = $('#search').val();
	select = $('#select option:selected').val();
	
	let data = {
		"cnt":cnt,
		"search":search,
		"select":select
	}
	
	$.ajax({
		"method":"GET",
		"url":``,
		"data":data
	}).done(list => {
		cnt = cnt + 1;
		$('#lines').empty();
		list.forEach(post =>{
			var data = splitTimeStamp(post.createdTime);
			$('#lines').append(`
				<ul class="table_content" onclick="readPost(this)">
					<li name="post_no" value="${post.postNo }">${post.postNo}</li>
					<li>${post.gameTitle}</li>
					<li>${post.title}</li>
					<li>${post.userId}</li>
					<li>${data.MM}-${data.DD} ${data.hh}:${data.mm}</li>
					<li>${post.recruitMax}</li>
					<li>${post.viewCount}</li>
				</ul>
			`)
		})
		$('')
	}).fail(e => {
		cnt = backup;
	})
}

function splitTimeStamp(ts){
	const YY = ts.substr(0,4);
	const MM = ts.substr(5,2);
	const DD = ts.substr(8,2);
	const hh = ts.substr(11,2);
	const mm = ts.substr(14,2);
	const ss = ts.substr(17,2);
	
	var date = {
		"YY":YY, "MM":MM, "DD":DD, "hh":hh, "mm":mm, "ss":ss
	}
	
	return date;
}