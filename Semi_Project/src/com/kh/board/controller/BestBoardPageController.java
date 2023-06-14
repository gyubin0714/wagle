package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

@WebServlet("/bestBoard.bo")
public class BestBoardPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BestBoardPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount;
		int currentPage;
		int pageLimit;
		int boardLimit;
		
		int maxPage;
		int startPage;
		int endPage;
		
		listCount = new BoardService().selectBestBoardListCount();
		
		currentPage = Integer.parseInt(request.getParameter("aPage"));
		
		pageLimit = 10;
		
		boardLimit = 15;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> pageBoardBestList = new BoardService().selectBestBoardList(pi);
		
		if(pageBoardBestList.isEmpty()) {
			System.out.println("pageBoardList 잘안됨");
		} else {
			System.out.println("pageBoardBestList 너무잘됨");
			request.setAttribute("pageBoardBestList",pageBoardBestList);
			request.setAttribute("pi", pi);
			request.getRequestDispatcher("views/board/bestBoardPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
