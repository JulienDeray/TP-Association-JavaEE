package com.association.controller.servlets;

import com.association.controller.services.ServiceLogin;
import com.core.Tools;
import com.model.bean.Adherent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns={"/Login","/Login/*"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tools.init();

        ServletContext context = getServletContext();
		RequestDispatcher rd;

		rd = context.getRequestDispatcher("/jsp/Login.jsp");
		
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceLogin serviceLogin = new ServiceLogin();
        RequestDispatcher rd;

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        Adherent adherent = serviceLogin.login(request.getParameter("login"), Tools.md5(request.getParameter("password")));

        if( adherent != null ){
            session.setAttribute("adherent", adherent);
            response.sendRedirect(context.getContextPath() + "/List");
        }
        else{
            request.setAttribute("error", "Login ou mot de passe invalide");
            rd = context.getRequestDispatcher("/jsp/Login.jsp");
            rd.include(request, response);
	    }
    }
}
