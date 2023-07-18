package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.comment.Comment;
import model.comment.CommentDao;
import model.comment.CommentResponseDto;
import model.party.Party;
import model.party.PartyDao;
import model.party.PartyRequestDto;
import model.post.Post;
import model.post.PostDao;
import model.post.PostRequestDto;
import model.post.PostResponseDto;
import model.profile.ProfileDao;
import model.profile.ProfileDto;
import util.TimestampToString;

/**
 * Servlet implementation class ReadPostFormAction
 */
//@WebServlet("/ReadPostFormAction")
public class ReadPostFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReadPostFormAction() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int postNo = Integer.parseInt(request.getParameter("post_no"));
		
		PostDao postDao = PostDao.getInstance();
		
		// postDao.increaseViewCount(postNo);
		Post post = postDao.getPostByPostNo(postNo);
		
		String title = post.getTitle();
		Timestamp createdTime = post.getCreatedTime();
		String userId = post.getUserId();
		String gameTitle = post.getGameTitle();
		int recruitMax = post.getRecruitMax();
		Timestamp meetTime = post.getMeetTime();
		Timestamp leaveTime = post.getLeaveTime();
		String content = post.getContent();
		int viewCount = post.getViewCount();
		String state = String.valueOf(meetTime.getTime()).compareTo(String.valueOf(System.currentTimeMillis())) > 0 ? "모집중" : "기간종료";
		
		String url = "index";
		
		if(post!=null) {
//			request.setAttribute("title", title);
//			request.setAttribute("createdTime", createdTime);
//			request.setAttribute("userId", userId);
//			request.setAttribute("gameTitle", gameTitle);
//			request.setAttribute("recruitMax", recruitMax);
//			request.setAttribute("meetTime", meetTime);
//			request.setAttribute("leaveTime", leaveTime);
//			request.setAttribute("content", content);
			
			PostResponseDto postDto = new PostResponseDto(postNo, userId, title, gameTitle, recruitMax, createdTime, meetTime, leaveTime, content, viewCount);
			PartyDao partyDao = PartyDao.getInstance();
			CommentDao commentDao = CommentDao.getInstance();
			ProfileDao profileDao = ProfileDao.getInstance();
			
			Party party = partyDao.getPartyByPostNo(postNo);
			System.out.println(party);
			
			ArrayList<String> userIds = null;
			ArrayList<ProfileDto> profileDtos = null;
			PartyRequestDto partyDto = null;
			
			userIds = party.getUserIds();
			profileDtos = profileDao.getProfileDtosByUserIds(userIds, postNo);
			System.out.println(profileDtos);
			partyDto = new PartyRequestDto(postNo, userIds);
			
			ArrayList<CommentResponseDto> cmtList = commentDao.getCommentsByPostNo(postNo);
			
			TimestampToString timestampToString = TimestampToString.getInstance();
			
			String strCreatedT = timestampToString.TTS(createdTime);
			String strMeetT = timestampToString.TTS(meetTime);
			String strleaveT = timestampToString.TTS(leaveTime);
			
			request.setAttribute("post", postDto);
			request.setAttribute("party", partyDto);
			request.setAttribute("profileDtos", profileDtos);
			request.setAttribute("cmtList", cmtList);
			request.setAttribute("createdTime", strCreatedT);
			request.setAttribute("meetTime", strMeetT);
			request.setAttribute("leaveTime", strleaveT);
			request.setAttribute("state", state);
			
			url = "post";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		//response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
