package com.kh.common.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.service.CoService;
import com.kh.product.model.vo.Product;



/**
 * Servlet implementation class MyEnrollProductController
 */
@WebServlet("/newProduct.co")
public class NewProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//최근 등록 상품 페이지 띄워주기

		
		ArrayList<Product> list = new CoService().selectNewProduct();
		
		request.setAttribute("list", list);
		
		// 1. RequestDispatcher 객체를 이용하는 방법(forwarding)
		  RequestDispatcher view = request.getRequestDispatcher("views/common/newProduct.jsp");
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
