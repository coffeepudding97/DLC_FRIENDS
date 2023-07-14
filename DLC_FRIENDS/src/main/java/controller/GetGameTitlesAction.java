package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.gametitle.GameTitle;
import model.gametitle.GameTitleDao;

/**
 * Servlet implementation class GetGameTitlesAction
 */
//@WebServlet("/GetGameTitlesAction")
public class GetGameTitlesAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGameTitlesAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		GameTitleDao gameTitleDao = GameTitleDao.getInstance();
		
		ArrayList<GameTitle> gameTitles = gameTitleDao.allGameTitle();
		
		JSONArray json = new JSONArray(gameTitles);
		
		response.getWriter().append(json.toString());
	}

}
