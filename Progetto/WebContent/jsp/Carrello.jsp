<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrello</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container tile navTop">
<c:choose>
	<c:when test="${not empty carrello}">
			<form action="Carrello?action=buyAll" method="post">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Nome</th>
								<th>Descrizione</th>
								<th>Prezzo Unitario</th>
								<th>Quantità</th>
								<th>Compra</th>
								<th>Rimuovi</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="p" items="${carrello}">
								<tr>
									<input type="hidden" name="id" value="${p.prodotto.idProdotto}">
									<td><img class="pic"
										src="${ p.prodotto.immagine}"></td>
									<td>${p.prodotto.nome}</td>
									<td>${p.prodotto.descrizione}</td>
									<td>${p.prodotto.prezzo}&euro;</td>
									<td><input name="qts" type="number" class="num" min=1 max="${p.quantita}" value="${p.quantita}"></td>
									<td>
									<a class="buy" href="<%=request.getContextPath() %>/Carrello?action=acquista&id=${p.prodotto.idProdotto}&qt="><span
											class="glyphicon glyphicon-edit"></span></a></td>
									<td><a class="remove"  on="${p.prodotto.idProdotto}" class="removeItem" href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<input type="submit" class="btn btn-default" value="Acquista
					tutti">
			</form>
		</c:when>
	<c:otherwise>
		<h1>Il carrello è vuoto</h1>
	</c:otherwise>
</c:choose>
	

</section>


</body>
<script src="js/Carrello.js"></script>
</html>