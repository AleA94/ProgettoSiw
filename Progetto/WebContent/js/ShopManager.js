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
		data : {
			idRemove : JSON.stringify(v),
		},
		success : function(data) {
			
		},
		fail : function() {
			alert('niente');
		}
	});
}