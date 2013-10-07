<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>Commande</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 20px;
        padding-bottom: 60px;
      }

      /* Custom container */
      .container {
        margin: 0 auto;
        max-width: 1000px;
      }
      .container > hr {
        margin: 60px 0;
      }

      <%@ include file="HeaderCSS.jsp" %>
    </style>
</head>
<body>

    <div class="container">

		<jsp:include page="Header.jsp" >
    		<jsp:param name="bouton" value="Commande" />
		</jsp:include>
	  
		<h3>Votre commande en cours</h3>
	  		
	  		
		<table class="table table-hover">
			<thead>
				<tr>
				  <th>Code</th>
				  <th>Nom</th>
				  <th>Prix</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${requestScope['articles']}" >
					<td>article.Code</td>
					<td>article.Nom</td>
					<td>article.Prix</td>					  		
	  			</c:forEach>
			</tbody>
		</table>
		
		<div class="form-actions">
			<a href="/Order?valid=True">
  				<button type="submit" class="btn btn-primary">Valider la commande</button>
  			</a>
  			<a href="/Order?cancel=True">
  				<button type="button" class="btn">Cancel</button>
  			</a>
		</div>
	</div> <!-- /container -->

    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>