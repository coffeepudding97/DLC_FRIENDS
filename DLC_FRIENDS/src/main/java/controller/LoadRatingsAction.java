package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.party.Party;
import model.party.PartyDao;
import model.post.PostDao;
import model.rating.Rating;
import model.rating.RatingDao;

/**
 * Servlet implementation class LoadRatingsAction
 */
@WebServlet("/LoadRatingsAction")
public class LoadRatingsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadRatingsAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String rater = request.getParameter("userId");
		
		ArrayList<Integer> postNos = null;
		ArrayList<Party> partys = new ArrayList<Party>();
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		
		PartyDao partyDao = PartyDao.getInstance();
		RatingDao ratingDao = RatingDao.getInstance();
		
		postNos = partyDao.getPostNosByUserId(rater);
		
		PostDao postDao = PostDao.getInstance();
	
		postNos = postDao.getTimeEndPostNosByPostNos(postNos);
		
		for(int postNo : postNos) {
			Party party = partyDao.getPartyExceptSelfByPostNo(postNo, rater);
			ArrayList<Rating> list = ratingDao.getRatingByPostNoAndUserId(postNo, rater);
			partys.add(party);
			ratings.addAll(list);
		}
					
		JSONArray json = new JSONArray(partys);
		JSONArray jsonRatings = new JSONArray(ratings);
		
		response.getWriter().append(jsonRatings.toString());
	}

}
