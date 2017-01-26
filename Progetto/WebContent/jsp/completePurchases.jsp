<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>I tuoi acquisti</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container tile navTop">
	<div class="row">
		<div class="page-header">
			<h1 style="text-align: center">I tuoi acquisti</h1>
		</div>
	</div>
	
	<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Nome</th>
								<th>Descrizione</th>
								<th>Prezzo</th>
								<th>Quantità</th>
								<th>Data</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="a" items="${acquisti}">
								<tr>
									<td></td>
									<td>${a.prodotto.nome }</td>
									<td>${a.prodotto.descrizione }</td>
									<td>${a.prodotto.prezzo }</td>
									<td>${a.quantita }</td>
									<td>${a.data }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
	
</section>
</body>
</html>