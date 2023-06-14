package com.kh.auction.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.auction.model.service.AuctionService;
import com.kh.common.model.vo.Attachment;
import com.kh.member.model.vo.Member;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class BuyProductController
 */
@WebServlet("/buyProduct.au")
public class BuyProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
		// 중복 클릭 체크 
		int check = new AuctionService().checkTransAction(productNo, memNo);
		
		// 완료화면에 뿌려줄 상품 조회
		Product p = new ProductService().selectProduct(productNo);
		ArrayList<Attachment> list = new ProductService().selectAttachment(productNo);
		
		int result = 0;
		
		if(check == 0) {
		//  product를 update하고 transaction insert 
			result = new AuctionService().updateTradeStatus(productNo, memNo);
		}
		System.out.println(productNo + " " + memNo + " " + check+ " " + result);
		
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/auction/purchaseComplete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
