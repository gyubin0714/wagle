package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemService;
import com.kh.product.model.vo.Product;
import com.kh.review.model.vo.Review;

/**
 * Servlet implementation class MyReviewController
 */
@WebServlet("/myReview.me")
public class MyReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내 리뷰 페이지 띄워주기
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		
		
		//int productNo = Integer.parseInt(request.getParameter("productNo"));
		
		
		ArrayList<Product>list = new MemService().writableReview(memNo);
		
		ArrayList<Review> reviewList = new MemService().selectReviewList(memNo);
		
		request.setAttribute("list", list);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("memNo",  memNo);
		//request.setAttribute("productNo", productNo);
		
		
		RequestDispatcher view = request.getRequestDispatcher("views/member/myReview.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
