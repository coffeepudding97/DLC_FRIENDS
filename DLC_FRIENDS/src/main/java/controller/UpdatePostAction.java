package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.post.Post;
import model.post.PostDao;
import model.post.PostResponseDto;

/**
 * Servlet implementation class UpdatePostAction
 */
@WebServlet("/UpdatePostAction")
public class UpdatePostAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePostAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		PostDao postDao = PostDao.getInstance();
		
		Post post = postDao.getPostByPostNo(postNo);
		
		request.setAttribute("post", post);
		
		String url = "postUpdate";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}