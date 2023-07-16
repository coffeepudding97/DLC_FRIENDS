$(document).ready(function(){
	const userId = $('#user_id').val();
	const name = $('#selected').val();
	var selected = "";
	
	$.ajax({
		"method":"POST",
		"url":"http://localhost:8080/GetGameTitles"
	}).done(list => {
		list.forEach(gametitle => {		
			if(gametitle.gameTitle === name){
				selected = "selected";
			}else{
				selected = "";
			}
			$('#gametitle').append(`
				<option value="${gametitle.gameTitle}" ${selected}>${gametitle.gameTitle}</option>
			`);
		})
	})
	
	$('#gametitle').val(name).prop("selected", true);
	
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/GetFavorateGame?userId=${userId}`
	}).done(list => {
		
		list.forEach(game => {
			$('#div-gametitle').append(`
				<labal onclick="setGameTitle(this)" id="${game.gametitle}" value="${game.gametitle}">${game.gametitle}</label>
			`)
		})
	})
	
	let curTime = new Date();
	timeFormat(curTime, 9);
	let formatCur = new Date(curTime).toISOString().slice(0, 16);
	
	$('#meettime').attr("min", formatCur);

	$('#leavetime').attr("min", formatCur);
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