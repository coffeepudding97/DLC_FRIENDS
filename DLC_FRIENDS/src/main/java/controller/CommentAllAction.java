package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.comment.CommentDao;
import model.comment.CommentResponseDto;

/**
 * Servlet implementation class CommentAllAction
 */
@WebServlet("/CommentAllAction")
public class CommentAllAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentAllAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));

		CommentDao cmtDao = CommentDao.getInstance();

		ArrayList<CommentResponseDto> list = null;
		list = cmtDao.getCommentsByPostNo(postNo);
		cmtDao.sortCommentList(list);

		JSONArray arr = new JSONArray(list);
		response.getWriter().append(arr.toString());
	}

}
