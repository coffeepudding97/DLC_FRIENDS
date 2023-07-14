package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.rating.Rating;
import model.rating.RatingDao;

/**
 * Servlet implementation class GetWarningPostAction
 */
//@WebServlet("/GetWarningPostAction")
public class GetWarningPostAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWarningPostAction() {
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
		
		
		RatingDao ratingDao = RatingDao.getInstance();
		
		ArrayList<Rating> ratings = ratingDao.getTenRatings();
		
		JSONArray list = new JSONArray(ratings);
		
		response.getWriter().append(list.toString());
	}

}
