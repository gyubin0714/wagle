package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.member.model.service.MemService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class MyEnrollProductController
 */
@WebServlet("/myEnrollProduct.me")
public class MyEnrollProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyEnrollProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//내 등록상품 페이지 띄워주기 
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		System.out.println(memNo);
		
		
		
		ArrayList<Product> list = new MemService().selectMyEnrollProduct(memNo);
		System.out.println(list);
		
		request.setAttribute("list", list);
		request.setAttribute("memNo", memNo);
		
		
		// 1. RequestDispatcher 객체를 이용하는 방법(forwarding)
		  RequestDispatcher view = request.getRequestDispatcher("views/member/myEnrollProduct.jsp");
		  view.forward(request, response);
		  
		  request.getRequestDispatcher("views/member/myPage.jsp");
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
