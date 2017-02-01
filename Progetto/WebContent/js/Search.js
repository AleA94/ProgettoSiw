$(document).ready(function(){
	$('.add-to-cart').click(function(e){
		e.preventDefault();
		if($('#user').hasClass('disappear')){
			alert('devi effettuare il login prima di poter aggiungere un prodotto al carrello oppure registrati');
		}else{
			var btn=$(this);
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
			})
		}
	});
	$('.buy-now').click(function(e){
		e.preventDefault();
		if($('#user').hasClass('disappear')){
			alert('devi effettuare il login prima di poter acquistare un prodotto oppure registrati');
		}else{
			var btn=$(this);
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
				},
				fail : function() {
					alert('niente');
				}
			})
		}
	});
})