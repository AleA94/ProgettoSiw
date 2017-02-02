$(document).ready(function()
{

$('#sliderOre').change(function()
{
cambioData($('#sliderGiorni').val(), $(this).val())
})

$('#sliderGiorni').change(function()
{
cambioData($(this).val(), $('#sliderOre').val())

});

})

$(document).on('click', '.add-to-cart', function()
{
	var prezzoCorrente = $(this).parent().prev().children().text();
	var offerta = parseInt(prezzoCorrente)+1;
	var offertaMax=$(this).next().val();
	if(offertaMax==""){
		alert("inserire un offerta massima")
	}else{
		if(parseFloat(prezzoCorrente)>=parseFloat(offertaMax))
			alert('l\'offerta massima deve essere maggiore dell\'importo corrente');
		else
			offertaLampo($(this).attr('on'), $('#sliderGiorni').val(), $('#sliderOre').val(),prezzoCorrente,offerta,offertaMax)
	}
})

function cambioData(value, value1)
{
var urs9 = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/Ast";
rangeInfo2.value = value + ' giorni';
rangeInfo1.value = value1 + ' ore';
$.ajax(
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
	if(data.length==0){
		$("#asteInScadenza").append('<div class="row"><div class="page-header"><h1 style="text-align: center">nessuna asta in scadenza per l\'intervallo selezionato</h1></div></div>');
	}else{
	for (var i = 0; i < data.length; i++)
	{
		var p = parseInt(data[i].prezzo) + 1;
		$("#asteInScadenza").append(
				"<div class='row'>" + "<div class='card'>" + "	<div class='col-sm-4 col-lg-4'>"
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
						+ "<div  id='datiAsta' class='col-sm-4 col-lg-8'>"
						//
						+ " <h3 class='product-title'>"
						+ data[i].asta
						+ "</h3>"
						+ "		<h3 class='description'>Descrizione:</h3>"
						+ " <p id='ciao'class='product-description'>"
						+ data[i].descrizione
						+ "</p>"
						+ "<p class='product-description'>data inizio asta: "
						+ data[i].dataInizio
						+ "</p>"
						+ "<p class='product-description'>data fine asta: "
						+ data[i].dataFine
						+ "</p>"
						+ "		<h4 class='price'>"
						+ " current price:<span id='prezzoCorr'>"
						+ data[i].prezzo
						+ "</span>"
						+ "		</h4>"
						+ "		<div class='action'>"
						+ "			<button class='add-to-cart btn btn-default' type='button' on="
						+ data[i].idAsta
						+ ">fai"
						+ "				un offerta</button>"
						+ " <input type='text' id='offertaMassima'value="
						+ p
						+ ">"
						+ "		</div>"
						+ "	</div>"
						+ "		</div>	"
						+ "	</div>"
						+ "	<li id='divider'></li>"

						+ "</div>");
		}
	}
	},
	fail : function()
	{
	alert('niente');
	}
});

}

function offertaLampo(idasta, value, value1,prezzoCorrente,offerta,offertaMax)
{
if ($('#user').hasClass('disappear'))
{
	alert('devi effettuare il login prima di poter effettuare un offerta');
}
else
{
	if (offertaMax == "")
		offertaMax = parseInt(prezzoCorrente) + 1;
	$.ajax(
	{
		type : "GET",
		url : "Asta",
		datatype : "json",
		data :
		{
			asta : idasta,
			prezzoCor : prezzoCorrente,
			off : offerta,
			offMax : offertaMax
		},
		success : function(data)
		{
		cambioData(value, value1);

		if (data == "attualeVincitore")
		{
			alert('sei il vincitore attuale');

		}
		else if (data == "OffertaMaxBassa")
		{
			alert('la tua offerta \u00e9 stata superata');

		}
		else if (data == "OffertaMaxMinoreImporto")
		{
			alert('offerta massima minore dell\' importo offerto');

		}

		},
		fail : function()
		{
		alert('niente');
		}
	});

}
}
