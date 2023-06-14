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
import com.kh.auction.model.vo.AuctionRecode;
import com.kh.common.model.vo.Attachment;
import com.kh.member.model.vo.Member;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class AuctionDetailController
 */
@WebServlet("/detail.au")
public class AuctionDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productNo = Integer.parseInt(request.getParameter("pno"));
		// 1. 조회수 요청
		int result = new ProductService().increaseCount(productNo);

		// 2. product, attachment 조회

		if(result > 0) {

			// auction 조회
			Auction a = new AuctionService().selectAuction(productNo);
			// attachment 조회
			ArrayList<Attachment> list = new ProductService().selectAttachment(productNo);
			// 입찰자수 조회
			int count = new AuctionService().selectCountMember(a.getAuctionNo());
			
			a.setCount(count);
			
			int memNo = Integer.parseInt(a.getMemNo());
			// 판매자 정보
			Member m = new ProductService().selectMember(memNo);
			// 판매자가 파는 상품 리스트
			ArrayList<Product> pList = new ProductService().selectSaleProduct(memNo, productNo);
			
			ArrayList<Product> sellPnoList = new ArrayList();		
			
			if(pList.size() == 1) {
				int sellProductNo = pList.get(0).getProductNo();
				Product sellP = new Product();
				if(sellProductNo != productNo) {
					sellP.setMemNo(Integer.toString(memNo));
					sellP.setProductNo(sellProductNo);
				}
				sellPnoList.add(sellP);
			}
			
			if(pList.size() == 2) {
				for(int i = 0; i < 2; i++) {
					int sellProductNo = pList.get(i).getProductNo();
					Product sellP = new Product();
					if(sellProductNo != productNo) {
						sellP.setMemNo(Integer.toString(memNo));
						sellP.setProductNo(sellProductNo);
					}
					sellPnoList.add(sellP);
				}
			} else if(pList.size() >= 3) {
				for(int i = 0; i < 3; i++) {
					int sellProductNo = pList.get(i).getProductNo();
					Product sellP = new Product();
					if(sellProductNo != productNo) {
						sellP.setMemNo(Integer.toString(memNo));
						sellP.setProductNo(sellProductNo);
					}
					
					sellPnoList.add(sellP);
				}
			}
			
			ArrayList<Attachment> pListAt = new ProductService().selectSaleAttachment(sellPnoList);  
			
			// 판매자 프로필
			Attachment profile = new ProductService().selectUserProfile(memNo);
			
			request.setAttribute("m", m);
			request.setAttribute("a", a);
			request.setAttribute("list", list);
			request.setAttribute("pList", pList);
			request.setAttribute("pListAt", pListAt);
			request.setAttribute("profile", profile);

			request.getRequestDispatcher("views/auction/auctionDetailView.jsp").forward(request, response);
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
