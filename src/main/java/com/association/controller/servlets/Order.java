package com.association.controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.bean.Article;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.ArticlePersistence;

/**
 * Servlet implementation class Order
 */
@WebServlet(urlPatterns = { "/Order", "/Order/*" })
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("adherent") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		if (request.getParameter("cancel") != null
				&& request.getParameter("cancel").equals("True")) {
			HttpSession session = request.getSession();
			session.setAttribute("orderInProcess", new ArrayList<Article>());
		}
		ArticlePersistence serviceAr = PersistenceServiceProvider
				.getService(ArticlePersistence.class);
		if (request.getParameter("valid") != null
				&& request.getParameter("valid").equals("True")
				&& request.getSession().getAttribute("orderInProcess") != null) {
			HttpSession session = request.getSession();
			ArrayList<Article> articles = (ArrayList<Article>) session
					.getAttribute("orderInProcess");
			for (Article article : articles) {
				Article tmp =serviceAr.load(article.getArId());
				tmp.setArStock(tmp.getArStock() - 1);
				serviceAr.save(tmp);
				
			}
			session.setAttribute("orderInProcess", new ArrayList<Article>());
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = null;
		if (request.getSession().getAttribute("orderInProcess") == null) {
			request.getSession().setAttribute("orderInProcess",
					new ArrayList<Article>());
		}
		System.err.println(((ArrayList<Article>) request.getSession()
				.getAttribute("orderInProcess")).size());
		request.setAttribute("articles",
				request.getSession().getAttribute("orderInProcess"));
		rd = context.getRequestDispatcher("/jsp/CommandeEnCours.jsp");
		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
