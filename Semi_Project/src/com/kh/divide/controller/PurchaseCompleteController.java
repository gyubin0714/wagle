package com.kh.divide.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.divide.model.service.DivideService;
import com.kh.divide.model.vo.Divide;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class PurchaseCompleteController
 */
@WebServlet("/purchase.di")
public class PurchaseCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseCompleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			int pNo = Integer.parseInt(request.getParameter("pno"));
		
			Product p = new DivideService().purchaseBoard(pNo);
			
			ArrayList<Attachment> list = new ProductService().selectAttachment(pNo);
				
			request.setAttribute("p", p);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("views/divide/purchaseComplete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
