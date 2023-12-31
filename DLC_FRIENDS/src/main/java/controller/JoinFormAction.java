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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");

		// 회원가입 데이터 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String passwordChk = request.getParameter("passwordChk");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		int birth = Integer.parseInt(request.getParameter("birth"));
		System.out.print("id : " + id);
		System.out.print(" pw : " + password);
		System.out.print(" pwc : " + passwordChk);
		System.out.print(" name : " + nickName);
		System.out.print(" email : "+email);
		System.out.println(" birth : " + birth);

		UserDao userDao = UserDao.getInstance();

		boolean duplId = userDao.duplIdCheck(id);
		boolean duplNick = userDao.duplNickname(nickName);
		boolean result = false;	
		
		System.out.println("ID중복 : " + duplId + ", Nick중복 : " + duplNick);
		if(!duplId && !duplNick) {
			UserRequestDto user = new UserRequestDto(id, passwordChk, nickName, email, birth);
			result = userDao.createUser(user);
		}

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("result", result);
		response.getWriter().append(jsonResponse.toString());
	}
}