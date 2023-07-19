package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.comment.Comment;
import model.comment.CommentDao;

/**
 * Servlet implementation class SelectProfileCommentAction
 */
public class SelectProfileCommentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProfileCommentAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentDao commentDao = CommentDao.getInstance();
		String id = request.getParameter("id");
		
		// 시작페이지, 표시될 페이지개수
		int startIdx = Integer.parseInt(request.getParameter("startIdx"));
		int countPosts = 5;
		
		ArrayList<Comment> commentList = commentDao.getCommentByIdAndIdx(id, startIdx, countPosts);
		JSONObject jObject = new JSONObject();

		jObject.put("commentList", commentList);

		response.setContentType("application/x-json; charset=utf-8");
		response.getWriter().print(jObject);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
