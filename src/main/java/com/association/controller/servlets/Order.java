package com.association.controller.servlets;

import com.model.bean.Article;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.ArticlePersistence;

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
import java.util.ArrayList;

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
    //    ServletContext context = getServletContext();
        RequestDispatcher rd;

//        if ( notConnected(request) ) {
//			response.sendRedirect(request.getContextPath() + "/Login");
//			return;
//		}
        //si on demande de vider la commande
		if ( canceled(request) ) {
			session.setAttribute("orderInProcess", new ArrayList<Article>());
		}

		ArticlePersistence serviceAr = PersistenceServiceProvider.getService(ArticlePersistence.class);
		ArrayList<Article> articlesRestant = new ArrayList<Article>();
		//validation de la commande
        if ( validated(request) ) {
			ArrayList<Article> articles = (ArrayList<Article>) session.getAttribute("orderInProcess");

            for (Article article : articles) {
				Article tmp = serviceAr.load(article.getArId());
				//deducation dans le stock
				if(tmp.getArStock()>0){
					System.out.println(tmp.getArStock());
					tmp.setArStock(tmp.getArStock() - 1);
					serviceAr.save(tmp);
					
				}else{
					articlesRestant .add(tmp);
				}
			}

			session.setAttribute("orderInProcess", articlesRestant);
			System.out.println(articlesRestant.size()+"  ssss");
			if(articlesRestant.size()>0){
				request.setAttribute("error", "Les articles restant n'ont pas pu être validés par manque de stock");
				request.setAttribute("articles", request.getSession().getAttribute("orderInProcess"));
				rd = context.getRequestDispatcher("/jsp/CommandeEnCours.jsp");
			}else{
				rd = context.getRequestDispatcher("/jsp/commandeConfirmee.jsp");
			}
            //System.err.println(((ArrayList<Article>) request.getSession() .getAttribute("orderInProcess")).size());
            //rd = context.getRequestDispatcher("/jsp/commandeConfirmee.jsp");
            rd.include(request, response);
		}
        //panier vide
        else if (request.getSession().getAttribute("orderInProcess") == null || ((ArrayList<Article>) request.getSession().getAttribute("orderInProcess")).isEmpty() ) {
			request.getSession().setAttribute("orderInProcess", new ArrayList<Article>());
            rd = context.getRequestDispatcher("/jsp/panierVide.jsp");
            rd.include(request, response);
		}
        //panier avec au moins un produit
        else {
            request.setAttribute("articles", request.getSession().getAttribute("orderInProcess"));
            rd = context.getRequestDispatcher("/jsp/CommandeEnCours.jsp");
            rd.include(request, response);
        }
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
