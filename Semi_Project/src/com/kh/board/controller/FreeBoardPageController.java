package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class FreeBoardPageController
 */
@WebServlet("/freeBoard.bo")
public class FreeBoardPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FreeBoardPageController() {
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
		
		listCount = new BoardService().selectBoardListCount();
		
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
		
		
		ArrayList<Board> pageBoardList = new BoardService().selectBoardList(pi);
		
		if(pageBoardList.isEmpty()) {
			System.out.println("pageBoardList 잘안됨");
		} else {
			System.out.println("pageBoardList 너무잘됨");
			request.setAttribute("pageBoardList",pageBoardList);
			request.setAttribute("pi", pi);
			request.getRequestDispatcher("views/board/freeBoardPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
