package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.comment.Comment;
import model.comment.CommentDao;
import model.post.Post;
import model.post.PostDao;
import model.profile.Profile;
import model.profile.ProfileDao;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 30 // 30MB
)

public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileDao profileDao = ProfileDao.getInstance();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("log");
		
		try {
			Part inputImage = request.getPart("input_image");
			InputStream inputStream = inputImage.getInputStream();
			boolean isTrue = profileDao.upload(id, inputImage, inputStream);
			System.out.println("업로드: " + isTrue);
			
	        String message = "프로필 사진이 업로드되었습니다!";
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(message);
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
	        String message = "프로필사진 업로드를 실패하였습니다.";
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println(message);
	        
	        out.flush();
	        out.close();
		}
	}

}
