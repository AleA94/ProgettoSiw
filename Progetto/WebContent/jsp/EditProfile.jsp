<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione Profilo</title>
</head>
<body>
	<%@include file="header.jsp"%>

	<section class="container tile navTop">
	<div class="row">
		<div class="page-header">
			<h1 class=title>Modifica il tuo profilo</h1>
		</div>
	</div>

	<ul class="nav nav-tabs">
		 
		<li class="active"><a data-toggle="tab" href="#dati">Dati
				Generali</a></li>  
		<li><a data-toggle="tab" href="#password">Cambia password</a></li>
	</ul>

	<div class="tab-content">
		 
		<div id="dati" class="tab-pane fade in active">
			<form method="post" action="ProfileManager" role="form">
				<div class="form-group">
					<label for="name" class="col-lg-2 col-sm-2">Nome</label> 
					<div class="col-lg-10 col-sm-10">
						<input type="text"
							class="form-control" required name="nome" id="name"
							value="${account.nome}">
					</div>
				</div>
				<div class="form-group">
					<label for="cognome" class="col-lg-2 col-sm-2">Cognome</label>
					<div class="col-lg-10 col-sm-10">
						<input type="text"
							class="form-control" required name="cognome" id="cognome"
							value="${account.cognome}">
					</div>
				</div>
				<div class="form-group">
					<label for="indirizzo" class="col-lg-2 col-sm-2">Indirizzo</label> 
					<div class="col-lg-10 col-sm-10">
						<input type="text"
							class="form-control" required name="indirizzo" id="indirizzo"
							value="${account.indirizzo}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-sm-offset-2 col-lg-10 col-sm-10">
						<input type=submit class="btn btn-default" value="salva">
					</div>
				</div>
			</form>
			     
		</div>
		 
		<div id="password" class="tab-pane fade">
			   
			<form method="post" action="ProfileManager" role="form">
				<div class="form-group">
					<label for="oldPass" class="col-lg-2 col-sm-2">Vecchia
						password</label>
					<div class="col-lg-10 col-sm-10">
						<input type="password" class="form-control" required
							name="oldPass" id="oldPass">
					</div>
				</div>
				<div class="form-group">
					<label for="newPass" class="col-lg-2 col-sm-2">Nuova
						Password</label>
					<div class="col-lg-10 col-sm-10">
						<input type="password" class="form-control col-lg-10 col-sm-10"
							required name="newPass" id="newPass">
					</div>
				</div>
				<div class="form-group">
					<label for="confirmPass" class="col-lg-2 col-sm-2">Conferma
						password</label>
					<div class="col-lg-10 col-sm-10">
						<input type="password" class="form-control col-lg-10 col-sm-10"
							required disabled="disabled" id="confirmPass">
					</div>

				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-sm-offset-2 col-lg-10 col-sm-10">
						<input type=submit id="savePass" class="btn btn-default" value="salva">
					</div>
				</div>

			</form>
			     
		</div>
		 
	</div>

	</section>

</body>
<script src="js/EditProfile.js"></script>
</html>