package com.association.controller.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.Tools;
import com.model.bean.Adherent;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.AdherentPersistence;
import com.model.persistence.services.ArticlePersistence;
import com.model.persistence.services.PaysPersistence;

/**
 * Servlet implementation class SignUp
 */
@WebServlet(urlPatterns={"/SignUp","/SignUp/*"})
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		PaysPersistence service = PersistenceServiceProvider.getService(PaysPersistence.class);
		request.setAttribute("paysAll", service.loadAll());
		RequestDispatcher rd =null;
		rd = context.getRequestDispatcher("/jsp/CreationCompte.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdherentPersistence serviceAdh = PersistenceServiceProvider.getService(AdherentPersistence.class);
		PaysPersistence servicePays= PersistenceServiceProvider.getService(PaysPersistence.class);
		Adherent adherent = new Adherent();
		String nom = null;
		String password = null;
		String passwordConfirm = null;
		String prenom = null;
		String adresse = null;
		String codePostal = null;
		String ville = null;
		String login = null;
		com.model.bean.Pays pays = null;
		Enumeration<String> parameters = request.getParameterNames();
		while(parameters.hasMoreElements()){
			String param = parameters.nextElement();
			switch (param) {
			case "nom":
				nom = request.getParameter("nom");
				break;
			case "prenom":
				prenom = request.getParameter("prenom");
				break;
			case "codePostal":
				prenom = request.getParameter("codePostal");
				break;
			case "ville":
				prenom = request.getParameter("ville");
				break;
			case "login":
				prenom = request.getParameter("login");
				break;
			case "adresse":
				prenom = request.getParameter("login");
				break;
			case "password":
				password = Tools.md5(request.getParameter("password"));
				break;
			case "passwordConfirm":
				passwordConfirm = Tools.md5(request.getParameter("passwordConfirm"));
				break;	
			case "pays":
				try{
					pays = servicePays.load(Integer.parseInt(request.getParameter("pays")));
				}catch (Exception e) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "La page entr�e n'est pas valide ");
					return;
				}
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "La page entr�e n'est pas valide ");
				return;
			}	
		}
		if(login==null||nom==null|| prenom==null || password ==null || !password.equals(passwordConfirm)){
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "La page entr�e n'est pas valide ");
			return;
		}
		
		//--TODO verification de l'unicit� du login
		adherent.setPays(pays);
		adherent.setAdAdresse(adresse);
		adherent.setAdCodepostal(codePostal);
		adherent.setAdLogin(login);
		adherent.setAdNom(nom);
		adherent.setAdPrenom(prenom);
		adherent.setAdVille(ville);
		adherent.setAdPassword(password);
		serviceAdh.insert(adherent);
	}

}
