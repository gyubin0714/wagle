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
import com.kh.auction.model.vo.AuctionRecode;

/**
 * Servlet implementation class AjaxRecodeListController
 */
@WebServlet("/selectRecode.au")
public class AjaxRecodeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxRecodeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int auctionNo = Integer.parseInt(request.getParameter("auctionNo"));
		
		ArrayList<AuctionRecode> list = new AuctionService().selectRecodeList(auctionNo);
		
		response.setContentType("application/json; charset=UTF-8"); 
		
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
