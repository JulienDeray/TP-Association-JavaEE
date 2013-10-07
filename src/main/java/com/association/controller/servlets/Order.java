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

/**
 * Servlet implementation class Order
 */
@WebServlet(urlPatterns={"/Order","/Order/*"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("adherent")==null){
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		if(request.getParameter("cancel")!=null && request.getParameter("cancel").equals("True")){
			HttpSession session = request.getSession();
			session.setAttribute("orderInProcess", new ArrayList<Article>());
		}
		
		ServletContext context = getServletContext();
		RequestDispatcher rd =null;
		rd = context.getRequestDispatcher("/jsp/CommandeEnCours.jsp");
		rd.include(request, response);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
