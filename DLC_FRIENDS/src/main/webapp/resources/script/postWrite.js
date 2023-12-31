$(document).ready(function(){
	const userId = $('#user_id').val();
	
	getSessionLog(function(log){
		$.ajax({
			"method":"POST",
			"url":`GetFavorateGame?userId=${log}`
		}).done(list => {
			
			list.forEach(game => {
				$('.favorite_game_list').append(`
					<labal onclick="setGameTitle(this)" id="${game.gametitle}" class="played_game" value="${game.gametitle}">${game.gametitle}</label>
				`)
			})
		})
	})
	
	let curTime = new Date();
	let intmeetTime = new Date();
	let intleaveTime = new Date();
	timeFormat(curTime, 9);
	timeFormat(intmeetTime, 12);
	timeFormat(intleaveTime, 14);
	let formatCur = new Date(curTime).toISOString().slice(0, 16);
	let formatMeet = new Date(intmeetTime).toISOString().slice(0, 16);
	let formatLeave = new Date(intleaveTime).toISOString().slice(0, 16);
	
	$('#meettime').attr("min", formatCur);
	$('#meettime').val(formatMeet);
	$('#leavetime').val(formatLeave);
	$('#leavetime').attr("min", formatCur);	
	
	$.ajax({
		"method":"POST",
		"url":"GetGameTitles"
	}).done(list => {
		list.forEach(gametitle => {
			$('#gametitle').append(`
				<option value="${gametitle.gameTitle}">${gametitle.gameTitle}</option>
			`);
		})
	})
	
	
});

function setGameTitle(label){
	const name = $.trim($(label).text());
	
	$('#gametitle').val(name).prop("selected", true);
}

function timeFormat(time, offset){
	time.setHours(time.getHours() + offset);
	time.setMinutes(0);
	time.setSeconds(0);
	time.setMilliseconds(0);
}

function checkWrite(){
	const title = $('#title').val();
	$('#submit_btn').prop("disabled", true);
	
	setTimeout(function(){
		$('#submit_btn').prop("disabled", false);
	}, 300);
	
	if(title == ""){
		alert("제목을 입력해 주세요.");
		return false;
	} else {
		return true;
	}
}