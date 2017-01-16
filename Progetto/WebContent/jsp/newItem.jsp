<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuovo Prodotto</title>
</head>
<body>
	<%@include file="header.jsp"%>

	<section class="container tile">
	<div class="row">
		<div class="page-header">
			<h1 style="text-align: center">Aggiungi un nuovo prodotto</h1>
		</div>
	</div>
	<form method="post" action="NewItem" role="form">
		<div class="form-group">
			<label for="name">Nome</label> <input type="text"
				class="form-control" required name="nome" id="name"
				placeholder="Inserire nome prodotto">
		</div>
		<div class="form-group">
			<label for="description">Descrizione</label>
			<textarea required name="descrizione" id="description"
				style="resize: vertical; min-height: 200px;" class="form-control"
				rows="3"></textarea>
		</div>
		<div class="form-group">
			<label class="control-label">Carica l'immagine principale</label>
			<input id="mainPhoto" name="mainPhoto" type="file" class="file">
		</div>
		<div class="form-group">
			<label class="control-label">Carica delle immagini aggiuntive</label>
			<input id="morePhotos" name="morePhotos" multiple="true" type="file" class="file">
		</div>
		<div class="form-group">
			<label for="name">Scegli una categoria</label> <select required
				name="categoria" class="form-control">
				<c:forEach var="cat" items="${subCategorie}">
					<option value="${cat.id}">${cat.nome}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="inAsta">Spunta qui per metterlo in asta </label>
			<div class="checkbox">
				<label><input id="inAsta" type="checkbox" name="inAsta" value="">Asta</label>
			</div>
		</div>
		<div class="form-group">
			<div id="date" class="disappear">
				<div class="form-group">
					<label for="dataInizioAsta">Scegli la data in cui iniziare
						l'asta... </label> <input type="date" name="dataInizio"
						class="form-control" placeholder="Data Inizio" id="dataInizioAsta">
				</div>
				<div class="form-group">
					<label for="dataFineAsta">...e quella in cui terminare</label> <input
						type="date" name="dataFine" class="form-control"
						placeholder="Data Fine" id="dataFineAsta">
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="prezzo">Indica un prezzo per l'oggetto (prezzo
				minimo per asta)</label> 
			<input type="number" name="prezzo" required class="form-control" placeholder="Prezzo" id="prezzo" step=0.01 min=0>
		</div>
		<div class="form-group">
			<input type=submit class="btn btn-default" value="salva">
		</div>
	</form>
	</section>

	<script src="js/newItem.js"></script>
</body>
</html>