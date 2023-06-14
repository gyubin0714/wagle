package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class SearchBoardController
 */
@WebServlet("/searchBoard.ad")
public class SearchBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String searchField = request.getParameter("searchField");
		String searchText = request.getParameter("searchText");
		
		ArrayList<Board> list = new AdminService().searchBoard(searchField, searchText);
	
		request.setAttribute("list", list);
		request.setAttribute("searchField", searchField);
		request.setAttribute("searchText", searchText);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/boardListAdmin.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
