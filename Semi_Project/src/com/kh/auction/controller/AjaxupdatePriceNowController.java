package com.kh.auction.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.auction.model.service.AuctionService;
import com.kh.auction.model.vo.Auction;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxupdatePriceNowController
 */
@WebServlet("/updatePriceNow.au")
public class AjaxupdatePriceNowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxupdatePriceNowController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		int auctionNo = Integer.parseInt(request.getParameter("auctionNo"));
		int priceNow = Integer.parseInt(request.getParameter("priceNow"));
		
		Auction a = new Auction();
		a.setAuctionNo(auctionNo);
		a.setPriceNow(priceNow);
		a.setMemNo(String.valueOf(memNo));
		
		int result = new AuctionService().updatePriceNow(a);
		
		response.setContentType("html/text; charset=UTF-8");
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
