<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
        width: 450px;
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
        height: 20px;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
	  .form-signin p{
		padding-top: 5px;
		height: 37px;
	  }

    </style>
</head>
<body>
	<form class="form-signin" action="POST" action="<%=request.getContextPath()%>/SignUp">
        <h2 class="form-signin-heading">Enregistrez vous</h2>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span5">
					<p>Identifiant : </p>
					<p>Mot de passe : </p>
					<p>Confirmation MDP : </p>
					<p>Nom : </p>
					<p>Pr&eacute;nom : </p>
					<p>Adresse : </p>
					<p>Code Postal : </p>
					<p>Ville : </p>
					<p>Pays : </p>
				</div>
				<div class="span7">
					<input id="login" type="text" class="input-block" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" required>
					<input id="password" type="password" class="input-block" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required>
					<input id="passwordConfirm" type="password" class="input-block" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required>
					<input id="nom" type="text" class="input-block" required>
					<input id="prenom" type="text" class="input-block" required>
					<input id="adresse" type="text" class="input-block">
					<input id="codePostal" type="text" class="input-block" pattern="[0-9]{5}">
					<input id="ville" type="text" class="input-block">
					<select id="pays">
						<c:forEach var="pays" items="${requestScope['paysAll']}" >
							<option>${pays.paNom}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div align="center">
			<button class="btn btn-large btn-primary" type="submit">S'enregistrer</button>
		</div>

	</form>
	<script src="bootstrap/js/bootstrap.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>