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
		<input type="hidden" name="id" value="${p.prodotto.idProdotto}">
		<div class="form-group">
			<label for="name">Nome</label> 
			<input type="text" class="form-control" required name="nome" id="name" value="${p.prodotto.nome}">
		</div>
		<div class="form-group">
			<label for="description">Descrizione</label>
			<textarea required name="descrizione" id="description"
				style="resize: vertical; min-height: 200px;" class="form-control"
				rows="3">${p.prodotto.descrizione}</textarea>
		</div>
		<div class="form-group">
			<label for="name">Scegli una categoria</label> <select required
				name="categoria" class="form-control">
				<c:forEach var="cat" items="${subCategorie}">
					<c:choose>
						<c:when test="${cat.id eq p.prodotto.idCategoria}">
							<option selected="selected" value="${cat.id}">${cat.nome}</option>
						</c:when>
						<c:otherwise>
							<option value="${cat.id}">${cat.nome}</option>					
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		<c:if test="${empty a}">
		<div class="form-group">
			<label for="quantita">Quantità</label> 
			<input type="number" name="quantita" required class="form-control" placeholder="Quantità" id="quantita" min=1 value="${p.quantita}">
		</div>
		</c:if>
		<input type="hidden" name="inAsta" value="${p.prodotto.inAsta}">
		<c:if test="${p.prodotto.inAsta eq 1}">
			<div class="form-group">
				<div class="form-group">
					<label for="dataInizioAsta">Scegli la data in cui iniziare
						l'asta... </label>
						 <input type="datetime" name="dataInizio"
						class="form-control" disabled placeholder="Data Inizio" id="dataInizioAsta" value="${a.getDataInizio()}">
				</div>
				<div class="form-group">
					<label for="dataFineAsta">...e quella in cui terminare</label> <input
						type="datetime" name="dataFine" class="form-control"
						placeholder="Data Fine" disabled id="dataFineAsta" value="${a.getDataFine()}">
				</div>
				<div class="form-group">
					<label for="riserva">Indica un prezzo di riserva</label> 
					<input type="number" name="riserva" required class="form-control" placeholder="Prezzo di riserva" id="riserva" value="${a.prezzoRiserva}" step=0.01 min=0>
				</div>
			</div>
		</c:if>
		<div class="form-group">
			<label for="prezzo">Indica un prezzo per l'oggetto (prezzo
				minimo per asta)</label> 
			<c:choose>
			<c:when test="${empty a}">
			<input type="number" name="prezzo" required class="form-control" placeholder="Prezzo" id="prezzo" step=0.01 min=0 value="${p.prodotto.prezzo}">
			</c:when>
			<c:otherwise>
			<input type="number" name="baseAsta" required class="form-control" placeholder="Prezzo" id="prezzo" step=0.01 min=0 value="${a.baseAsta}">
			</c:otherwise>
			</c:choose>
		</div>
		<div class="form-group">
			<input type=submit class="btn btn-default" value="salva">
		</div>
	</form>
</section>

</body>
</html>