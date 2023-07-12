$(document).ready(function(){
	const userId = $('#userId').text();
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/LoadRatingsAction?userId=${userId}`
	}).done(list => {
		$('#ul-rating').empty();
		list.forEach(rating => {
			
				$('#ul-rating').append(`
					<li name="line">
						<div onclick="formToggle(this)">
							<div name="postNo">${rating.postNo}</div>
							<div name="userId">${rating.userId}</div>
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
							<input type="button" value="제출" onclick="rating(this)">
						</form>
					</li>
				`);
			
		})
	})
});

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



function formToggle(info){
	const li = $(info).closest("li");
	const form = $(li).find("form");
	$(form).toggle();
}

function rating(button){
	const line = $(button).closest('li[name="line"]');
	const postNo = $(line).find('div[name="postNo"]').text();
	const userId = $(line).find('div[name="userId"]').text();
	const radio = $(line).find('input[name="score"]:checked').val();
	const tags = $('input[name="tag"]:checked').map(function() {
  		return this.value;
	}).get();
	
	var data = {
		postNo,
		userId,
		radio,
		tags
	}
	
	$.ajax({
		"method":"POST",
		"url":``,
		"data":data
	}).done()
}