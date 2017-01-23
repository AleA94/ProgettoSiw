<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/product.css" rel="stylesheet">
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container tile navTop">
			<div class="col-lg-12 col-sm-12 ">
				<div class="container">
					<div class="card">
						<div class="container-fliud">
							<div class="wrapper row">
								<div class="preview col-md-6">

									<div class="preview-pic tab-content">
										<div class="tab-pane active" id="pic-1">
											<img src="http://placekitten.com/400/252" />
										</div>
										<div class="tab-pane" id="pic-2">
											<img src="http://placekitten.com/400/252" />
										</div>
										<div class="tab-pane" id="pic-3">
											<img src="http://placekitten.com/400/252" />
										</div>
										<div class="tab-pane" id="pic-4">
											<img src="http://placekitten.com/400/252" />
										</div>
										<div class="tab-pane" id="pic-5">
											<img src="http://placekitten.com/400/252" />
										</div>
									</div>
									<ul class="preview-thumbnail nav nav-tabs">
										<li class="active"><a data-target="#pic-1"
											data-toggle="tab"><img
												src="http://placekitten.com/200/126" /></a></li>
										<li><a data-target="#pic-2" data-toggle="tab"><img
												src="http://placekitten.com/200/126" /></a></li>
										<li><a data-target="#pic-3" data-toggle="tab"><img
												src="http://placekitten.com/200/126" /></a></li>
										<li><a data-target="#pic-4" data-toggle="tab"><img
												src="http://placekitten.com/200/126" /></a></li>
										<li><a data-target="#pic-5" data-toggle="tab"><img
												src="http://placekitten.com/200/126" /></a></li>
									</ul>

								</div>
								<div class="details col-md-6">

									<h3 class="product-title">${prodotto.nome}</h3>

									<p class="product-description">>${prodotto.descrizione}</p>
									<h4 class="price">
										current price: <span>>${prodotto.prezzo} &euro;</span>
									</h4>
									<div class="action">
										<button class="add-to-cart btn btn-default" type="button">add
											to cart</button>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</body>
</html>