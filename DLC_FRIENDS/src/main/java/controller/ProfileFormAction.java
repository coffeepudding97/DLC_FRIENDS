package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		ProfileDto profileDto = null;

		String id = request.getParameter("id");
		System.out.println("id : " + id);
		
		profileDto = new ProfileDto(id);
		
		ProfileDao profileDao = ProfileDao.getInstance();
		Profile profile = profileDao.getUserProfile(id);
		System.out.println("?>" + profile);
		
		request.setAttribute("profile", profile);
		
		String url = "/";
		
		if(profile != null) {
			System.out.println("들어옴");
			url = "profile";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else {
			// null이면 index로 이동
			response.sendRedirect(url);
		}
		

	}

}