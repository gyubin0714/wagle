package com.kh.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Interest;

/**
 * Servlet implementation class AjaxInterestUpdateController
 */
@WebServlet("/interestUpdate.po")
public class AjaxInterestUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxInterestUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productNo = Integer.parseInt(request.getParameter("pno"));
		
		//로그인한 유저 정보
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
		Interest itst = new Interest();
		itst.setProductNo(productNo);
		itst.setMemNo(memNo);
		
		int result = new ProductService().interestUpdate(itst);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
