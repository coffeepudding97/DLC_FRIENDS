$(document).ready(function(){
	const userId = $('#userId').text();
	$.ajax({
		"method":"POST",
		"url":`LoadRating?userId=${userId}`
	}).done(list => {
		$('#ul-rating').empty();
		list.forEach(rating => {
			$('#ul-rating').append(`
				<li name="line">
					<div class="line_post" onclick="formToggle(this)">
						<div><h1>글 번호</h1><p name="postNo">${rating.postNo}</p></div>
						<div><h1>글 제목</h1><p name="title">${rating.title}</p></div>
						<div><h1>같이한 게임</h1><p name="title">${rating.gametitle}</p></div>
						<div><h1>닉네임</h1><p name="userId">${rating.rated}</p></div>
					</div>
					<form>
						<div class="user_rating">
							<h1>유저 평가</h1>
							1<input type="radio" name="score" value="1">
							2<input type="radio" name="score" value="2">
							3<input type="radio" name="score" value="3">
							4<input type="radio" name="score" value="4">
							5<input type="radio" name="score" value="5" checked>
						</div>
						<div class="report">
							<label for="${rating.postNo}${rating.rated}curse">욕설</label>
							<input id="${rating.postNo}${rating.rated}curse" name="tag" type="checkbox" value="curse">
							<label for="${rating.postNo}${rating.rated}run">탈주</label>
							<input id="${rating.postNo}${rating.rated}run" name="tag" type="checkbox" value="run">
							<label for="${rating.postNo}${rating.rated}late">지각</label>
							<input id="${rating.postNo}${rating.rated}late" name="tag" type="checkbox" value="late">
							<label for="${rating.postNo}${rating.rated}disturb">게임진행방해</label>
							<input id="${rating.postNo}${rating.rated}disturb" name="tag" type="checkbox" value="disturb">
							<label for="${rating.postNo}${rating.rated}hack">불법프로그램사용</label>
							<input id="${rating.postNo}${rating.rated}hack" name="tag" type="checkbox" value="hack">
						</div>
						<div class="memo">
							<h1>메모</h1>
							<input id="content" name="content" type="textarea">
						</div>
						<div class="submit_wrap">
						<input type="button" class="submit" value="제출" onclick="rating(this)">
						</div>
					</form>
				</li>
			`);
		})
	})
});

$('.header_bottom a:nth-child(3)').addClass("on").siblings().removeClass("on");

function formToggle(info){
	const li = $(info).closest("li");
	const form = $(li).find("form");
	$(form).toggle();
}

function rating(button){
	$(button).prop("disabled", true);
	setTimeout(function(){
		$(button).prop("disabled", false);
	}, 300);
	const rater = $('#userId').text();
	const line = $(button).closest('li[name="line"]');
	const postNo = $(line).find('p[name="postNo"]').text();
	const rated = $(line).find('p[name="userId"]').text();
	const radio = $(line).find('input[name="score"]:checked').val();
	const content = $(line).find('input[name="content"]').val();
	const tags = $(line).find('input[name="tag"]:checked').map(function() {
  		return this.value;
	}).get();
	console.log(tags);
	var data = {
		rater,
		postNo,
		rated,
		radio,
		content,
		tags
	}
	
	$.ajax({
		"method":"POST",
		"url":`RatingForm`,
		"data":data
	}).done(res => {
		if(res === "true"){
			line.remove();
		}
	})
}


/* partys
$(document).ready(function(){
	const userId = $('#userId').text();
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/LoadRatingsAction?userId=${userId}`
	}).done(list => {
		$('#ul-rating').empty();
		list.forEach(party => {
			for(let i=0; i<party.userIds.length; i++){
				$('#ul-rating').append(`
					<li name="line">
						<div onclick="formToggle(this)">
							<div name="postNo">${party.postNo}</div>
							<div name="userId">${party.userIds[i]}</div>
							<div>${party.imageHtml[i]}</div>
						</div>
						<form>
							<div>
								<input type="radio" name="score" value="1">
								<input type="radio" name="score" value="2">
								<input type="radio" name="score" value="3">
								<input type="radio" name="score" value="4">
								<input type="radio" name="score" value="5">
							</div>
							<div>
								<label for="tag1">욕설</label>
								<input id="tag1" name="tag" type="checkbox" value="curse">
								<label for="tag2">탈주</label>
								<input id="tag2" name="tag" type="checkbox" value="run">
								<label for="tag3">지각</label>
								<input id="tag3" name="tag" type="checkbox" value="late">
								<label for="tag4">게임진행방해</label>
								<input id="tag4" name="tag" type="checkbox" value="disturb">
								<label for="tag5">불법프로그램사용</label>
								<input id="tag5" name="tag" type="checkbox" value="hack">
							</div>
							<div>
								<input id="content" name="content" type="textarea">
							</div>
							<input type="hidden" value="${party.userIds[i]}">
							<input type="hidden" value="${party.postNo}">
							<input type="button" value="제출" onclick="rating(this)">
						</form>
					</li>
				`);
			}
		})
	})
});
*/