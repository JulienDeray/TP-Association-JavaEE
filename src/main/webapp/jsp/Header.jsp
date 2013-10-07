<%@page import="com.model.bean.Adherent"%>
<%
	Adherent adh = (Adherent) session.getAttribute("adherent");
	String bouton = (String) request.getParameter("bouton");
%>

<div class="masthead">
	<div class="navbar">
	  <div class="navbar-inner">
		<div class="nav-collapse collapse">
		  <ul class="nav">
			<li <% if (bouton == "Accueil") { %> class="active" <% } %>> <a href="<%=request.getContextPath()%>/Accueil">Accueil</a></li>
			<li <% if (bouton == "Articles") { %>class="active" <% } %>> <a href="<%=request.getContextPath()%>/List">Articles</a></li>
			<li <% if (bouton == "Commande") { %>class="active" <% } %>> <a href="<%=request.getContextPath()%>/Order">Commande</a></li>
			<li class="inactive">Adherent : <%= adh.getAdLogin() %>
				<a href="<%=request.getContextPath()%>/Logout">
					<img src="images/deconnexion.jpg" height="25px" width="25px" alt="">
				</a>
			</li>
		  </ul>
		</div>
	  </div>
	</div><!-- /.navbar -->
</div>