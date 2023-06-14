package com.kh.common.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

@WebServlet("/searchList.main")
public class searchMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public searchMainPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
		
		ArrayList<Board> searchMainBoardList = new BoardService().searchMainBoard(query);
		
		request.setAttribute("searchMainBoardList", searchMainBoardList);
		request.setAttribute("query", query);
		
		request.getRequestDispatcher("/views/common/searchMainPage.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
