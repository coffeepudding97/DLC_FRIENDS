function loginChk(htmlform){
	const id = htmlform.id.value;
	const pw = htmlform.password.value;
	console.log(id);
}


$('#id').on('change', e => {
	const id = $('#id').val();
	if(id !== "") {
		$('#error-id').hide();
	}
});