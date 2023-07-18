var cnt = 1;
var search = "";
var select = "";
var tag = "undefined";

$(document).ready(function(){
	tag = getSearchTag();
	
	if(tag == "undefined"){
		$.ajax({
			"method":"GET",
			"url":`main?cnt=${cnt}`
		}).done(list => {
			cnt = cnt + 1;
			$('#lines').empty();
			list.forEach(post =>{
				var data = splitTimeStamp(post.createdTime);
				appendAni(post, data);
			})
			
			getSessionLog(function(log){
				if(log == "null"){
					$("#writing_link").attr("href", "/login");
					$(".table_content").removeAttr("onclick");
					$(".table_content").attr("onclick", "goLogin()");
				} else {
					$("#writing_link").attr("href", "/postWrite");
				}
			})
		})
	} else {
		searchPost();
	}
	
	
	$('#search').on('keyup', function(key){
		if(key.keyCode==13){
			searchPost();
		}
	})
})

function viewPost() {
	const post_no = document.getElementById('post_no').value;
	
	if(parseInt(post_no) >= 1) {
		location.href = `PostRead?post_no=${post_no}`;			
	}
}

function readPost(ul){
	// const post_no = $(ul).closest('li[name="post_no"]').val();
	const post_no = $(ul).find('li[name="post_no"]').val();
	console.log(post_no);
	
	if(parseInt(post_no) >= 1) {
		location.href = `PostRead?post_no=${post_no}`;			
	}
}



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
		"url":`main?cnt=${cnt}`
	}).done(list => {
		cnt = cnt + 1;
		list.forEach(post =>{
			var data = splitTimeStamp(post.createdTime);
			appendAni(post, data);
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
		"url":`Search`,
		"data":data
	}).done(list => {
		cnt = cnt + 1;
		list.forEach(post =>{
			var data = splitTimeStamp(post.createdTime);
			appendAni(post, data);
		})
	})
}

function searchPost(){
	let backup = cnt;
	cnt = 1;
	search = $('#search').val();
	select = $('#search_select option:selected').val();
	if(tag != "undefined"){
		search = tag;
		select = "gametitle";
		tag = "undefined";
	}
	
	let data = {
		"cnt":cnt,
		"search":search,
		"select":select
	}
	
	$.ajax({
		"method":"GET",
		"url":`Search`,
		"data":data
	}).done(list => {
		cnt = cnt + 1;
		$('#lines').empty();
		list.forEach(post =>{
			var data = splitTimeStamp(post.createdTime);
			appendAni(post, data);
		})
		$('#more_btn').removeAttr("onclick");
		$('#more_btn').attr("onclick", "getMoreSearchs()");
	}).fail(e => {
		cnt = backup;
	})
}

function getSearchTag(){
	let url = window.location.href;
	let tag = decodeURIComponent(url.split("search=")[1]);
	return tag;
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

function appendAni(post, data){
	var data = splitTimeStamp(post.createdTime);
	var line = $('<ul class="table_content" onclick="readPost(this)">')
			      .append($('<li name="post_no">').val(post.postNo).text(post.postNo))
			      .append($('<li>').text(post.gameTitle))
			      .append($('<li>').text(post.title))
			      .append($('<li>').text(post.userId))
			      .append($('<li>').text(data.MM+"-"+data.DD+" "+data.hh+":"+data.mm))
			      .append($('<li>').text(post.recruitMax))
			      .append($('<li>').text(post.viewCount))
			      .hide();
	$('#lines').append(line);
	line.slideDown(500);
}