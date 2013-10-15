<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Accueil</title>
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
    		<jsp:param name="bouton" value="Accueil" />
		</jsp:include>
	  
		<h3> Bienvenue sur le site des adh&eacute;rent de l'association </h3>
		<h5> <a href="<%=request.getContextPath()%>/List">Consulter les articles disponibles </a> </h5>
		<h5> <a href="<%=request.getContextPath()%>/Order">Consulter votre commande</a> </h5>
	  


    </div> <!-- /container -->

    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

  </body>
  </html>