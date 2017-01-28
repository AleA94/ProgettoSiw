$(document).ready(function(){
	$('#inAsta').click(function(){
		$('#date').toggleClass('disappear');
		$('.notAsta').toggleClass('disappear');
	})
	
	$('.morePhotos').click(function(e){
		e.preventDefault();
		if($('input[name=photos]').length==0)
			$(this).prepend();
		$(this).parent().prepend($('<input type="text" name="photos" placeholder="inserisci URL" class="form-control"/>'));
	});
	$('.lessPhotos').click(function(e){
		e.preventDefault();
		if($('input[name=photos]').length!=0)
			$('input[name=photos]').last().remove();
	})
	$('input[value=salva]').click(function(e){
		var n=$('input[name=photos]').filter(function(){
		    return !$(this).val();
		}).length;
		
		if(n!=0){
			e.preventDefault();
			alert('completare campi foto oppure rimuoverli (a meno che non sia l\'immagine principale che va compilata obbligatoriamente\)');
		
		}
	});
	
});