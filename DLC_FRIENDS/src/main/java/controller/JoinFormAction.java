package controller;

import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
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
//@WebServlet("/joinForm")
public class JoinFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public JoinFormAction() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json");
			request.setCharacterEncoding("UTF-8");
		
		
	        // 회원가입 데이터 처리
	        String id = request.getParameter("id");
	        String password = request.getParameter("password");
	        String passwordChk = request.getParameter("passwordChk");
	        String nickName = request.getParameter("nickName");
	        String email = request.getParameter("email");
	        int birth = Integer.parseInt(request.getParameter("birth"));
	        System.out.println(id);
	        System.out.println(password);
	        System.out.println(passwordChk);
	        System.out.println(nickName);
	        System.out.println(email);
	        System.out.println(birth);
	        
	        UserDao userDao = UserDao.getInstance();
	        
	        boolean duplId = userDao.duplIdCheck(id);
	        boolean duplNick = userDao.duplNickname(nickName);
	        boolean result = false;

	        String message = "";
	         
	        if (!password.equals(passwordChk)) {
	        	 message = "비밀번호가 일치하지 않습니다.";
	        } else {
	            if (!duplId) {
	            	System.out.println("아이디 중복 없음");

	                if (!duplNick) {
	                	 System.out.println("닉네임 중복 없음");
	                	 UserRequestDto user = new UserRequestDto(id, password, nickName, email, birth);
	                     result = userDao.createUser(user);
	                     message = "Success";
	                } else {
	                	message = "이미 존재하는 닉네임입니다.";
	                }
	            } else {
	            	message = "이미 존재하는 아이디입니다.";
	            }
	        }
	        
	        if(message.equals("Success")) {
	        	response.sendRedirect("/views/login.jsp");
	        } else {
	        	JSONObject jsonResponse = new JSONObject();
		        jsonResponse.put("message", message);
		        response.getWriter().write(jsonResponse.toString());
	        }       
}
}