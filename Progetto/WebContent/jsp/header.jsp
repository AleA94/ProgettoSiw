<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/stile.css" rel="stylesheet">
<link href="css/creative.css" rel="stylesheet">
<link href="css/creative.min.css" rel="stylesheet">
<link href="css/lavish-bootstrap.css" rel="stylesheet">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/index.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#menu">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/"> Sito Bello </a>
	</div>
	<div class="collapse navbar-collapse" id="menu">
		<ul class="nav navbar-nav navbar-right">
			<li><a id="log" data-toggle="modal" data-target="#login-modal"
				href="#"> <span class="glyphicon glyphicon-user"></span> login
			</a>
				<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					style="display: none;">
					<div class="modal-backdrop" style="display: none;">
					
					</div>

					<div class="modal-dialog">
						<div class="loginmodal-container">
							<h1>Login to Your Account</h1>
							<br> <input type="text" id="mail" placeholder="Username">
							<input type="password" id="pass" placeholder="Password">
							<input type="submit" id="login" class="login loginmodal-submit"
								value="Login">
							<div class="login-help">
								<a href="#" data-toggle="modal" data-target="#register-modal">Register</a>
								 <a href="#">Forgot Password</a>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="register-modal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
					style="display: none;">
					<div class="modal-dialog">
						<div class="loginmodal-container">
							<h1>Registrati sul nostro sito</h1>
							<br> <input type="text" id="Rmail" placeholder="Email">
							<input type="text" id="Rnome" placeholder="Nome"> <input
								type="text" id="Rcogn" placeholder="Cognome"> <input
								type="text" id="Raddr" placeholder="Indirizzo"> <input
								type="password" id="Rpass" placeholder="password"> <input
								type="submit" id="register" class="login loginmodal-submit"
								value="Registrati">
						</div>
					</div>
				</div>
			<li id="user" class="dropdown disappear"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <b id="person"></b>
					<b class="caret"></b>
			</a>
				<ul class="dropdown-menu user-menu">
					<li><a href="${request.getContextPath()}/ProfileManager">I tuoi dati</a></li>
					<li><a href="#">I tuoi ordini</a></li>
					<li class="divider"></li>
					<li><a id="logout" href="#"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul></li>
			<li><a><span class="glyphicon glyphicon-shopping-cart"></span>
					Carrello</a></li>
		</ul>
	</div>
	</nav>

	<div class="container">
		<div class="row tile navTop">
			<div class="row"></div>
			<div class="row">
				<div class="col-lg-12 col-sm-12">
				<form action="SearchProduct">
					<div class="input-group searchBar">
						<span class="input-group-btn">
							<input type=hidden name="categoria" id="currCat">
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown">
								<b id="selCat">Tutte le Categorie</b><span class="caret"></span>
							</button>
							<ul id="srcCat" class="dropdown-menu">
								<li class="active"><a >Tutte Le categorie</a></li>
							</ul>
						</span>
						
						<input type="text" name="nomeProdotto" class="form-control"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<span class="glyphicon glyphicon-search"></span>&nbsp;
							</button>	
						</span>
						
					</div>
					</form> 
				</div>
			</div>
		</div>
	</div>

</body>

</html>
