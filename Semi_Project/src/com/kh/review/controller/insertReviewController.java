package com.kh.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.review.model.service.ReviewService;
import com.kh.review.model.vo.Review;

/**
 * Servlet implementation class insertReviewController
 */
@WebServlet("/insertreview.ri")
public class insertReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		int pno = Integer.parseInt(request.getParameter("pNo"));
		String content = request.getParameter("review-area");
		
		Review r = new Review();
		r.setMemNo(memNo);
		r.setpNo(pno);
		r.setContent(content);
		
		
		int result= new ReviewService().insertReview(r);
		
		if(result > 0) { 
			request.setAttribute("alertMsg", "게시글 작성 성공!\n상품리스트로 돌아갑니다!");
			response.sendRedirect(request.getContextPath()+"/list.po?cpage=1");
		}else {
			request.setAttribute("errorMsg", "리뷰 작성 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
