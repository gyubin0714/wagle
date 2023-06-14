package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.model.vo.Attachment;
import com.kh.member.model.service.MemService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class MyReviewFormController
 */
@WebServlet("/writeReview.me")
public class MyReviewFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리뷰 작성 폼 페이지로 이동하는 controller
		
		 request.setCharacterEncoding("UTF-8");
			
	     //session영역에 담겨 있는 로그인 정보를 가져옴
		HttpSession session = request.getSession();
		
		// 해당 상품 번호 뽑기
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		//System.out.println(productNo);
		
		// 상품 번호 해당하는 product테이블 조회
		Product p = new MemService().selectProduct(productNo);
	    //System.out.println(p);
		// 상품 번호 해당하는 attachment 테이블 조회
		Attachment at = new MemService().selectProductAttachment(productNo);
		
		request.setAttribute("p", p);
		request.setAttribute("at", at);
		
		// 화면 띄우기
		
		RequestDispatcher view = request.getRequestDispatcher("views/member/reviewWritingForm.jsp");
		view.forward(request, response);
		//request.removeAttribute("productNo");
		//session.removeAttribute("productNo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
