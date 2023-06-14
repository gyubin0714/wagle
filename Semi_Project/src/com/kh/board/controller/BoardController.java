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
import com.kh.board.model.vo.Notice;

@WebServlet("/board.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// (메인)공지사항게시판
		ArrayList<Notice> noticelist = new BoardService().selectNoticeBoard();
		if(noticelist.isEmpty()) {
			System.out.println("noticelist 잘못됨");
		} else {
			System.out.println("noticelist 잘됨");
			request.setAttribute("noticeList", noticelist);
		}
		
		ArrayList<Board> bestList = new BoardService().selectBestBoard();
		if(bestList.isEmpty()) {
			System.out.println("bestList 잘못됨");
		} else {
			System.out.println("bestList 잘됨");
			request.setAttribute("bestList", bestList);
		}
		
		// (메인)전체게시판
		ArrayList<Board> listAll = new BoardService().selectAllBoard();
		if(listAll.isEmpty()) {
			System.out.println("listAll 잘못됨");
		} else {
			System.out.println("listAll 잘됨");
			request.setAttribute("listAll", listAll);
		}
		
		// (메인)자유게시판
		ArrayList<Board> freeList = new BoardService().selectFreeBoard();
		
		if(freeList.isEmpty()) {
			System.out.println("freeList 잘안됨");
		} else {
			System.out.println("freeList 잘됨");
			request.setAttribute("freeList", freeList);
		}
		
		// (메인 view(메인 게시판들 넘기기))
		request.getRequestDispatcher("views/board/boardMain.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
