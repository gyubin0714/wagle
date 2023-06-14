package com.kh.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Interest;

/**
 * Servlet implementation class AjaxSelectInterestController
 */
@WebServlet("/selectInterest.po")
public class AjaxSelectInterestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxSelectInterestController() {
		 super();
	        // TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productNo = Integer.parseInt(request.getParameter("pno"));

		int memNo = 0;
		//로그인한 유저 정보
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		if(loginUser != null) {
			memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		}
		int check = 0;

		Interest itst = new Interest();
		itst.setProductNo(productNo);
		itst.setMemNo(memNo);

		Interest checkInterest = new ProductService().selectInterest(itst);
		
		
		if(checkInterest != null) {
			check = 1;
		} else {
			check = 0;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(check);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
