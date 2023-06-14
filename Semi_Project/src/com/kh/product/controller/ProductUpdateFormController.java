package com.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductCategory;

/**
 * Servlet implementation class ProductUpdateFormController
 */
@WebServlet("/updateForm.po")
public class ProductUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productNo = Integer.parseInt(request.getParameter("pno"));
		
		ArrayList<ProductCategory> list = new ProductService().selectCategoryList();
		
		ArrayList<Attachment> at = new ProductService().selectAttachment(productNo);
		
		Product p = new ProductService().selectProduct(productNo);
		
		request.setAttribute("list", list);
		request.setAttribute("at", at);
		request.setAttribute("p", p);
		
		request.getRequestDispatcher("views/product/productUpdateForm.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
