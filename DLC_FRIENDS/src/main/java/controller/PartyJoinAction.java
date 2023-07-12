package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.party.Party;
import model.party.PartyDao;
import model.profile.Profile;
import model.profile.ProfileDao;
import model.rating.RatingDao;

/**
 * Servlet implementation class PartyJoinAction
 */
@WebServlet("/PartyJoinAction")
public class PartyJoinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartyJoinAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));		
		String userId = request.getParameter("userId");
		ProfileDao profileDao = ProfileDao.getInstance();
		
		Profile profile = profileDao.getUserProfile(userId);
		
		PartyDao partyDao = PartyDao.getInstance();
		
		RatingDao ratingDao = RatingDao.getInstance();
		
		Party party = partyDao.getPartyByPostNo(postNo);
		for(String member : party.getUserIds()) {
			ratingDao.createRating(postNo, member, userId);
			ratingDao.createRating(postNo, userId, member);
		}
		
		partyDao.join(postNo, userId);
		
		JSONObject json = new JSONObject(profile);
		response.getWriter().append(json.toString());
		
	}

}
