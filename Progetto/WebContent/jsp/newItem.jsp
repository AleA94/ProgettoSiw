<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	DbConnector d=DbConnector.getInstance();
    	List<Categoria> l=d.getSubCategorie();
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
				class="form-control" required name="nome" id="name" placeholder="Inserire nome prodotto">
		<br>
			<label for="description">Descrizione</label>
			<textarea required name="descrizione" id="description" style="resize: vertical; min-height: 200px;"
				class="form-control" rows="3"> </textarea>
				<br>
		<label class="control-label">Carica l'immagine principale</label> 
		<input id="mainPhoto" type="file" class="file"><br>
		
		<label class="control-label">Carica delle immagini aggiuntive</label> 
		<input id="morePhotos" type="file" class="file"><br>
		
		
		<label for="name">Scegli una categoria</label> 
		<select required name="categoria" class="form-control">
			<%
			for(Categoria c:l)	
			out.println("<option value="+c.getId()+">"+c.getNome()+"</option>");
			%>
		</select>
		<br>
		<label for="inAsta">Spunta qui per metterlo in asta </label>
		<div nome="inAsta" class="checkbox"> <label><input id="inAsta" type="checkbox" value="">Asta</label> 
		</div>
		
		<div id="date" class="disappear">
			<label for="dataInizioAsta">Scegli la data in cui iniziare l'asta... </label>
			<input type="date" name="dataInizio" class="form-control" placeholder="Data Inizio" id="dataInizioAsta">
			<br>
			<label for="dataFineAsta">...e quella in cui terminare</label>
			<input type="date" name="dataFine" class="form-control" placeholder="Data Fine" id="dataFineAsta">
		</div>
<br>		
		<label for="prezzo">Indica un prezzo per l'oggetto (prezzo minimo per asta)</label>
		<input type="number" name="prezzo" required class="form-control" placeholder="Prezzo" id="prezzo">

		<br>
		<input type=submit class="btn btn-default" value="salva">
		</div>
	</form>
	</section>

<script src="js/newItem.js"></script>
</body>
</html>