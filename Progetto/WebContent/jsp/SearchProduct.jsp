<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca</title>
<link href="css/product.css" rel="stylesheet">
</head>
<body>

	<%@include file="header.jsp"%>
	<div class="container tile navTop">
		<form action="SearchProduct?action=filter" method="POST">
		<div class="col-lg-6 col-sm-6">
			<h3>Filtra Categorie</h3>
			<input type=hidden value="${nomeProdotto}">
			<c:choose>
				<c:when test="${not empty cat}">
					<c:forEach var="c" items="${cat}">
						<div class="checkbox padding"><input name="categoria" type="checkbox" value="${c.id}"/> ${c.nome}</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h4>Nessuna SottoCategoria per questa Categoria</h4>
				</c:otherwise>
			</c:choose>
			</div>
			<div class="col-lg-3 col-sm-3">
			<h3>Ordina Per</h3>
			<p>
				<select name="order" class="form-control">
					<option value="Prezzo asc">Prezzo crescente</option>
					<option value="Prezzo desc">Prezzo decrescente</option>
				</select>
			</p>
			<input type="submit" class="btn btn-default" value="Cerca"/>
			</div>
		</form>
	</div>
	<div class="container tile navTop">
		<c:choose>
		<c:when test="${not empty prodotti}">
		<c:forEach items="${prodotti}" var="i">
			<div class="col-lg-12 col-sm-12 ">
				<div class="container">
					<div class="card">
						<div class="container-fliud">
							<div class="wrapper row">
								<div class="preview col-md-4">

									<div class="preview-pic tab-content">
										<div class="tab-pane active" id="pic-1">
											<img src="${i.prodotto.immagine}" >
										</div>
									</div>


								</div>
								<c:choose>
									<c:when test="${i.prodotto.inAsta==0}">
								<div class="details col-md-8">
									<h3 class="product-title">${i.prodotto.nome}</h3>
									<h4 class="price">Descrizione:</h4>
									${i.prodotto.descrizione}
									
									<h4 class="price">
										Prezzo: <span>${i.prodotto.prezzo} &euro;</span>
									</h4>
									
									<h4>
										Quantit&agrave;: <input type="number" max="${i.quantita}" class="num" min="1" value="${i.quantita}">
									</h4>
									
									<div class="action">
										<p><input class="add-to-cart btn btn-default" on="${i.idProdotto}" type="submit" value="Aggiungi al Carrello"/></p>
										<p><input class="buy-now btn btn-default" on="${i.idProdotto}" type="submit" value="Compralo Subito"/></p>
										<p>
										<form action="VisitProduct" name="myForm">
											<input type="hidden" name="idProdotto" value="${i.idProdotto}">  
											<button  class="btn btn-default" type="submit" >visita
												pagina prodotto</button>
										</form>
										</p>
									</div>
								</div>
								</c:when>
								<c:when test="${i.prodotto.inAsta==1}">
								<div class="details col-md-6">
									<h3 class="product-title">${i.prodotto.nome}</h3>
									<h4 class="price">Descrizione:</h4>
									<span>${i.prodotto.descrizione}</span>
									<h4 class="price">Data Inizio: <span>${i.dataInizio}</span></h4>
									<h4 class="price">Data Fine: <span>${i.dataFine}</span></h4>
									<h4 class="price">
										current price: <span>${i.prezzoCorrente} &euro;</span>
									</h4>
									<div class="action">
										<button class="btn btn-default" type="button">Fai un'offerta</button>
										<br> <br>
										<form action="VisitProduct" name="myForm">
										<input type="hidden" name="idProdotto" value="${i.idProdotto}">  
										<button  class="btn btn-default" type="submit" >visita
											pagina prodotto</button></form>
									</div>
								</div>
								</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</div>

			</div>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<center>
				<h1>nessun prodotto presente per i correnti parametri di ricerca</h1>
			</center>
		</c:otherwise>
		</c:choose>
	</div>
</body>
<script src="js/Search.js"></script>
</html>