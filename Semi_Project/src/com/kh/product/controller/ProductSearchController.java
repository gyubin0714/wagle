package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class ProductSearchController
 */
@WebServlet("/search.po")
public class ProductSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchSelect = request.getParameter("searchSelect");
		String searchText = request.getParameter("searchText");

		int boardLimit;
		boardLimit = 12;

		
		ArrayList<Product> searchList = new ProductService().searchProduct(searchSelect, searchText, boardLimit);
		int listCount = new ProductService().selectListCount(searchSelect, searchText); 

		request.setAttribute("searchList", searchList);
		request.setAttribute("searchText", searchText);
		request.setAttribute("searchSelect", searchSelect );
		request.setAttribute("listCount", listCount);

		request.getRequestDispatcher("views/product/productSearchListView.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
