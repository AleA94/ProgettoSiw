function cambioData(value)
{
console.log(value);
var urs9 = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/Ast";
rangeInfo2.value = value + ' giorni';
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
	$("#asteInScadenza").replaceWith("<div id='asteInScadenza'></div>");
	$("#asteInScadenza").append("	<li id='divider'></li>");
	for (var i = 0; i < data.length; i++)
	{
		console.log(data[i].asta);
		var p = parseInt(data[i].prezzo) + 1;
		$("#asteInScadenza").append(
				"<div class='row'>" + "<div class='card'>" + "	<div class='col-sm-4 col-lg-4'>"
						+ "		<div class='preview-pic tab-content'>"
						+ "			<div class='tab-pane active' id='pic-1'>"
						+ "				<div id='immagineAsta'>" + " <img id='immagine'src="
						+ data[i].asta
						+ " class='img-responsive'>"
						+ "	</div>"
						+ "</div>"
						+ "</div>"
						+ "<ul class='preview-thumbnail nav nav-tabs'></ul>"
						+ "	</div>"
						+ "<div class='col-sm-4 col-lg-8'>"
						//
						+ " <h3 class='product-title'>"
						+ data[i].asta
						+ "</h3>"
						+ "		<h3 class='description'>Descrizione:</h3>"
						+ " <p id='ciao'class='product-description'>"
						+ data[i].descrizione
						+ "</p>"
						+ "		<h4 class='price'>"
						+ " current price:<span>"
						+ data[i].prezzo
						+ "</span>"
						+ "		</h4>"
						+ "		<div class='action'>"
						+ "			<button class='add-to-cart btn btn-default' type='button'>fai"
						+ "				un offerta</button>"
						+ " <input type='text' id='offertaMassima'placeholder="
						+ p
						+ ">"
						+ "		</div>"
						+ "	</div>"
						+ "		</div>	"
						+ "	</div>"
						+ "	<li id='divider'></li>"

						+ "</div>");
	}
	},
	fail : function()
	{
	alert('niente');
	}
});

}