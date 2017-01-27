$(document).ready(function() {
	$('.remove').click(function(e){
		e.preventDefault();
		var r=confirm('sei sicuro di voler rimuovere quest\'oggetto?');
		if(r){
			rimuoviOggetto($(this).attr('on'));
			$(this).parent().parent().remove()
		}
		
	})
	
});

function rimuoviOggetto(v){
	$.ajax({
		type : "POST",
		url : "ShopManager",
		datatype : "json",
		mimeType: "textPlain",
		data : {
			idRemove : v,
		},
		success : function(data) {
			if($('tr').length==1)
				window.location.href=window.location.href;
		},
		fail : function() {
			alert('niente');
		}
	});
}