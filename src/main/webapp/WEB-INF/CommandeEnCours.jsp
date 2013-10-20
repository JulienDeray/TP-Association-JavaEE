<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.bean.Article" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
      
		<jsp:include page="Header.jsp" >
    		<jsp:param name="bouton" value="Commande" />
		</jsp:include>
	  
		<h3>Votre commande en cours</h3>
	  		<c:if test="${error != null}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
	  	<!-- Récupérataion du panier conserver en sessions  -->	
		<table class="table table-hover">
			<thead>
				<tr>
				  <th>Code</th>
				  <th>Nom</th>
				  <th>Prix</th>
				</tr>
			</thead>
			<tbody>
				<% int total =0; %>
				<!-- Récupération de chaque élément du panier -->
				<c:forEach var="article" items="${requestScope['articles']}" >
					<tr>
					<td>${article.arCode}</td>
					<td>${article.arNom}</td>
					<td>${article.arPrix}</td>					  		
	  				<% total =total + ((Article) pageContext.getAttribute("article")).getArPrix();%>
	  				</tr>
	  			</c:forEach>
			 	<tr>
			 		<td> Total</td>
			 		<td></td>
			 		<td><%=total %></td>
			 	</tr>
			</tbody>
		</table>
		
		<!-- Boutons d'actions -->
		<div class="form-actions">
			<a href="<%=request.getContextPath()%>/Order?valid=True">
  				<button type="submit" class="btn btn-primary">Valider la commande</button>
  			</a>
  			<a href="<%=request.getContextPath()%>/Order?cancel=True">
  				<button type="button" class="btn">Cancel</button>
  			</a>
		</div>
	</div> <!-- /container -->

    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>