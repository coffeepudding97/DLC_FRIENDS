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
		
		Post post = postDao.getPostByPostNo(postNo);
		
		String title = post.getTitle();
		Timestamp createdTime = post.getCreatedTime();
		String userId = post.getUserId();
		String gameTitle = post.getGameTitle();
		int recruitMax = post.getRecruitMax();
		Timestamp meetTime = post.getMeetTime();
		Timestamp leaveTime = post.getLeaveTime();
		String content = post.getContent();
		
		
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
			
			PostResponseDto postDto = new PostResponseDto(postNo, userId, title, gameTitle, recruitMax, createdTime, meetTime, leaveTime, content, 0);
			PartyDao partyDao = PartyDao.getInstance();
			CommentDao commentDao = CommentDao.getInstance();
			
			Party party = partyDao.getPartyByPostNo(postNo);
			
			int partyNo = party.getPartyNo();
			ArrayList<String> userIds = party.getUserIds();
			
			PartyRequestDto partyDto = new PartyRequestDto(postNo, userIds);
			
			ArrayList<CommentResponseDto> cmtList = commentDao.getCommentsByPostNo(postNo);
			
			request.setAttribute("post", postDto);
			request.setAttribute("party", partyDto);
			request.setAttribute("cmtList", cmtList);
			System.out.println("cmtlist(0) " + cmtList.get(0).getUserId());
			
			url = "post";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
