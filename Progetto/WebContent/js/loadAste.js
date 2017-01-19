$(document).ready(function()
{
$('.remove').click(function(e)
{
e.preventDefault();
var r = confirm('sei sicuro di voler rimuovere quest\'oggetto?');
if (r)
{
	rimuoviOggetto($(this).attr('on'));
	$(this).parent().parent().remove()
}

})

});

function rimuoviOggetto(v)
{
$.ajax(
{
	type : "GET",
	url : "LoadAste",
	datatype : "json",

});
}