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

/**
 * Servlet implementation class AuctionSearchController
 */
@WebServlet("/search.au")
public class AuctionSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchSelect = request.getParameter("searchSelect");
		String searchText = request.getParameter("searchText");

		int startNum = 0;

		
		ArrayList<Auction> searchList = new AuctionService().searchAuction(searchSelect, searchText, startNum);

		request.setAttribute("searchList", searchList);
		request.setAttribute("searchText", searchText);
		request.setAttribute("searchSelect", searchSelect);

		request.getRequestDispatcher("views/auction/auctionSearchListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
