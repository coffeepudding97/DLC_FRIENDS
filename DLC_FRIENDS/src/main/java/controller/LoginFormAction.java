package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.User;
import model.user.UserDao;

/**
 * Servlet implementation class LoginFormAction
 */
@WebServlet("/LoginFormAction")
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파라미터 값 가져오기 (id, password)
					// 2. User 조회 getUserById(id)
					// 3. 결과에 따른 페이징 처리 
					// ㄴ User 객체가 null -> loginForm.jsp  
					// ㄴ User 객체가 있으면 
//					    ㄴ 비밀번호 일치 -> main.jsp -> welcome message 
//					    ㄴ 비밀번호 불일치 -> loginForm.jsp 

					String id = request.getParameter("id");
					String password = request.getParameter("password");

					UserDao userDao = UserDao.getInstance();
					User user = userDao.getUserById(id);

					String url = "login";

					if(user != null && user.getPassword().equals(password)) {
						url = "main";   
						
						// 4. 로그인한 회원의 아이디를 -> session 에 속성값으로 저장
						HttpSession session = request.getSession();
						session.setAttribute("log", id);
					}

					response.sendRedirect(url);
	}

}
