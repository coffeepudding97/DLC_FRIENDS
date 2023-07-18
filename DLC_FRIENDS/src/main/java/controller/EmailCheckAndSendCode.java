package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.user.User;
import model.user.UserDao;
import util.MailSender;

/**
 * Servlet implementation class EmailCheckAndSendCode
 */
//@WebServlet("/EmailCheckAndSendCode")
public class EmailCheckAndSendCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCheckAndSendCode() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// System.out.println("send servlet");
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String addr = request.getParameter("addr");
		String id = request.getParameter("id");
		
		UserDao userDao = UserDao.getInstance();
		String userId = userDao.checkEmail(addr, id);
		System.out.println(userId);
		JSONObject json = new JSONObject();

		if(userId != "") {
			MailSender mailSender = MailSender.getInstance();
			String code = mailSender.gmailSend(addr);
			
			Long time = System.currentTimeMillis() + 180000;
			
			json.put("code", code);
			json.put("time", time);
			json.put("userId", userId);
			
			if(code != null) {
				session.setAttribute("code", code);
				session.setAttribute("time", time);
			} else {
				session.setAttribute("code", null);
			}
		}
		
		response.getWriter().append(json.toString());
	}

}
