package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class searchProductController
 */
@WebServlet("/searchProduct.ad")
public class SearchProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");

		String searchField = request.getParameter("searchField");
		String searchText = request.getParameter("searchText");
		
		
		// 가공 안훼
		
		ArrayList<Product> list = new AdminService().searchProductList(searchField, searchText);
		
		request.setAttribute("list", list);
		request.setAttribute("searchField", searchField);
		request.setAttribute("searchText", searchText);
		
		
		
		// 응답화면
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/productListAdmin.jsp");
		
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
