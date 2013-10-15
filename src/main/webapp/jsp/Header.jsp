/* Customize the navbar links to be fill the entire space of the .navbar */
      .navbar .navbar-inner {
        padding: 0;
      }
      .navbar .nav {
        margin: 0;
        display: table;
        width: 100%;
      }
      .navbar .nav li {
        display: table-cell;
        width: 1%;
        float: none;
		text-align: center;
      }
      .navbar .nav li a {
        font-weight: bold;
        text-align: center;
        border-left: 1px solid rgba(255,255,255,.75);
        border-right: 1px solid rgba(0,0,0,.1);
      }
      .navbar .nav li:first-child a {
        border-left: 0;
        border-radius: 3px 0 0 3px;
      }
      .navbar .nav li:last-child a {
        border-right: 0;
        border-radius: 0 3px 3px 0;
      }
      
          </style>
  </head>

  <body>
    <div class="container">
    
<%@page import="com.model.bean.Adherent"%>
<%
	Adherent adh = (Adherent) session.getAttribute("adherent");
	String bouton = (String) request.getParameter("bouton");
%>

<!-- Affichage de la barre de menu -->
<div class="masthead">
	<div class="navbar">
	  <div class="navbar-inner">
		<div class="nav-collapse collapse">
		<!-- Récupération de la page active pour mettre le bouton correspondant actif -->
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