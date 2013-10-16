package com.association.controller.servlets;

import com.association.controller.services.ServiceLogin;
import com.core.Tools;
import com.model.bean.Adherent;

import javax.inject.Inject;
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

	@Inject
	ServletContext context;

    private RequestDispatcher rd;
	
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
		rd = context.getRequestDispatcher("/jsp/Login.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceLogin serviceLogin = new ServiceLogin();
        HttpSession session = request.getSession();

        Adherent adherent = serviceLogin.login(request.getParameter("login"), Tools.md5(request.getParameter("password")));

        //adherent trouve + mot de passe OK
        if( adherent != null ){
            session.setAttribute("adherent", adherent);
            response.sendRedirect(context.getContextPath() + "/Accueil");
        }

        //adherent Non trouve ou mot de passe Non OK
        else{
            request.setAttribute("error", "Login ou mot de passe invalide");
            rd = context.getRequestDispatcher("/jsp/Login.jsp");
            rd.include(request, response);
	    }
    }
}
