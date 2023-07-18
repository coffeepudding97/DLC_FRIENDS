package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.party.Party;
import model.party.PartyDao;
import model.post.Post;
import model.post.PostDao;
import model.rating.Rating;
import model.rating.RatingDao;

/**
 * Servlet implementation class LoadRatingsAction
 */
//@WebServlet("/LoadRatingsAction")
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
		
		//ArrayList<Integer> postNos = null;
		//ArrayList<Party> partys = new ArrayList<Party>();
		ArrayList<Rating> ratings = null;
		
		//PartyDao partyDao = PartyDao.getInstance();
		RatingDao ratingDao = RatingDao.getInstance();
		
		//postNos = partyDao.getPostNosByUserId(rater);
		
		PostDao postDao = PostDao.getInstance();
	
		/*postNos = postDao.getTimeEndPostNosByPostNos(postNos);
		
		for(int postNo : postNos) {
			Party party = partyDao.getPartyExceptSelfByPostNo(postNo, rater);
			partys.add(party);
		}*/
					
		
		ratings = ratingDao.getRatingsByRater(rater);
		
		ArrayList<Integer> postNos = new ArrayList<Integer>();
		
		
		for(Rating rating: ratings) {
			postNos.add(rating.getPostNo());
		}
		
		//JSONArray json = new JSONArray(partys);
		
		//ArrayList<Integer> postNos = ratingDao.getPostNosByRatings(ratings);
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		for(Integer postNo : postNos) {
			posts.add(postDao.getPostByPostNo(postNo));
		}
		
		Timestamp cur = new Timestamp(System.currentTimeMillis());
		for(int i=posts.size()-1;i>=0;i--) {
			if(posts.get(i).getLeaveTime().compareTo(cur) > 0) {
				posts.remove(i);
				ratings.remove(i);
			}
		}
		
		JSONArray jsonRatings = new JSONArray(ratings);
		for(int i=0; i<jsonRatings.length(); i++) {
			JSONObject json = jsonRatings.getJSONObject(i);
			System.out.println(posts.get(i).getLeaveTime());
			json.put("title", posts.get(i).getTitle());
			json.put("gametitle", posts.get(i).getGameTitle());
			//json.put("title", titles.get(i));
		}
		
		response.getWriter().append(jsonRatings.toString());
	}

}
