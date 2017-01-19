<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aste In Scadenza</title>
</head>

<body>
	<div class="container tile navTop">
		<h1 style="text-align: center">Aste in scadenza oggi</h1>

		<c:forEach var="c" items="${asteProdotto}">
			<div class="row">

				<div class="card">

					<div class="preview col-md-6">
						<div class="preview-pic tab-content">
							<div class="tab-pane active" id="pic-1">
								<div id="immagineAsta">
									<img src="immagini/${c.nomeProdotto}.jpg"
										class="img-responsive" alt="">
								</div>
							</div>
						</div>
						<ul class="preview-thumbnail nav nav-tabs">
						</ul>
					</div>
					<div class="details col-md-6">
						<h3 class="product-title">${c.nomeProdotto}</h3>
						<h3 class="description">Descrizione:</h3>
						<p class="product-description">${c.descrizioneProdotto}</p>
						<h4 class="price">
							current price: <span>${c.prezzoCorrente}</span>
						</h4>

						<div class="action">
							<button class="add-to-cart btn btn-default" type="button">fai
								un offerta</button>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>


</body>

</html>
