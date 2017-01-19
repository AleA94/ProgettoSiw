

$(document).ready(function() {
	checkSession();

    $('.search-panel .dropdown-menu').find('a').click(function(e) {
    	e.preventDefault();
		var param = $(this).attr("href").replace("#","");
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		$('.input-group #search_param').val(param);
	});
	
	$("#login").click(function() {
		var mail = $('#mail');
		if (isEmail(mail.val()))
			Login($(this).attr('context'));
		else
			mail.css('border-color', 'blue');

	});
	$("#logout").click(function(e) {
		e.preventDefault();
		logout();
	});

	$("#register").click(function(e) {
		e.preventDefault();
		register();
	});
	
});

var b = false;

function register() {
	var utente={
		email:$('#Rmail').val(),
		password:$('#Rpass').val(),
		nome:$('#Rnome').val(),
		cognome:$('#Rcogn').val(),
		indirizzo: $('#Raddr').val(),
		eVenditore:0,
		eAttivo:0
	}
	
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			register : JSON.stringify(utente),
		},
		success : function(data) {
			alert('registrazione completata con successo');
			$('#register-modal').modal('hide');
		},
		fail : function() {
			alert('niente');
		}
	})
}

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
				if(res[2]==1)
					$('.user-menu').prepend($('<li id="shopLink"><a href="'+$('#context').val()+'/ShopManager">Gestisci il tuo negozio</a></li>'));
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
			$('#shopLink').remove();
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

function Login(c) {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			user : JSON.stringify( $('#mail').val()+";"+$('#pass').val()),
		},
		success : function(data) {
			var res = data.split(";");
			if (res[0] == "true") {
				$('#user').toggleClass("disappear");
				$('#person').html(res[1]);
				if(res[2]==1)
					$('.user-menu').prepend($('<li id="shopLink"><a href="'+$('#context').val()+'/ShopManager">Gestisci il tuo negozio</a></li>'));
				$('#log').toggleClass('disappear');
				$('#login-modal').modal('hide');
				$('#mail').val('');
				$('#pass').val('');
			} else {
				alert('nome utente o password errati!!!')
			}
		},
		fail : function() {
			alert('niente');
		}
	})
}
