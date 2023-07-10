function checks(){
		//값 불러오기
        var getId = document.getElementById("id");
        var getPw = document.getElementById("password");
        var getPwCheck = document.getElementById("password_check");
        var getnickName = document.getElementById("nickname");
        var getMail = document.getElementById("mail");
        var getbirth = document.getElementById("birth")
        
        
        
        var regExp = /^[a-zA-Z0-9]{4,12}$/; //id, password
        var regName = /^[가-힝]{2,}$/; //name
        var regMail = /[a-z0-9]{2,}@[a-z0-9-]{2,}.[a-z0-9]{2,}/i; //mail
        
        
}