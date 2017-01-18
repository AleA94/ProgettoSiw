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

	<div class="container tile navTop">
		<div class="row">
			<div class="page-header">
				<h1 style="text-align: center">Le nostre categorie</h1>
			</div>
		</div>

		<section class="no-padding" id="portfolio">
		<div class="container-fluid">
			<div class="row no-gutter popup-gallery">
				<c:forEach var="c" items="${categorie}">
					<div class="col-lg-4 col-sm-6">
						<form action="SearchProduct">
							<input type=hidden name="nomeProdotto" value=""> 
								<a	class="portfolio-box"> 
									<img src="immagini/categorie/${c.nome}.jpg" class="img-responsive" alt="">
									<button class="portfolio-box-caption" style="border: none" type="submit" name="categoria" value="${c.id}">
										<div class="portfolio-box-caption-content">
											<div class="project-category text-faded">Category</div>
											<div class="project-name">${c.nome}</div>
										</div>
									</button>
									</a>
							</input>
						</form>
					</div>
				</c:forEach>
			</div>
		</div>
		</section>

	</div>

</body>
</html>