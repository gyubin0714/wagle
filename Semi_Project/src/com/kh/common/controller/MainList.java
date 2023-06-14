package com.kh.common.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.auction.model.vo.Auction;
import com.kh.common.model.service.CommonService;

/**
 * Servlet implementation class MainList
 */
@WebServlet("/mainList")
public class MainList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList list = new ArrayList();
		ArrayList<Auction> newList = new CommonService().selectNew();
		ArrayList<Auction> bestList = new CommonService().selectBest();
		ArrayList<Auction> auctionList = new CommonService().selectAuction();
		
		list.add(newList);
		list.add(bestList);
		list.add(auctionList);
		
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
