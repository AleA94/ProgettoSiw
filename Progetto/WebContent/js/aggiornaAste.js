function cambioData(value)
{
console.log(value);
var urs9 = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/Ast";
rangeInfo2.value = value + ' giorni';
$("#asteInScadenza").replaceWith("<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaaa </div>");
$.ajax(
{
	type : "GET",
	url : "Ast",
	datatype : "json",
	data :
	{
		day : JSON.stringify(value),
	},
	success : function(data)
	{
	for (var i = 0; i < data.length; i++)
		console.log(data[i].asta);
	},
	fail : function()
	{
	alert('niente');
	}
});

}