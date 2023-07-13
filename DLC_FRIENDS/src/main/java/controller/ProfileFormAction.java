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
import model.gametitle.GameTitle;
import model.gametitle.GameTitleDao;
import model.post.Post;
import model.post.PostDao;
import model.post.PostResponseDto;
import model.profile.Profile;
import model.profile.ProfileDao;
import model.profile.ProfileDto;
import model.selectgames.SelectGames;
import model.selectgames.SelectGamesDao;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String log = (String) request.getSession().getAttribute("log");
		String value = request.getParameter("id");

		if (value != null) {
			log = value;
		}

		System.out.println("log : " + log);

		String url = "/";

		if (log != null) {
			ProfileDao profileDao = ProfileDao.getInstance();
			Profile profile = profileDao.getUserProfile(log);
			System.out.println(profile);

			if (profile == null) {
				System.out.println("없는 계정입니다.");
				url = "/";
				// 조회한 계정이 없을 경우 index로 이동
				response.sendRedirect(url);
			} else {
				PostDao postDao = PostDao.getInstance();
				ArrayList<Post> postList = postDao.getPostByUserId(log);

				CommentDao commentDao = CommentDao.getInstance();
				ArrayList<Comment> commentList = commentDao.getCommentsByUserId(log);

				GameTitleDao gameTitleDao = GameTitleDao.getInstance();
				ArrayList<GameTitle> gameList = gameTitleDao.allGameTitle();
				
				SelectGamesDao selectGamesDao = SelectGamesDao.getInstance();
				ArrayList<SelectGames> selectGameList = selectGamesDao.getSelectedListById(log);
				System.out.println(selectGameList);
				
				
				request.setAttribute("profile", profile);
				request.setAttribute("postList", postList);
				request.setAttribute("commentList", commentList);
				request.setAttribute("gameList", gameList);
				request.setAttribute("selectGameList", selectGameList);

				url = "profile";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}

		} else {
			response.sendRedirect(url);
		}



	}

}