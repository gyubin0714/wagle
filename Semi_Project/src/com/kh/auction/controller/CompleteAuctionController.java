package com.kh.auction.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.auction.model.service.AuctionService;
import com.kh.auction.model.vo.Auction;
import com.kh.chat.model.sevice.ChatService;
import com.kh.common.model.vo.Attachment;
import com.kh.product.model.service.ProductService;

/**
 * Servlet implementation class CompleteAuctionController
 */
@WebServlet("/completeAuction.au")
public class CompleteAuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteAuctionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		
		int result = new ChatService().updateProductRoom(productNo);
		// auction 조회
		Auction a = new AuctionService().selectAuction(productNo);
		// attachment 조회
		ArrayList<Attachment> list = new ProductService().selectAttachment(productNo);
		
		request.setAttribute("a", a);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/auction/auctionComplete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
