package com.association.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.Article;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.ArticlePersistence;

/**
 * Servlet implementation class ListArticle
 */
@WebServlet(urlPatterns={"/List","/List/*"})
public class ListArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		ArticlePersistence service = PersistenceServiceProvider.getService(ArticlePersistence.class);
		
		List<Article> articles = service.loadAll();
		RequestDispatcher rd =null;
		request.setAttribute("articles", articles);
		rd = context.getRequestDispatcher("/jsp/Articles.jsp");
		rd.include(request, response);

	}


}
