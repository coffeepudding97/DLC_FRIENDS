package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import model.comment.Comment;
import model.comment.CommentDao;
import model.post.Post;
import model.post.PostDao;
import model.post.PostResponseDto;
import model.profile.Profile;
import model.profile.ProfileDao;
import model.profile.ProfileDto;
import model.user.User;
import model.user.UserDao;

/**
 * Servlet implementation class ProfileFormAction
 */
public class ProfileFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProfileFormAction() {
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		ProfileDto profileDto = null;
		PostResponseDto postDto = null;

		System.out.println("id : " + id);
		
		profileDto = new ProfileDto(id);
		
		ProfileDao profileDao = ProfileDao.getInstance();
		Profile profile = profileDao.getUserProfile(id);

		PostDao postDao = PostDao.getInstance();
		ArrayList<Post> postList = postDao.getPostByUserId(id); 
		
		CommentDao commentDao = CommentDao.getInstance();
		ArrayList<Comment> commentList = commentDao.getCommentsByUserId(id);

		
		System.out.println("profile>" + profile);
		System.out.println("postList>" + postList);
		System.out.println("commentList>" + commentList);
		
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