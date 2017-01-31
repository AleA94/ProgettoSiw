

$(document).ready(function() {
	checkSession();

	$('#RVend').click(function(){
		$('#nomeNegozio').toggleClass('disappear');
	});
	
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
		if($('#RVend').is(':checked')){
			$('#RVend').val(1);
		}else{
			$('#RVend').val(0);
		}
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
		eVenditore:$('#RVend').val(),
		eAttivo:0
	}
	
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			register : JSON.stringify(utente),
			nome:$('#nomeNegozio').val()
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
		mimeType: "textPlain",
		data : {
			session : ' ',
		},
		success : function(data) {
			data=JSON.parse(data);
			if (data.log == true) {
				$('#user').toggleClass("disappear");
				$('#person').html(data.name);
				if(data.sell==1)
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
		mimeType: "textPlain",
		data : {
			logout : '',
		},
		success : function(data) {
			$('#user').toggleClass("disappear");
			$('#log').toggleClass('disappear');
			$('#shopLink').remove();
			 var href = $('.navbar-brand').attr('href');
		      window.location.href = href;
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
	var user={
			mail:$('#mail').val(),
			pass:$('#pass').val()
	}
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			user : JSON.stringify(user),
		},
		success : function(data) {
			if (data.log == true) {				
				$('#user').toggleClass("disappear");
				$('#person').html(data.name);
				if(data.sell==1)
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
