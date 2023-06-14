package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.common.model.vo.PageInfo;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class ProductListAdminController
 */
@WebServlet("/productList.ad")
public class ProductListAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 인코딩x
		
		
		// 상품 조회 List
		ArrayList<Product> list = new AdminService().selectProductAdmin();
	
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).getAuctionNo());
//		}

		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/admin/productListAdmin.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
