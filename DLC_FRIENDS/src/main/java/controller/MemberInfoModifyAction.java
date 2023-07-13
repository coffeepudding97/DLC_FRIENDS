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

import model.comment.Comment;
import model.comment.CommentDao;
import model.gametitle.GameTitle;
import model.gametitle.GameTitleDao;
import model.post.Post;
import model.post.PostDao;
import model.profile.Profile;
import model.profile.ProfileDao;
import model.selectgames.SelectGames;
import model.selectgames.SelectGamesDao;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameTitleDao gameTitleDao = GameTitleDao.getInstance();
		ArrayList<GameTitle> gameList = gameTitleDao.allGameTitle();
		System.out.println(gameList);
		request.setAttribute("gameList", gameList);
		
		String val = (String) request.getSession().getAttribute("log");
		SelectGamesDao selectGamesDao = SelectGamesDao.getInstance();
		ArrayList<SelectGames> selectGameList = selectGamesDao.getSelectedListById(val);
		System.out.println("gameList: "+selectGameList);
		request.setAttribute("selectGameList", selectGameList);
		
		request.getRequestDispatcher("profileUpdate").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRequestDto userDto = null; 
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		System.out.println(id);
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPw");
//		String nickname = request.getParameter("nickname");
		String info = request.getParameter("info");
		
		userDto = new UserRequestDto(id, newPassword, info);
		
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
