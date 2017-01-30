$(document).ready(function() {
	$('.remove').click(function(e) {
		e.preventDefault();
		var id = $(this).attr('on');
		var tr = $(this).parent().parent();
		$.ajax({
			type : "POST",
			url : "ProfileManager",
			datatype : "json",
			mimeType : "textPlain",
			data : {
				rimuovi : id,
			},
			success : function(data) {
				tr.remove();
				if($('tr').length==1)
					window.location.href=window.location.href;
			},
			fail : function() {
				alert('niente');
			}
		})
	})
	
	$('.addCart').click(function(e){
		e.preventDefault();
		var tr= $(this).parent().parent();
		var prodotto={
			id:$(this).attr('on'),
			qt:$(this).parent().prev().children().val()
		}
		$.ajax({
			type : "POST",
			url : "ProfileManager",
			datatype : "json",
			data : {
				addCart : JSON.stringify(prodotto),
			},
			success : function(data) {
				alert('il prodotto \xE8 stato aggiunto con successo al carrello');
				tr.remove();
				if($('tr').length==1)
					window.location.href=window.location.href;
			},
			fail : function() {
				alert('niente');
			}
		})
	})
	
	$('.buy').click(function(e){
		e.preventDefault();
		var tr= $(this).parent().parent();
		var prodotto={
			id:$(this).attr('on'),
			qt:$(this).parent().prev().prev().children().val()
		}
		$.ajax({
			type : "POST",
			url : "ProfileManager",
			datatype : "json",
			data : {
				buyNow : JSON.stringify(prodotto),
			},
			success : function(data) {
				alert('il prodotto \xE8 stato acquistato con successo');
				tr.remove();
				if($('tr').length==1)
					window.location.href=window.location.href;
			},
			fail : function() {
				alert('niente');
			}
		})
	})
	
	$('.buyAll').click(function(){
		
	})
})