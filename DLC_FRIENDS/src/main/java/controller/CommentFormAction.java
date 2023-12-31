package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.comment.CommentDao;
import model.comment.CommentRequestDto;

/**
 * Servlet implementation class CommentFormAction
 */
//@WebServlet("/CommentFormAction")
public class CommentFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentFormAction() {
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
		
		String log = (String) request.getSession().getAttribute("log");
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		// String userId = request.getParameter("userId");
		String content = request.getParameter("comment");
		int rpNo = Integer.parseInt(request.getParameter("rpNo"));
		
		CommentRequestDto cmtDto = new CommentRequestDto(log, content, rpNo);			
		
		CommentDao cmtDao = CommentDao.getInstance();
		
		boolean result = false;
		if(rpNo==0) {
			result = cmtDao.createComment(cmtDto, postNo);
		}else {
			result = cmtDao.createReply(cmtDto, postNo);
		}
		response.getWriter().append(result + "");
	}

}
