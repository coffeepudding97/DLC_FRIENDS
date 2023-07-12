package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.UserDao;
import model.user.UserRequestDto;

/**
 * Servlet implementation class JoinFormAction
 */
//@WebServlet("/JoinFormAction")
public class JoinFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public JoinFormAction() {
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
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id"); // re
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		int birth = Integer.parseInt(request.getParameter("birth"));
		
		UserDao userDao = UserDao.getInstance();
		
		boolean duplId = userDao.duplIdCheck(id);
		boolean duplNick = userDao.duplNickname(nickName);
		boolean result = false;
		
		String url = "join";
		// 비밀번호와 확인 비밀번호가 같은 경우
		if (!password.equals(confirmPassword)) {
			System.out.println("비밀번호 다시 입력");
		} else {
			// 아이디 중복이 없는 경우
			if (!duplId) {
				System.out.println("아이디 중복 없음");

				// 닉네임 중복이 없는 경우
				if (!duplNick) {
					System.out.println("닉네임 중복 없음");

					// 사용자 생성
					UserRequestDto user = new UserRequestDto(id, password, nickName, email, birth);
					result = userDao.createUser(user);
					url = "login";
				} else {
					// 닉네임 중복인 경우
					System.out.println("닉네임 중복 있음");
				}
			} else {
				// 아이디 중복인 경우
				System.out.println("아이디 중복 있음");
			}
		}
		
		System.out.println("결과: " + result);
		response.sendRedirect(url);
	}

}
