function cambioData(value, value1)
{
var urs9 = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/Ast";
rangeInfo2.value = value + ' giorni';
rangeInfo1.value = value1 + ' ore';
console.log(value + " " + value1);
$
		.ajax(
		{
			type : "GET",
			url : "Asta",
			datatype : "json",
			data :
			{
				day : value,
				ore : value1,
			},
			success : function(data)
			{
			$("#asteInScadenza").replaceWith("<div id='asteInScadenza'></div>");
			$("#asteInScadenza").append("	<li id='divider'></li>");
			for (var i = 0; i < data.length; i++)
			{
				console.log(data[i].asta);
				var p = parseInt(data[i].prezzo) + 1;
				$("#asteInScadenza")
						.append(
								"<div class='row'>" + "<div class='card'>"
										+ "	<div class='col-sm-4 col-lg-4'>"
										+ "		<div class='preview-pic tab-content'>"
										+ "			<div class='tab-pane active' id='pic-1'>"
										+ "				<div id='immagineAsta'>" + " <img id='immagine'src="
										+ data[i].urlImage
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
										+ " current price:<span id='prezzoCorr'>"
										+ data[i].prezzo
										+ "</span>"
										+ "		</h4>"
										+ "		<div class='action'>"
										+ "			<button class='add-to-cart btn btn-default' type='button'  onclick='offertaLampo("
										+ data[i].idAsta
										+ ","
										+ value
										+ ","
										+ value1
										+ ")'>fai"
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

function offertaLampo(idasta, value, value1)
{
console.log(idasta);
if ($('#user').hasClass('disappear'))
{
	alert('devi effettuare il login prima di poter effettuare un offerta');
}
else
{
	var prezzoCorrente = parseInt($("#prezzoCorr").text());
	var offerta = parseInt($("#prezzoCorr").text()) + 1;
	var offertaMax = parseInt($("#offertaMassima").val());
	$.ajax(
	{
		type : "GET",
		url : "Asta",
		datatype : "json",
		data :
		{
			asta : JSON.stringify(idasta),
			prezzoCor : JSON.stringify(prezzoCorrente),
			off : JSON.stringify(offerta),
			offMax : JSON.stringify(offertaMax)
		},
		success : function(data)
		{
		cambioData(value, value1);

		console.log("asdkajhksdjashdj " + value + " " + value1)
		},
		fail : function()
		{
		alert('niente');
		}
	});

}
}
