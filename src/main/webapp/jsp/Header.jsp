<%@page import="com.model.bean.Adherent"%>
<%
	Adherent adh = (Adherent) session.getAttribute("adherent");
	String bouton = request.getParameter("bouton");
%>

<div class="masthead">
	<div class="navbar">
	  <div class="navbar-inner">
		<div class="nav-collapse collapse">
		  <ul class="nav">
			<li class="active"><a href="#">Accueil</a></li>
			<li><a href="/List">Articles</a></li>
			<li><a href="/Order">Commande</a></li>
			<li class="inactive">Adherent : <%= adh.getAdLogin() %>			
				<a href="">
					<img src="images/deconnexion.jpg" height="25px" width="25px" alt="">
				</a>
			</li>
		  </ul>
		</div>
	  </div>
	</div><!-- /.navbar -->
</div>