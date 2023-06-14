package com.kh.auction.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.auction.model.service.AuctionService;
import com.kh.auction.model.vo.Auction;
import com.kh.chat.model.sevice.ChatService;
import com.kh.product.model.service.ProductService;

/**
 * Servlet implementation class AjaxDetailModalController
 */
@WebServlet("/modal.au")
public class AjaxDetailModalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxDetailModalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int auctionNo = Integer.parseInt(request.getParameter("auctionNo"));
		
		Auction a = new AuctionService().selectAuctionModal(auctionNo);
		// 입찰자 수
		int count = new AuctionService().selectCountMember(auctionNo);
		// 종료 시간
		int endTime = Integer.parseInt(a.getEndTime());
		
		int day = endTime / (60 * 60 * 24);
		int hour = endTime / (60 * 60) - day * 24;
		int min = endTime / 60 - day*24*60 - hour*60 ;
		int sec = endTime - day*24*60*60 - hour*60*60 - min*60;
		
		a.setEndTime(day+"일 "+hour+"시간 "+min+"분 "+sec+"초");
		a.setCount(count);
		
		if(endTime <= 0) {
			
			// 경매를 낙찰 받은 멤버넘버를 조회
			int memNo = new AuctionService().selectSuccessMember(auctionNo);
			
			// 중복 클릭 체크 
			int check = new AuctionService().checkTransAction(a.getProductNo(), memNo);
			
			// endTime이 0보다 작을 때 product를 update하고 transaction insert 
			int result = 0;
			if(check == 0) {
				// 상품이름 조회
				int productNo = a.getProductNo();
				String productName = (new ProductService().selectProduct(productNo)).getProductName();
				
				result = new AuctionService().updateTradeStatus(a.getProductNo(), memNo);
				
				// 낙찰 메세지 보내기
				result *= new ChatService().completeAuction(productNo, memNo, productName);

			}
			response.setContentType("html/text; charset=UTF-8");
			response.getWriter().print(result);
		} else {
			response.setContentType("application/json; charset=UTF-8");
		
			new Gson().toJson(a, response.getWriter());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
