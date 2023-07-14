$(document).ready(function(){
	getSessionLog(function(log){
		if(log == "null"){
			$("#rating_link").attr("href", "/login");
		} else {
			$("#rating_link").attr("href","/rating");
		}
	})
})

function getSessionLog(callback){
	$.ajax({
		"method":"POST",
		"url":"http://localhost:8080/GetSession"
	}).done(log => {
		callback(log);
	})
}

function goLogin(){
	location.href = "/login";
}