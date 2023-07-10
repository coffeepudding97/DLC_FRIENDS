package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.comment.Comment;
import model.comment.CommentDao;
import model.post.Post;
import model.post.PostDao;
import model.profile.Profile;
import model.profile.ProfileDao;
import model.user.UserDao;
import model.user.UserRequestDto;

/**
 * Servlet implementation class MemberInfoModify
 */
public class MemberInfoModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfoModifyAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRequestDto userDto = null; 
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPw");
//		String nickname = request.getParameter("nickname");
		
		userDto = new UserRequestDto(id, newPassword);
		
//		UserDao userDao = UserDao.getInstance();
//		userDao.updateUser(userDto, password);
		ProfileDao profileDao = ProfileDao.getInstance();
		boolean isTrue = profileDao.updateUser(userDto, password);
		System.out.println("변경: " + isTrue);
		
		Profile profile = profileDao.getUserProfile(id);
		PostDao postDao = PostDao.getInstance();
		ArrayList<Post> postList = postDao.getPostByUserId(id); 
		CommentDao commentDao = CommentDao.getInstance();
		ArrayList<Comment> commentList = commentDao.getCommentsByUserId(id);
		
		request.setAttribute("profile", profile);
		request.setAttribute("postList", postList);
		request.setAttribute("commentList", commentList);
		
		String url = "/";
		
		if(profile != null) {
			url = "profile";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else {
			// null이면 index로 이동
			response.sendRedirect(url);
		}
	}

}
