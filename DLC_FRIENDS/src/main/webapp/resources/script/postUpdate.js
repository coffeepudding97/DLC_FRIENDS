$(document).ready(function(){
	const userId = $('#user_id').val();
	
	$.ajax({
		"method":"POST",
		"url":"http://localhost:8080/GetGameTitlesAction"
	}).done(list => {
		list.forEach(gametitle => {
			$('#gametitle').append(`
				<option value="${gametitle.gameTitle}">${gametitle.gameTitle}</option>
			`);
		})
	})
	
	$.ajax({
		"method":"POST",
		"url":`http://localhost:8080/GetFavorateGameAction?userId=${userId}`
	}).done(list => {
		
		list.forEach(game => {
			$('#div-gametitle').append(`
				<labal onclick="setGameTitle(this)" id="${game.gametitle}" value="${game.gametitle}">${game.gametitle}</label>
			`)
		})
	})
});

function setGameTitle(label){
	const name = $.trim($(label).text());
	
	$('#gametitle').val(name).prop("selected", true);
}