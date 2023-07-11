package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("log");
		String password = request.getParameter("password");
		System.out.println("아이디: "+id);
		
		UserDao userDao = UserDao.getInstance();
		boolean result = userDao.deleteUserById(id, password);
		
		String url = "profile";
		if(result) {
			request.getSession().removeAttribute("log");
			url = "/";
			
			String message = "유저 삭제 완료.";
	        // PrintWriter 객체를 생성해서 메소드를 통해 클라이언트에 데이터 전송
	        PrintWriter out = response.getWriter();
	        // script로 alert를 호출하고, location.href로 페이지 이동 처리.
	        out.println("<script>alert('" + message + "'); location.href='profileUpdate';</script>");
	        out.flush();
	        out.close();
		}else {
			String message = "유저 삭제 실패.";
	        // PrintWriter 객체를 생성해서 메소드를 통해 클라이언트에 데이터 전송
	        PrintWriter out = response.getWriter();
	        // script로 alert를 호출하고, location.href로 페이지 이동 처리.
	        out.println("<script>alert('" + message + "'); location.href='profileUpdate';</script>");
	        out.flush();
	        out.close();
		}
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}

}
