package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemService;

/**
 * Servlet implementation class MyReviewFormController
 */
@WebServlet("/myReviewList.me")
public class MyReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		
		new MemService().insertReview(memNo, productNo);
		
		request.setAttribute("memNo", memNo);
		request.setAttribute("productNo", productNo);
		
		//응답화면
        RequestDispatcher view = request.getRequestDispatcher("/views/member/reviewForm.jsp");
		
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
