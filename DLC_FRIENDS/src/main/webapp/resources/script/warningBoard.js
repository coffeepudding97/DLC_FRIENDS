$(document).ready(function(){
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/GetWarningPostAction`
	}).done(list => {
		list.forEach(rating => {
			$('#ul-board').append(`
				<li>
					<div class="id">${rating.rated}</div>
					<div class="content">${rating.content}</div>
					<div class="score">${rating.score}</div>
					<div class="tags">
						<div class="curse tag${rating.curse}">욕설</div>
						<div class="run tag${rating.run}">탈주</div>
						<div class="late tag${rating.late}">지각</div>
						<div class="disturb tag${rating.disturb}">방해</div>
						<div class="hack tag${rating.hack}">핵</div>
					</div>
				</li>
			`)
		})
	})
});