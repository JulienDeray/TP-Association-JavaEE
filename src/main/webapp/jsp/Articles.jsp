<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Commande</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <script type="text/javascript" src="boostrap/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="boostrap/js/bootstrap-spineedit.js"></script>
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
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

    
	  	<jsp:include page="Header.jsp" >
    		<jsp:param name="bouton" value="Aticles" />
		</jsp:include>
	  	
		<h3>Liste des articles</h3>
	  
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
				<!-- Affichage de chaque article en base -->
				<c:forEach var="article" items="${requestScope['articles']}" >
					<tr>
						<td>${article.arCode}</td>
						<td>${article.arNom}</td>
						<td>${article.arPrix}</td>
	
						    <c:choose>						    	 
						        <c:when test="${article.arStock > 0}">  
						        	<!--  Affichage du stock si > 0 --> 
					            	<td>${article.arStock}</td>
					            	
					            	<!-- Ajout de l'article dans le panier -->
					            	<td><a href="<%=request.getContextPath()%>/List?article=${article.arId}">
	                                    <c:choose>
	                                        <c:when test="${article.arId == requestScope['added']}">
	                                            <span class="label label-info">Ajouté !</span>
	                                        </c:when>
	                                    </c:choose> Ajouter au panier</a></td>
					            </c:when>
					            <c:otherwise>					            						            
					            	<!-- Sinon article indisponible et impossible de l'ajouter au panier -->    
					            	<td>Indisponible</td>
					            	<td></td>
					            </c:otherwise>  
						    </c:choose>  
					</tr>				
				</c:forEach>
			</tbody>
		</table>
	</div> <!-- /container -->

    </body>
</html>