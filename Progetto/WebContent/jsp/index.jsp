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
		<div class="row">
			<div class="page-header">
				<h1 style="text-align: center">Le nostre categorie</h1>
			</div>
		</div>

		<section class="no-padding" id="portfolio">
			<div class="container-fluid">
				<div class="row no-gutter popup-gallery">
					<%
						for(Categoria c:l)
							out.println("<div class=\"col-lg-4 col-sm-6\"><a class=\"portfolio-box\"> <img src=\"immagini/categorie/"+
							c.getNome()+
							".jpg\" class=\"img-responsive\" alt=\"\"><div class=\"portfolio-box-caption\">	<div class=\"portfolio-box-caption-content\">	<div class=\"project-category text-faded\">Category</div><div class=\"project-name\">"+
							c.getNome()
							+"</div>	</div>	</div></a></div>");
					%>
				</div>
			</div>
		</section>

	</div>

</body>
</html>