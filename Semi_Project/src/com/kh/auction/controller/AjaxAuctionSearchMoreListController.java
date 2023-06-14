package com.kh.auction.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.auction.model.service.AuctionService;
import com.kh.auction.model.vo.Auction;

/**
 * Servlet implementation class AjaxAuctionSearchMoreListController
 */
@WebServlet("/moreSearchList.au")
public class AjaxAuctionSearchMoreListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxAuctionSearchMoreListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int startNum = Integer.parseInt(request.getParameter("startNum"));
		String searchSelect = request.getParameter("searchSelect");
		String searchText = request.getParameter("searchText");
		
		ArrayList<Auction> moreSearchList = new AuctionService().searchAuction(searchSelect, searchText,startNum);
	
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(moreSearchList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
