package com.kh.divide.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.divide.model.service.DivideService;
import com.kh.divide.model.vo.Divide;

/**
 * Servlet implementation class AuctionCompleteController
 */
@WebServlet("/auction.di")
public class AuctionCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionCompleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			int dNo = Integer.parseInt(request.getParameter("dno"));
		
			Divide d = new DivideService().auctionBoard(dNo);
			
			ArrayList<Attachment> list = new DivideService().selectAttachmentList(dNo);
				
			request.setAttribute("d", d);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("views/divide/auctionComplete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
