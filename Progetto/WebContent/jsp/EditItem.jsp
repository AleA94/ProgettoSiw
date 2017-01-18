<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Prodotto</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container tile navTop">
	<div class="row">
		<div class="page-header">
			<h1 style="text-align: center">Modifica il tuo prodotto</h1>
		</div>
	</div>
	<form method="post" action="ShopManager?par=edit" role="form">
		<input type="hidden" name="id" value="${p.idProdotto}">
		<div class="form-group">
			<label for="name">Nome</label> 
			<input type="text" class="form-control" required name="nome" id="name" value="${p.nome}">
		</div>
		<div class="form-group">
			<label for="description">Descrizione</label>
			<textarea required name="descrizione" id="description"
				style="resize: vertical; min-height: 200px;" class="form-control"
				rows="3">${p.descrizione}</textarea>
		</div>
		<div class="form-group">
			<label for="name">Scegli una categoria</label> <select required
				name="categoria" class="form-control">
				<c:forEach var="cat" items="${subCategorie}">
					<c:choose>
						<c:when test="${cat.id eq p.idCategoria}">
							<option selected="selected" value="${cat.id}">${cat.nome}</option>
						</c:when>
						<c:otherwise>
							<option value="${cat.id}">${cat.nome}</option>					
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		<input type="hidden" name="inAsta" value="${p.inAsta}">
		<c:if test="${p.inAsta eq 1}">
			<div class="form-group">
				<div class="form-group">
					<label for="dataInizioAsta">Scegli la data in cui iniziare
						l'asta... </label> <input type="date" name="dataInizio"
						class="form-control" placeholder="Data Inizio" id="dataInizioAsta" value="${p.dataInizio}">
				</div>
				<div class="form-group">
					<label for="dataFineAsta">...e quella in cui terminare</label> <input
						type="date" name="dataFine" class="form-control"
						placeholder="Data Fine" id="dataFineAsta" value="${p.dataFine}">
				</div>
			</div>
		</c:if>
		<div class="form-group">
			<label for="prezzo">Indica un prezzo per l'oggetto (prezzo
				minimo per asta)</label> 
			<input type="number" name="prezzo" required class="form-control" placeholder="Prezzo" id="prezzo" step=0.01 min=0 value=${p.prezzo}>
		</div>
		<div class="form-group">
			<input type=submit class="btn btn-default" value="salva">
		</div>
	</form>
</section>

</body>
</html>