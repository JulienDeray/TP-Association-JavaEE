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
import java.util.List;

/**
 * Servlet implementation class ListArticle
 */
@WebServlet(urlPatterns={"/List","/List/*"})
public class ListArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Inject
    ServletContext context;

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
		
		if(request.getParameter("article")!= null){
			int id = Integer.parseInt(request.getParameter("article"));
			Article article = service.load(id);
			if(article==null){
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "La page entr�e n'est pas valide ");
				return;
			}
			article.setArStock(article.getArStock()-1);
			service.save(article);
			HttpSession session = request.getSession();
			ArrayList<Article> articles = (ArrayList<Article>) session.getAttribute("orderInProcess");
			if(articles==null){
				articles = new ArrayList<Article>();
			}
			articles.add(article);
		}
		
		List<Article> articles = service.loadAll();
		RequestDispatcher rd =null;
		request.setAttribute("articles", articles);
		rd = context.getRequestDispatcher("/jsp/Articles.jsp");
		rd.include(request, response);

	}
	
}
