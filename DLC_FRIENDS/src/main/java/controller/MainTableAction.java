package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.main.MainTable;
import model.main.MainTableDao;
import model.post.PostResponseDto;

/**
 * Servlet implementation class mainTableAction
 */
public class MainTableAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainTableAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MainTableDao MaintableDao = MainTableDao.getInstance();
		ArrayList<MainTable> mainList = MaintableDao.getPostAll();
		request.setAttribute("mainList", mainList);
		
		request.getRequestDispatcher("index").forward(request, response);
	}

}
