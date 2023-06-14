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

@WebServlet("/productBoard.bo")
public class productBoardPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public productBoardPageController() {
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
		
		listCount = new BoardService().selectProductBoardListCount();
		currentPage = Integer.parseInt(request.getParameter("aPage")); // 1
		pageLimit = 10;
		boardLimit = 15;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// VO가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> pageBoardProductList = new BoardService().selectProductBoardList(pi);
		
		if(pageBoardProductList.isEmpty()) {
			System.out.println("pageList 잘안됨");
		} else {
			System.out.println("pageList 너무잘됨");
			request.setAttribute("pageBoardProductList",pageBoardProductList);
			request.setAttribute("pi", pi);
			request.getRequestDispatcher("views/board/productBoardPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
