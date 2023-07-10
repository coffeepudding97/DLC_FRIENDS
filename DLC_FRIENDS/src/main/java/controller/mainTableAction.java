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

import model.main.mainTable;
import model.main.mainTableDao;
import model.post.PostResponseDto;

/**
 * Servlet implementation class mainTableAction
 */
public class mainTableAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainTableAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		mainTableDao maintableDao = mainTableDao.getInstance();
		ArrayList<mainTable> mainList = maintableDao.getPostAll();
		
		System.out.println(mainList);
	}

}
