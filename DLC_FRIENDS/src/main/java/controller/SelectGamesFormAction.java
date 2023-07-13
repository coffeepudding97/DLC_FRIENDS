package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.selectgames.SelectGamesDao;
import model.selectgames.SelectGamesDto;

/**
 * Servlet implementation class FavoriteGamesFormAction
 */
public class SelectGamesFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectGamesFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String log = (String) request.getSession().getAttribute("log");
		
		String[] games = request.getParameterValues("selectGame");
		
		SelectGamesDao selectGamesDao = SelectGamesDao.getInstance();
		boolean result = selectGamesDao.updateSelectedList(log, games);
		System.out.println("result" + result);
		
		response.sendRedirect("/");
	}

}
