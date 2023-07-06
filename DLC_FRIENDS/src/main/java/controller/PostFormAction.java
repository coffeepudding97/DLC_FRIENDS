package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.party.Party;
import model.party.PartyDao;
import model.party.PartyRequestDto;
import model.post.PostDao;
import model.post.PostRequestDto;

/**
 * Servlet implementation class PostFormAction
 */
//@WebServlet("/PostFormAction")
public class PostFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostFormAction() {
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
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String user_id = request.getParameter("user_id");
		String gameTitle = request.getParameter("gametitle");
		int recruitMax = Integer.parseInt(request.getParameter("recruitment-max"));
		
		String meetTimeDateTimeLocal = request.getParameter("meettime");
		String leaveTimeDateTimeLocal = request.getParameter("leavetime");
		meetTimeDateTimeLocal += ":00";
		leaveTimeDateTimeLocal += ":00";
		Timestamp meetTime = Timestamp.valueOf(meetTimeDateTimeLocal.replace("T", " "));
		Timestamp leaveTime = Timestamp.valueOf(leaveTimeDateTimeLocal.replace("T", " "));
		String content = request.getParameter("content");

		PostRequestDto post = new PostRequestDto(title, user_id, gameTitle, recruitMax, meetTime, leaveTime, content);
		
		System.out.println("post : " + post.getUser_id());
		System.out.println(post.getTitle());
		
		PostDao postDao = PostDao.getInstance();
		int createdPostNo = postDao.createPost(post);
		
		boolean result = false;
		
		if(createdPostNo != 0) {
			ArrayList<String> userIds = new ArrayList<String>();
			userIds.add(user_id);
			PartyRequestDto party = new PartyRequestDto(createdPostNo, userIds);
			
			PartyDao partyDao = PartyDao.getInstance();
			result = partyDao.createParty(party, createdPostNo);
		}
	}

}
