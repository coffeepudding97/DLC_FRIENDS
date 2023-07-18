package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.profile.Profile;
import model.profile.ProfileDao;
import model.user.User;
import model.user.UserDao;

/**
 * Servlet implementation class LoginFormAction
 */
public class LoginFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserDao userDao = UserDao.getInstance();
		User user = userDao.getUserById(id);
		
		ProfileDao profileDao = ProfileDao.getInstance();
		Profile profile = profileDao.getUserProfile2(id);
		
//		String url = "/login";
		
		JSONObject jObject = new JSONObject();
		
		String message = "";
		// 유저정보 존재하면서 비밀번호 일치
		if(user != null && user.getPassword().equals(password)) {
			session.setAttribute("log", id);
			session.setAttribute("profile", profile);

			message = "loginTrue";
		
		// 비밀번호 불일치 & 유저정보 없을 시
		} else {
			message = "noUser";
			
		} 

//		System.out.println(message);
		jObject.put("result", message);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jObject);
	}

}
