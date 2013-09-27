package com.association;

import com.dao.AdherentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    AdherentDao adherentDao;

    /**
     * Default constructor. 
     */
    public Login() throws SQLException {
        adherentDao = new AdherentDao();
     //   adherentDao.saveAdherent(new Adherent("toto", "toto", "Toto", "Caca", "Rue de la vache qui pue", "86000", "La Havane", 1));
        System.out.println(adherentDao.getAdherentByLogin("toto", "toto"));
        System.out.println(adherentDao.getAdherentById(2));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println( getServletConfig().getServletName() );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
