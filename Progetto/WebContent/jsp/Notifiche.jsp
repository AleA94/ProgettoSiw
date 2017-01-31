<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area Notifiche</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container tile navTop">
	<div class="row">
		<div class="page-header">
			<h1 style="text-align: center">Le tue notifiche</h1>
		</div>
	</div>
	<c:choose>
	<c:when test="${not empty notifiche}">
		<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Notifica</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="n" items="${notifiche}">
				<c:choose>
					<c:when test="${n.nonLetta eq 1}">
						<tr class="active">
							<td>${n.notifica}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td>${n.notifica}</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:when>	
	<c:otherwise>
		<h1>nessuna notifica presente</h1>
	</c:otherwise>
	</c:choose>

</section>


</body>
</html>