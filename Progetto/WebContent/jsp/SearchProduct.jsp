<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

	<%@include file="header.jsp"%>
	<div class="container tile">
		<c:forEach items="${prodotti}" var="i">
			<div class="col-md-4 col-sm-6 col-xs-7">
				<div class="jumbotron">
					<d1>
					<dd>${i.idProdotto}</dd>
					<dd>${i.nome}</dd>
					<dd>${i.descrizione}</dd>
					<dd>${i.inAsta}</dd>
					<dd>${i.prezzo} &euro;</dd>
					<dd>${i.dataInizio}</dd>
					<dd>${i.dataFine} </dd>
					</d1>
				</div>
			</div>
		</c:forEach>
		
	</div>
    
</body>
</html>