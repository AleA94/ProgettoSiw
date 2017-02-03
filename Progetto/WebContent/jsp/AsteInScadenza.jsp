<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aste In Scadenza</title>

<link href="css/sp.css" rel="stylesheet">

<script src="js/aggiornaAste.js"></script>

</head>
<body>
<div class="container tile navTop">
	<h1 class=title>Aste in scadenza</h1>

	<div class="col-sm-12 col-lg-12">
		<div id="rangeAste">
			 <div class="range range-info"   > 
            	<input id="sliderOre" type="range" name="range" min="0" max="24" value="1"  >
            	<output id="rangeInfo1">1 ore</output>
         	 </div>  
        	 <div class="range range-info"   > 
        	    <input id="sliderGiorni" type="range" name="range" min="0" max="10" value="0"  >
            	<output id="rangeInfo2">0 giorni</output>
         	</div> 
         </div>
         <div id="asteInScadenza">
         <li id="divider"></li>
		<c:choose>
		<c:when test="${not empty asteProdotto}">
		
		<c:forEach var="c" items="${asteProdotto}">
		
			<div class="row">
				<div class="card">
					<div class="col-sm-4 col-lg-4">
						<div class="preview-pic tab-content">
							<div class="tab-pane active" id="pic-1">
								<div id="immagineAsta">
									<img id="immagine" src="${c.urlImg}"
										class="img-responsive" alt="">
								</div>
							</div>
						</div>
						<ul class="preview-thumbnail nav nav-tabs"></ul>
					</div>
					<div id="datiAsta" class="col-sm-4 col-lg-8">
						<h3 class="product-title">${c.nomeProdotto}</h3>
						<h3 class="description">Descrizione:</h3>
						<p id="ciao" class="product-description">${c.descrizioneProdotto}</p>
						<p class="product-description">data inizio asta: ${c.dataInizioAsta}</p>
						<p class="product-description">data fine asta: ${c.dataFineAsta}</p>
						<h4 class="price">
							current price: <span id="prezzoCorr">${c.prezzoCorrente}</span>
						</h4>
    					<div class="action">
							<button id="faiUnOfferta" on="${c.idAsta}" class="add-to-cart btn btn-default" type="button" >fai un offerta</button>
							<input type="text" id="offertaMassima" value="${c.prezzoCorrente+1}">
						</div>
					</div>
				</div>	
			</div>
		<li id="divider"></li>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="page-header">
					<h1 class=title>nessuna asta in scadenza per l'intervallo selezionato</h1>
				</div>
			</div>
		</c:otherwise>
		</c:choose>
	</div>
	</div>
</div>


</body>

</html>
