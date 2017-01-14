

$(document).ready(function() {
	checkSession();
	fillHome();

	prevCat=$('#srcCat li');
	
	$("#login").click(function() {
		var mail = $('#mail');
		if (isEmail(mail.val()))
			Login();
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
	

$("#srcCat").on('click', 'li a', function(){
    prevCat.removeClass('active');
	prevCat=$(this).parent();
	$(this).parent().addClass('active');
    $("#selCat").text($(this).text());
});


});

var b = false;

function register() {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			register : JSON.stringify($('#Rnome').val() + ";"
					+ $('#Rcogn').val() + ";" + $('#Raddr').val() + ";"
					+ $('#Rmail').val() + ";" + $('#Rpass').val()),
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

function fillHome() {
	$.ajax({
		type : "POST",
		url : "HomeLoad",
		datatype : "json",
		data : {
			query : JSON.stringify('select * from Categoria where sottocategoria is null'),
		},success : function(data) {
			data = data.substring(0, data.length - 1);
			var cat = data.split(';');
			var row=$('<div class=row></div>');
			var i=0;
			cat.forEach(function(c) {
				$('.popup-gallery').append('<div class=\"col-lg-4 col-sm-6\"><a class=\"portfolio-box\"> <img src=\"immagini/categorie/'+c+
						'.jpg\" class=\"img-responsive\" alt=\"\"><div class=\"portfolio-box-caption\">	<div class=\"portfolio-box-caption-content\">	<div class=\"project-category text-faded\">Category</div><div class=\"project-name\">'+
						c+'</div>	</div>	</div></a></div>');
				$('#srcCat').append($('<li><a href="#">'+c+'</a></li>'));
			});
		},fail : function() {
			alert('niente');
		}
	});
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
