var page = 1;



$(document).ready(function() {
	$.ajax({
		"method": "POST",
		"url": `Warning`,
		"data": { "page": page}
	}).done(list => {
		page = page + 1;
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

$('.header_bottom a:nth-child(2)').addClass("on").siblings().removeClass("on");

/*$(window).on('scroll', function() {
	var windowHeight = $(window).height();
	var documentHeight = $(document).height();
	var scrollTop = $(window).scrollTop();
	console.log(scrollTop);
	if (scrollTop + windowHeight >= documentHeight - 1) {
		getMore();
	}
});*/

function getMore() {
	$.ajax({
		"method": "POST",
		"url": `Warning`,
		"data": { "page": page}
	}).done(list => {
		page = page + 1;
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
}