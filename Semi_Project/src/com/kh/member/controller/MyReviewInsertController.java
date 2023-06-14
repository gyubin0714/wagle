package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemService;
import com.kh.review.model.vo.Review;

/**
 * Servlet implementation class MyReviewInsertController
 */
@WebServlet("/reviewInsert.me")
public class MyReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기
		String content = request.getParameter("content");
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		//System.out.println(productNo);
		int memNo = Integer.parseInt(request.getParameter("memNo"));	
		
		//int memNo = Integer.parseInt(request.getParameter("memNo"));
        //System.out.println(memNo);
	   
	   Review r = new Review();
	   //r.setMemNo(memNo);
	   r.setMemNo(memNo);
	   r.setpNo(productNo);
	   r.setContent(content);
	   
	   int result = new MemService().insertReview(r);
	   HttpSession session = request.getSession();
		
	   
	   if(result > 0 ){
		   request.removeAttribute("productNo");
		   response.sendRedirect(request.getContextPath() + "/myReview.me?memNo=" + memNo);

	   }else {
		   request.setAttribute("errorMsg", "리뷰작성 실패");
		   request.getRequestDispatcher("views/common.errorPage.jsp").forward(request, response);
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
