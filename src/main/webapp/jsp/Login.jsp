<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>Se connecter</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- Style-->
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 19px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading{
        margin-bottom: 10px;
		align: center;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
		width: 170px;
      }
	  td {
		padding-right: 10px
	  }

    </style>
</head>
<body>
	<form class="form-signin" method="POST" action="<%=request.getContextPath()%>/Login">
        <h2 class="form-signin-heading">Please login</h2>
		<table>
			<tr>
				<td>Identifiant</td>
				<td><input id="login" type="text" class="input-block"></td>				
			</tr>
			<tr>
				<td>Mot de passe</td>
				<td><input id="password" type="password" class="input-block"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn btn-large btn-primary" type="submit">Se connecter</button>
				</td>				
			</tr>
			<tr>
				<td colspan="2" align="center">
					Pas encore enregistrez ? 
					<a href="<%=request.getContextPath()%>/SignUp"> Creer votre compte </a>
				</td>
			</tr>
		</table>
	</form>
	<script src="bootstrap/js/bootstrap.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>