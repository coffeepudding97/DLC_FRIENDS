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

import org.json.JSONObject;

import javax.servlet.http.HttpSession;

import model.comment.Comment;
import model.comment.CommentDao;
import model.gametitle.GameTitle;
import model.gametitle.GameTitleDao;
import model.party.Party;
import model.party.PartyDao;
import model.post.Post;
import model.post.PostDao;
import model.post.PostResponseDto;
import model.profile.Profile;
import model.profile.ProfileDao;
import model.profile.ProfileDto;
import model.rating.Rating;
import model.rating.RatingDao;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				CommentDao commentDao = CommentDao.getInstance();
				ArrayList<Comment> commentList = commentDao.getCommentsByUserId(log);

				GameTitleDao gameTitleDao = GameTitleDao.getInstance();
				ArrayList<GameTitle> gameList = gameTitleDao.allGameTitle();
				
				SelectGamesDao selectGamesDao = SelectGamesDao.getInstance();
				ArrayList<SelectGames> selectGameList = selectGamesDao.getSelectedListById(log);
				System.out.println(selectGameList);
				
				PartyDao partyDao = PartyDao.getInstance();
				ArrayList<Party> partyMemList = partyDao.getPartyById(log);
				System.out.println(partyMemList);
				
				RatingDao ratingDao = RatingDao.getInstance();
				ArrayList<Rating> rateList = ratingDao.getRatingsById(log);
				System.out.println("신고리스트:" + rateList);
				
				// 게임 평가 처리
				int rateCount = rateList.size();
				int totalScore = 5; // default값
				
				if(rateList != null && rateCount > 0) {
					int score = 0;
					System.out.println(rateCount);
					for(int i=0; i<rateCount; i++) {
						score += rateList.get(i).getScore();
					}
					score /= rateCount;
					totalScore = score;
					System.out.println("스코어: "+totalScore);
				}
				
				request.setAttribute("profile", profile);
				request.setAttribute("commentList", commentList);
				request.setAttribute("gameList", gameList);
				request.setAttribute("selectGameList", selectGameList);
				request.setAttribute("partyMemList", partyMemList);
				// 유저평점이 2점 미만이면 신고태그가 프로필화면에 표시
				if(totalScore < 5) {
					request.setAttribute("rateList", rateList);
				}

				url = "profile";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}

		} else {
			response.sendRedirect(url);
		}

	}

}