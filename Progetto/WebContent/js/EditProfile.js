$(document).ready(function(){
	$('#oldPass').focusout(function(){
		checkPassword($(this).val());
	});
	$('#newPass').focusout(function(){
		if($(this).val()!='')
			$("#confirmPass").prop('disabled', false);
		else
			$("#confirmPass").prop('disabled', false);
	});
	$('#confirmPass').focusout(function(){
		if($(this).val()==$('#newPass').val())
			$("#savePass").prop('disabled', false);
		else
			$("#savePass").prop('disabled', true);
	});
});

function checkPassword(a){
	$.ajax({
		type : "POST",
		url : "ProfileManager",
		datatype : "json",
		mimeType: "textPlain",
		data : {
			passCheck : a,
		},
		success : function(data) {
			if(data=="true")
				$("#savePass").prop('disabled', false);
			else{
				$("#savePass").prop('disabled', true);
				alert('la password corrente non \u00e9 corretta');
			}
		},
		fail : function() {
			alert('niente');
		}
	})
}