<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wishlist</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container tile navTop">
	<div class="row">
		<div class="page-header">
			<h1 class=title>La tua wishlist</h1>
		</div>
	</div>
	<c:choose>
	<c:when test="${not empty wishlist}">
		<form action="ProfileManager?action=buyNowAll" method="POST">
		<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Immagine</th>
					<th>Nome</th>
					<th>Descrizione</th>
					<th>Prezzo Unitario</th>
					<th>in Asta</th>
					<th>Visita pagina prodotto</th>
					<th>Rimuovi</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${wishlist}">
					<tr>
					<c:if test="${p.prodotto.inAsta eq 0}">
						<input type="hidden" name="id" value="${p.prodotto.idProdotto}">
					</c:if>
						<td><img class="pic" src="${p.prodotto.immagine}"></td>
						<td>${p.prodotto.nome}</td>
						<td>${p.prodotto.descrizione}</td>
						<td>${p.prodotto.prezzo}&euro;</td>
						<c:choose>
							<c:when test="${p.prodotto.inAsta==1}">
								<td>Si</td>
							</c:when>		
							<c:otherwise>
								<td>No</td>
							</c:otherwise>				
						</c:choose>
						<td><a href="<c:url value="/VisitProduct?idProdotto=${p.prodotto.idProdotto}"/>" title="visita pagina"><span class="glyphicon glyphicon-edit"></span></a></td>
						<td><a class="remove" on="${p.prodotto.idProdotto}" href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input type="submit" class="btn btn-default" value="Compra subito tutti">
	</form>
	</c:when>	
	<c:otherwise>
		<h1>nessun prodotto presente nella lista dei desideri</h1>
	</c:otherwise>
	</c:choose>
</section>
</body>
<script src="js/wishlist.js"></script>
</html>