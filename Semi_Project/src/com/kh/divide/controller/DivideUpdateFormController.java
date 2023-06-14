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
import com.kh.product.model.vo.ProductCategory;

/**
 * Servlet implementation class DivideUpdateFormController
 */
@WebServlet("/updateForm.di")
public class DivideUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivideUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<ProductCategory> list = new DivideService().selectCategoryList();
		
		int dno = Integer.parseInt(request.getParameter("dno"));
		
		Divide d = new DivideService().selectBoard(dno);
		
		ArrayList<Attachment> at = new DivideService().selectAttachmentList(dno);
		
		request.setAttribute("list", list);
		request.setAttribute("at", at);
		request.setAttribute("d", d);
		
		request.getRequestDispatcher("views/divide/boardUpdateForm.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
