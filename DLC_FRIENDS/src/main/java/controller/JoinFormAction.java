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
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		int birth = Integer.parseInt(request.getParameter("birth"));
		
		UserDao userDao = UserDao.getInstance();
		
		boolean duplId = userDao.duplIdCheck(id);
		boolean duplNick = userDao.duplNickname(nickName);
		boolean result = false;
		
		if(!duplId) {
			System.out.println("아이디 중복없음");
			
			if(!duplNick) {
				System.out.println("닉네임 중복없음");
				
				UserRequestDto user = new UserRequestDto(id, password, nickName, email, birth);
				result = userDao.createUser(user);
				
			}else {
				System.out.println("닉네임 중복있음");
				response.sendRedirect("join");
			}
			
		}else{
			System.out.println("아이디 중복있음");
			response.sendRedirect("join");
		}
		
		System.out.println("결과: " + result);
	}

}
