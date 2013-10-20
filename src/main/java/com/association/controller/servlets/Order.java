package com.association.controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.association.controller.services.ServiceArticle;
import com.model.bean.Article;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.ArticlePersistence;

/**
 * Servlet implementation class Order
 */
@WebServlet(urlPatterns = { "/Order", "/Order/*" })
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	ServletContext context;
	
	public Order() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd;
		//ArticlePersistence serviceAr = PersistenceServiceProvider.getService(ArticlePersistence.class);

        //si on demande de vider la commande
		if ( canceled(request) ) {
			session.setAttribute("orderInProcess", new ArrayList<Article>());
		}

		//validation de la commande
        if ( validated(request) ) {
			List<Article> articlesRestant = validOrder(session);

			session.setAttribute("orderInProcess", articlesRestant);
			if( articlesRestant.size() > 0 ) {
				request.setAttribute("error", "Les articles restant n'ont pas pu être validés par manque de stock");
				request.setAttribute("articles", request.getSession().getAttribute("orderInProcess"));
				rd = context.getRequestDispatcher("/WEB-INF/CommandeEnCours.jsp");
			}
            else {
				rd = context.getRequestDispatcher("/WEB-INF/commandeConfirmee.jsp");
			}
            rd.include(request, response);
		}

        //panier vide
        else if (panierVide(request)) {
			request.getSession().setAttribute("orderInProcess", new ArrayList<Article>());
            rd = context.getRequestDispatcher("/WEB-INF/panierVide.jsp");
            rd.include(request, response);
		}

        //panier avec au moins un produit
        else {
            request.setAttribute("articles", request.getSession().getAttribute("orderInProcess"));
            rd = context.getRequestDispatcher("/WEB-INF/CommandeEnCours.jsp");
            rd.include(request, response);
        }
	}

    /**
     * Check si le panier est vide
     * @param request
     * @return
     */
    private boolean panierVide(HttpServletRequest request) {
        return request.getSession().getAttribute("orderInProcess") == null || ((ArrayList<Article>) request.getSession().getAttribute("orderInProcess")).isEmpty();
    }

    /**
	 * Valide la commande
	 * @param session
	 * @param serviceAr
	 * @return les articles n'ayant pas pu être validés
	 */
	private List<Article> validOrder(HttpSession session) {
		ArrayList<Article> articles = (ArrayList<Article>) session.getAttribute("orderInProcess");
		
		
		return new ServiceArticle().valideCommande(articles);
	}

	/**
	 * Demande de validation
	 * @param request
	 * @return
	 */
    private boolean validated(HttpServletRequest request) {
        return request.getParameter("valid") != null
				&& request.getParameter("valid").equals("True")
				&& request.getSession().getAttribute("orderInProcess") != null;
    }
    /**
     * Demande de suppression de la commande
     * @param request
     * @return
     */
    private boolean canceled(HttpServletRequest request) {
        return request.getParameter("cancel") != null && request.getParameter("cancel").equals("True");
    }
    
//    /**
//     * Verification de la connexion de l'adherent
//     * @param request
//     * @return True if not connected
//     */
//    private boolean notConnected(HttpServletRequest request) {
//        return request.getSession().getAttribute("adherent") == null;
//    }



}
