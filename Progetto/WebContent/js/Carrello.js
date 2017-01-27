$(document).ready(function(){
	$('.remove').click(rimuovi);
	$('.buy').click(function(e){
		e.preventDefault();
		$(this).attr('href',$(this).attr('href')+$(this).parent().prev().children().first().val())
		window.location.href = $(this).attr('href');
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
