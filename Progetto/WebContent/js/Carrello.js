$(document).ready(function(){
	$('.remove').click(rimuovi);
	$('.buy').click(function(e){
		e.preventDefault();
		
		var href=$(this).attr('href');
		var q=$(this).parent().prev().children().first().val();
		$.ajax({
			type : "POST",
			url : "Carrello",
			datatype : "json",
			mimeType: "textPlain",
			data : {
				idQuantity : $(this).attr('on'),
				quantity:q,
			},
			success : function(data) {
				console.log(data);
				if(data=="false"){
					window.location.href = href+q;
				}else{
					alert('la quantità selezionata non è più disponibile');
				}
			},
			fail : function() {
				alert('niente');
			}
		});
		
		
		
	});
})

function rimuovi(e){
	e.preventDefault();
	var tr=$(this).parent().parent();
	$.ajax({
		type : "POST",
		url : "Carrello",
		datatype : "json",
		mimeType: "textPlain",
		data : {
			rimuovi : $(this).attr('on'),
		},
		success : function(data) {
			tr.remove();
			if($('tr').length==1)
				window.location.href=window.location.href;
		},
		fail : function() {
			alert('niente');
		}
	});
}
