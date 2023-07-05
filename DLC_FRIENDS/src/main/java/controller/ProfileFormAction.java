package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.profile.Profile;
import model.profile.ProfileDao;

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
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		ProfileDao profileDao = ProfileDao.getInstance();
		Profile userProfile = profileDao.getUserProfile(id);
		
		// session 아이디와 일치할경우 본인 프로필, 수정버튼 표시
		if(session.getAttribute("log") == id) {
			
		}else if(session.getAttribute("log") == null) {
			
		}else if(session.getAttribute("log") != id) {
			
		}
	}

}
