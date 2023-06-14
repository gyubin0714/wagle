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

@WebServlet("/search.bo")
public class SearchBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchBoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");
		
		if(keyword.equals("title")) {
			
			int listCount;
			int currentPage;
			int pageLimit;
			int boardLimit;
			
			int maxPage;
			int startPage;
			int endPage;
			
			listCount = new BoardService().searchTitleSelectCount(search);
			
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

			ArrayList<Board> pageBoardList = new BoardService().selectSearchBoardList(pi, search);
			
			if(pageBoardList.isEmpty()) {
				request.setAttribute("listCount", listCount);
				request.setAttribute("search", search);
				request.getRequestDispatcher("views/board/searchResultErrorPage.jsp").forward(request, response);
			} else {
				System.out.println("pageBoardList 너무잘됨");
				request.setAttribute("listCount", listCount);
				request.setAttribute("search", search);
				request.setAttribute("keyword", keyword);
				request.setAttribute("pageBoardList",pageBoardList);
				request.setAttribute("pi", pi);
				request.getRequestDispatcher("views/board/searchResultBoardPage.jsp").forward(request, response);
			}
			
		} else if(keyword.equals("writer")) {
			
			int listCount;
			int currentPage;
			int pageLimit;
			int boardLimit;
			
			int maxPage;
			int startPage;
			int endPage;
			
			listCount = new BoardService().searchWriterSelectCount(search);
			
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

			ArrayList<Board> pageBoardList = new BoardService().searchWriterBoardList(pi, search);
			
			if(pageBoardList.isEmpty()) {
				request.setAttribute("listCount", listCount);
				request.setAttribute("search", search);
				request.getRequestDispatcher("views/board/searchResultErrorPage.jsp").forward(request, response);
			} else {
				System.out.println("pageBoardList 너무잘됨");
				request.setAttribute("listCount", listCount);
				request.setAttribute("pageBoardList",pageBoardList);
				request.setAttribute("keyword", keyword);
				request.setAttribute("search", search);
				request.setAttribute("pi", pi);
				request.getRequestDispatcher("views/board/searchResultBoardPage.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
