package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.post.PostDao;
import model.post.PostRequestDto;

/**
 * Servlet implementation class UpdatePostFormAction
 */
@WebServlet("/UpdatePostFormAction")
public class UpdatePostFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePostFormAction() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String title = request.getParameter("title");
		//String user_id = request.getParameter("user_id");
		String gameTitle = request.getParameter("gametitle");
		int recruitMax = Integer.parseInt(request.getParameter("recruitment-max"));
		
		String meetTimeDateTimeLocal = request.getParameter("meettime");
		String leaveTimeDateTimeLocal = request.getParameter("leavetime");
		meetTimeDateTimeLocal += ":00";
		leaveTimeDateTimeLocal += ":00";
		Timestamp meetTime = Timestamp.valueOf(meetTimeDateTimeLocal.replace("T", " "));
		Timestamp leaveTime = Timestamp.valueOf(leaveTimeDateTimeLocal.replace("T", " "));
		String content = request.getParameter("content");

		PostRequestDto post = new PostRequestDto(title, gameTitle, recruitMax, meetTime, leaveTime, content);
		
		System.out.println("post : " + post.getUser_id());
		System.out.println(post.getTitle());
		
		PostDao postDao = PostDao.getInstance();
		postDao.updatePost(post, postNo);
		
		String url = "dbtestGJ";
		
		response.sendRedirect(url);
	}

}
