<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="css/ShopManager.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestisci negozio</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<section class="container tile">
	<div class="row">
		<div class="page-header">
			<h1 style="text-align: center">il tuo negozio</h1>
		</div>
	</div>
	<form method="post" action="ShopManager">
		<input class="btn btn-default" value="Nuovo prodotto" type="submit">
	</form>
	<br>
	<br>
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Immagine</th>
					<th>Nome</th>
					<th>Descrizione</th>
					<th>Prezzo</th>
					<th>in Asta</th>
					<th>Modifica</th>
					<th>Rimuovi</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(DAO.Prodotto p:DbConnector.getInstance().getProdottoPerNegozio("g@g.com"))
						out.print("<tr><td><img class=\"picture\" src=\"immagini/categorie/Elettronica.jpg\"></td><td>"+p.getNome()+"</td><td>"+p.getDescrizione()+"</td><td>"+p.getPrezzo()+"</td><td>"+((p.getInAsta()==0)?"No":"Si")+"</td><td><a href=\"#\"><span class=\"glyphicon glyphicon-edit\"></span></a></td><td><a href=\"#\"><span class=\"glyphicon glyphicon-trash\"></span></a></td></tr>");
				%>
			</tbody>
		</table>
	</div>

	</section>

</body>
</html>