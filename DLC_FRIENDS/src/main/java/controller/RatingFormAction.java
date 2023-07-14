package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.rating.RatingDao;
import model.rating.RatingDto;

/**
 * Servlet implementation class RatingFormAction
 */
//@WebServlet("/RatingFormAction")
public class RatingFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingFormAction() {
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
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String rater = request.getParameter("rater"); // <- ajax rater 가져오기
		String rated = request.getParameter("rated");
		int score = Integer.parseInt(request.getParameter("radio"));
		String content = request.getParameter("content");
		String[] arr = request.getParameterValues("tags[]");

		int curse = 0;
		int run = 0;
		int late = 0;
		int disturb = 0;
		int hack = 0;
		for(String tag : arr) {
			if(tag.equals("curse")) {
				curse=1;
			}
			if(tag.equals("run")) {
				run=1;
			}
			if(tag.equals("late")) {
				late=1;
			}
			if(tag.equals("disturb")) {
				disturb=1;
			}
			if(tag.equals("hack")) {
				hack=1;
			}
		}
		
		RatingDto ratingDto = new RatingDto(postNo, rater, rated, content, score, curse, run, late, disturb, hack);
		
		RatingDao ratingDao = RatingDao.getInstance();
		
		ratingDao.updateRating(ratingDto);
		
		ratingDto.setFinish(true);
		
		JSONObject json = new JSONObject(ratingDto);
		
		response.getWriter().append(String.valueOf(ratingDto.isFinish()));
		// response.getWriter().append(json.toString());
		
	}

}
