<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conferma acquisto</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<section class="container tile navTop">
	<form method="post" action="Carrello?action=confirmAll">
	<h3>L'acquisto sarà spedito
		all'indirizzo: ${account.indirizzo}</h3>
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th></th>
					<th>Nome</th>
					<th>Descrizione</th>
					<th>Prezzo</th>
					<th>Quantità</th>
					<th>Rimuovi</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${daAcquistare}">
					<tr>
					<input name="id" type="hidden" value="${p.prodotto.idProdotto }">
						<td><img class="pic" src="${p.prodotto.immagine}"></td>
						<td>${p.prodotto.nome}</td>
						<td>${p.prodotto.descrizione}</td>
						<td>${p.prodotto.prezzo}&euro;</td>
						<td><input type="number" name="quantita" class="num" min=0 max="${p.quantita}" value="${p.quantita}"></td>
						<td><a class="remove" href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input type="submit" class="btn btn-default" value="Completa Acquisti">
	</form>
	</section>

</body>
<script type="text/javascript">
$('.remove').click(function(e){
	e.preventDefault();
	$(this).parent().parent().remove();
	if($('tr').length=1)
		window.location.href = $('#context').val()+'/Carrello';
})

</script>
</html>