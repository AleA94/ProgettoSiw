<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestisci negozio</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<section class="container tile navTop">
	<div class="row">
		<div class="page-header">
			<h1 style="text-align: center">il tuo negozio ${nome}</h1>
		</div>
	</div>
	<a href="<%=request.getContextPath() %>/ShopManager?action=new" class="btn btn-default">Nuovo Prodotto</a>
	<br>
	<br>
	<c:choose>
	<c:when test="${not empty prodotti}">
		<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Immagine</th>
					<th>Nome</th>
					<th>Descrizione</th>
					<th>Prezzo</th>
					<th>in Asta</th>
					<th>Quantità</th>
					<th>Modifica</th>
					<th>Rimuovi</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${prodotti}">
					<tr>
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
						<td>
						<c:if test="${p.quantita ne 0}">
							${p.quantita}
						</c:if>
						</td>
						<td><a href="<%=request.getContextPath() %>/ShopManager?action=edit&id=${p.prodotto.idProdotto}"><span class="glyphicon glyphicon-edit"></span></a></td>
						<td><c:if test="${p.prodotto.inAsta eq 0}"><a class="remove" on="${p.prodotto.idProdotto}" href="#"><span class="glyphicon glyphicon-trash"></span></a></c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:when>	
	<c:otherwise>
		<h1>nessun prodotto presente</h1>
	</c:otherwise>
	</c:choose>
	</section>
<script src="js/ShopManager.js" type="text/javascript"></script>
</body>
</html>