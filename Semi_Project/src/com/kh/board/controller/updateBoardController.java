package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

@WebServlet("/updateBoard.bo")
public class updateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public updateBoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String bno = request.getParameter("bno");
		String Cno = request.getParameter("Cno");
		String title = request.getParameter("title");
		String boardText = request.getParameter("boardText");
		
		Board b = new Board();
		
		b.setBoardNo(Integer.parseInt(bno));
		b.setCategoryNo(Cno);
		b.setBoardTitle(title);
		b.setBoardWriting(boardText);
		
		
		
		int result = new BoardService().updateBoard(b);
		
		if(result > 0) {
			System.out.println("update 잘됨");
			response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+bno);
		} else {
			System.out.println("update 실패");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
