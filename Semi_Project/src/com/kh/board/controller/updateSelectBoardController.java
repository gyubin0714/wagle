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

@WebServlet("/updateSelectBoard.bo")
public class updateSelectBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public updateSelectBoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bno = request.getParameter("bno");
		
		ArrayList<Board> list = new BoardService().updateSelect(bno);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/updateBoardPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
