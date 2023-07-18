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

import model.user.UserDao;

/**
 * Servlet implementation class DeleteUserFormAction
 */
public class DeleteUserFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserFormAction() {
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
		String id = (String) session.getAttribute("log");
		String password = request.getParameter("password");
		System.out.println("아이디: "+id);
		System.out.println("비밀번호: "+password);
		
		UserDao userDao = UserDao.getInstance();
		boolean check = userDao.checkAccount(id, password);
		boolean result = false;
		if(check) {
			result = userDao.deleteUserById(id, password);
		}
		
		JSONObject jObject = new JSONObject();

		String message = "";
		if(result) {
			System.out.println("유저삭제 성공");
			request.getSession().removeAttribute("log");
			message = "success";
			
		}else {
			System.out.println("유저삭제 실패");
			message = "fail";
			
		}
		
		jObject.put("result", message);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jObject);
	}

}
