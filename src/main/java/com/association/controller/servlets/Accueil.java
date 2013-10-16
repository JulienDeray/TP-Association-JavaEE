package com.association.controller.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Accueil
 */
@WebServlet(urlPatterns={"/Accueil","/Accueil/*"})
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//verification de la connexion de l'adherent
		if(request.getSession().getAttribute("adherent")== null){
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		
		RequestDispatcher rd;
		rd = getServletContext().getRequestDispatcher("/jsp/Accueil.jsp");
		rd.include(request, response);
	}

}
