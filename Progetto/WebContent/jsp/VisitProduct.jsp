<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/product.css" rel="stylesheet">
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container tile navTop">
		<div class="col-lg-12 col-sm-12 ">
			<div class="container">
				<div class="card">
					<div class="container-fliud">
						<div class="wrapper row">
							<div class="preview col-md-4">

								<div class="preview-pic tab-content">
									<div class="tab-pane active" id="pic-1">
										<img src="${prodotto.getProdotto().immagine}" />
									</div>
									<c:forEach items="${prodotto.getProdotto().immaginiAggiuntive}"
										var="i">
										<div class="tab-pane" id="pic-2">
											<img src="${i}" class="editImage img-responsive" />
										</div>
										<div class="tab-pane" id="pic-3">
											<img src="${i}" class="editImage img-responsive" />
										</div>
										<div class="tab-pane" id="pic-4">
											<img src="${i}" class="editImage img-responsive" />
										</div>
									</c:forEach>
								</div>
								<ul class="preview-thumbnail nav nav-tabs">
									<li class="active"><a data-target="#pic-1"
										data-toggle="tab"><img
											src="${prodotto.getProdotto().immagine}" /></a></li>
									<c:forEach items="${prodotto.getProdotto().immaginiAggiuntive}"
										var="i">
										<li><a data-target="#pic-2" data-toggle="tab"><img
												src="${i}" class="editImage img-responsive" /></a></li>
		
									</c:forEach>
								</ul>

							</div>
							<c:choose>
								<c:when test="${prodotto.getProdotto().inAsta==0}">
									<div class="details col-md-6">

										<h3 class="product-title">${prodotto.getProdotto().nome}</h3>
										<h4 class="price">Descrizione:</h4>
										${prodotto.getProdotto().descrizione}
									
										<h4 class="price">
											Prezzo: <span>${prodotto.getProdotto().prezzo} &euro;</span>
										</h4>
										<h4>
											Quantit&agrave;: <input type="number" max="${prodotto.quantita}" class="num" min="1" value="${prodotto.quantita}">
										</h4>
										<div class="action">
											<p><input class="add-to-cart btn btn-default" on="${prodotto.idProdotto}" type="submit" value="Aggiungi al Carrello"/></p>
											<p><input class="buy-now btn btn-default" on="${prodotto.idProdotto}" type="submit" value="Compralo Subito"/></p>
										</div>
									</div>
								</c:when>
								<c:when test="${prodotto.getProdotto().inAsta==1}">
									<div class="details col-md-6">

										<h3 class="product-title">${prodotto.getProdotto().nome}</h3>
										<span>${prodotto.getProdotto().descrizione}</span>
										<h4 class="price">Data Inizio: <span>${prodotto.dataInizio}</span></h4>
										<h4 class="price">Data Fine: <span>${prodotto.dataFine}</span></h4>
										<h4 class="price">
											current price: <span>>${prodotto.getProdotto().inAsta}
												&euro;</span>
										</h4>
										<div class="action">
											<button class="btn btn-default" type="button">Fai
												un'offerta</button>

										</div>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="js/Search.js"></script>
</html>