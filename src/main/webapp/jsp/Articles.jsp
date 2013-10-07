<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    		<jsp:param name="bouton" value="Aticles" />
		</jsp:include>
	  	
		<h3>Votre commande en cours</h3>
	  
		<table class="table table-hover">
			<thead>
				<tr>
				  <th>Code</th>
				  <th>Nom</th>
				  <th>Prix</th>
				  <th>Stock</th>
				  <th> </th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="article" items="${requestScope['articles']}" >
				<tr>
					<td>${article.arCode}</td>
					<td>${article.arNom}</td>
					<td>${article.arPrix}</td>
					
					    <c:choose>  
					        <c:when test="${article.arStock} > 0">  
				            	<td>${article.Stock}</td>
				            	<td><a href="/List?article=${article.arId}"/>Commander</a></td>   
				            </c:when>  
				            <c:otherwise>  
				            	<td>Indisponible</td>
				            	<td></td>
				            </c:otherwise>  
					    </c:choose>  
				</tr>				
			</c:forEach>
			</tbody>
		</table>
	</div> <!-- /container -->

    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

  </body>
</html>