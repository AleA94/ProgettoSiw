$(document).ready(function() {
	checkSession();

	$("#login").click(function() {
		var mail = $('#mail');
		if (isEmail(mail.val()))
			Login();
		else
			mail.css('border-color', 'red');

	});
	$("#logout").click(function(e) {
		e.preventDefault();
		logout();
	});

});

var b = false;

function checkSession() {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			session : JSON.stringify(" "),
		},
		success : function(data) {
			var res = data.split(";");
			if (res[0] == "true") {
				$('#user').toggleClass("disappear");
				$('#person').html(res[1]);
				$('#log').toggleClass('disappear');
				$('#login-modal').modal('hide');
			}
		},
		fail : function() {
			alert('niente');
		}
	})
}

function logout() {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			logout : JSON.stringify(" "),
		},
		success : function(data) {
			$('#user').toggleClass("disappear");
			$('#log').toggleClass('disappear');
		},
		fail : function() {
			alert('niente');
		}

	})
}

function isEmail(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return regex.test(email);
}

function Login() {
	var account = {
		user : $('#mail').val(),
		password : $('#pass').val(),
	}
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			user : JSON.stringify(account),
		},
		success : function(data) {
			var res = data.split(";");
			if (res[0] == "true") {
				$('#user').toggleClass("disappear");
				$('#person').html(res[1]);
				$('#log').toggleClass('disappear');
				$('#login-modal').modal('hide');
			} else {
				if (b != "true") {
					$("<b>username or password invalid<b>").insertBefore(
							$('#mail'));
					b = "true";
				}
			}
		},
		fail : function() {
			alert('niente');
		}
	})
}
