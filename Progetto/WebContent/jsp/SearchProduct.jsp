<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link href="css/product.css" rel="stylesheet">
</head>
<body>

	<%@include file="header.jsp"%>
	<div class="container tile navTop">
		<c:forEach items="${prodotti}" var="i">
			<div class="col-lg-12 col-sm-12 ">
				<div class="container">
					<div class="card">
						<div class="container-fliud">
							<div class="wrapper row">
								<div class="preview col-md-6">

									<div class="preview-pic tab-content">
										<div class="tab-pane active" id="pic-1">
											<img src="${i.getProdotto().immagine}" >
										</div>
									</div>


								</div>
								<c:choose>
									<c:when test="${i.getProdotto().inAsta==0}">
								<div class="details col-md-6">
									<h3 class="product-title">${i.getProdotto().nome}</h3>
									<p class="product-description">>${i.getProdotto().descrizione}</p>
									
									<h4 class="price">
										current price: <span>>${i.getProdotto().prezzo} &euro;</span>
									</h4>
									<div class="action">
										<button class="add-to-cart btn btn-default" type="button">add
											to cart</button>
										<br> <br>
										<form action="VisitProduct" name="myForm">
										<input type="hidden" name="idProdotto" value="${i.idProdotto}">  
										<button  class="add-to-cart btn btn-default" type="submit" >visita
											pagina prodotto</button></form>
									</div>
								</div>
								</c:when>
								<c:when test="${i.getProdotto().inAsta==1}">
								<div class="details col-md-6">
									<h3 class="product-title">${i.getProdotto().nome}</h3>
									<p class="product-description">>${i.getProdotto().descrizione}</p>
									<p class="product-description">${i.dataInizio}</p>
									<p class="product-description">${i.dataFine}</p>
									<h4 class="price">
										current price: <span>>${i.getProdotto().inAsta} &euro;</span>
									</h4>
									<div class="action">
										<button class="add-to-cart btn btn-default" type="button">add
											to cart</button>
										<br> <br>
										<button class="add-to-cart btn btn-default" type="button">Fai un'offerta</button>
										<br> <br>
										<form action="VisitProduct" name="myForm">
										<input type="hidden" name="idProdotto" value="${i.idProdotto}">  
										<button  class="add-to-cart btn btn-default" type="submit" >visita
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
		<c:forEach items="${emptyP}" var="k">
			<center>
				<h1>${k}</h1>
			</center>
			<br>
			<center>
				<img src="immagini/varie/error.png"></img>
			</center>
		</c:forEach>
	</div>

</body>
</html>