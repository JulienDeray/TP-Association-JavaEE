package com.association.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.Tools;
import com.model.bean.Adherent;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.AdherentPersistence;

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
    	//---TODO
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ServletContext context = getServletContext();
		RequestDispatcher rd =null;
		
		
		rd = context.getRequestDispatcher("/jsp/Login.jsp");
		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO verification du formulaire
		ServletContext context = getServletContext();
		HttpSession session = request.getSession();
		AdherentPersistence serviceAdh = PersistenceServiceProvider.getService(AdherentPersistence.class);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ad_login", request.getParameter("login"));
		RequestDispatcher rd =null;
		List<Adherent> adherant = serviceAdh.search(param);
		if(adherant.size()==1 && adherant.get(0).getAdPassword().equals(Tools.md5(request.getParameter("password"))) ){
			session.setAttribute("adherent", adherant.get(0));
			rd = context.getRequestDispatcher("/List");
			rd.forward(request, response);
			return;
		}else{
			request.setAttribute("error", "Login ou mot de passe invalide");
			rd = context.getRequestDispatcher("/jsp/Login.jsp");
			rd.include(request, response);
		}		
	}

}
