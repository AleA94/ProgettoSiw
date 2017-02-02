$(document).ready(function(){
	$('.add-to-cart').click(function(e){
		e.preventDefault();
		if($('#user').hasClass('disappear')){
			alert('devi effettuare il login prima di poter aggiungere un prodotto al carrello oppure registrati');
		}else{
			var btn=$(this);
			var q=parseInt(btn.parent().parent().prev().children().val());
			var max=parseInt(btn.parent().parent().prev().children().attr('max'));
			if(q<=max){
				var product={
					id:btn.attr('on'),
					qt:btn.parent().parent().prev().children().val()
				}
				$.ajax({
					type : "POST",
					url : "Carrello",
					datatype : "json",
					data : {
						addCart : JSON.stringify(product),
					},
					success : function(data) {
						alert('il prodotto \xE8 stato aggiunto con successo al carrello');
					},
					fail : function() {
						alert('niente');
					}
				});
			}
		}
	});
	$('.buy-now').click(function(e){
		e.preventDefault();
		if($('#user').hasClass('disappear')){
			alert('devi effettuare il login prima di poter acquistare un prodotto oppure registrati');
		}else{
			var btn=$(this);
			var q=parseInt(btn.parent().parent().prev().children().val());
			var max=parseInt(btn.parent().parent().prev().children().attr('max'));
			if(q<=max){
				var product={
					id:btn.attr('on'),
					qt:btn.parent().parent().prev().children().val()
				}
				$.ajax({
					type : "POST",
					url : "Carrello",
					datatype : "json",
					data : {
						buyNow : JSON.stringify(product),
					},
					success : function(data) {
						alert('il prodotto \xE8 stato acquistato con successo');
						btn.parent().parent().prev().children().attr('max',btn.parent().parent().prev().children().attr('max')-btn.parent().parent().prev().children().val())
					},
					fail : function() {
						alert('niente');
					}
				})
			}
		}
	});
	
	$('.add-to-wish').click(function(e){
		e.preventDefault();
		if($('#user').hasClass('disappear')){
			alert('devi effettuare il login prima di poter aggiungere un prodotto alla wishlist oppure registrati');
		}else{
			var product={
					id:$(this).attr('on'),
					qt:$(this).parent().parent().prev().children().val()
			}
			$.ajax({
				type : "POST",
				url : "ProfileManager",
				datatype : "json",
				data : {
					wishlist : JSON.stringify(product),
				},
				success : function(data) {
					alert('il prodotto \xE8 stato inserito con successo nella wishlist');
				},
				fail : function() {
					alert('niente');
				}
			})
			
		}
	});
	
	$('.make-offer').click(function(){
		var prezzoCorrente=$(this).parent().parent().prev().children();
		var offertaMassima=$(this).next();
		if(offertaMassima.val()==""){
			alert('inserire un valore per le offerta massima');
		}else{
			if(parseFloat(prezzoCorrente.text())>=parseFloat(offertaMassima.val()))
				alert('l\'offerta massima deve essere maggiore dell\'importo corrente');
			else{
				offertaLampo($(this).attr('on'),prezzoCorrente,offertaMassima.val());
			}
		}
		
	});
	
	
	function aggiornaAsta(idAsta,prezzoCorrente){
		$.ajax({
			type : "GET",
			url : "Asta",
			datatype : "json",
			data :
			{
				aggiorna : idAsta,
			},
			success : function(data){
				var prodotto=JSON.parse(data);
				prezzoCorrente.text(prodotto.prezzoCorrente);
			},
			fail : function()
			{
			alert('niente');
			}
		});
	}
	
	function offertaLampo(idasta,prezzoCorr,offertaMassima){
	if ($('#user').hasClass('disappear')){
		alert('devi effettuare il login prima di poter effettuare un offerta');
	}else{
		var prezzoCorrente = parseInt(prezzoCorr.text());
		var offerta = parseInt(prezzoCorrente) + 1;
		var offertaMax;
		if (offertaMassima != "")
		{
			offertaMax = parseInt(offertaMassima);
		}
		else
			offertaMax = prezzoCorrente + 1;
		$.ajax(
		{
			type : "GET",
			url : "Asta",
			datatype : "json",
			data :
			{
				asta : idasta,
				prezzoCor : prezzoCorrente,
				off : offerta,
				offMax : offertaMax
			},
			success : function(data){

			if (data == "attualeVincitore")
			{
				alert('sei il vincitore attuale');

			}
			else if (data == "OffertaMaxBassa")
			{
				alert('la tua offerta \u00e9 stata superata');

			}
			else if (data == "OffertaMaxMinoreImporto")
			{
				alert('offerta massima minore dell\' importo offerto');

			}
			aggiornaAsta(idasta,prezzoCorr);
			},
			fail : function()
			{
			alert('niente');
			}
		});
		}
	};
})