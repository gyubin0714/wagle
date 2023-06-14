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
import com.kh.common.model.vo.Attachment;
import com.kh.member.model.vo.Member;

@WebServlet("/detail.bo")
public class BoardDetailPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bno = request.getParameter("bno");
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null) {
			
			int result = new BoardService().insertCount(bno);
			
			if(result > 0) {
				System.out.println("조회수 등록 성공!");
			} else {
				System.out.println("조회수 등록 실패~");
			}
		}
		
		ArrayList<Board> listDetail = new BoardService().selectContentBoard(bno);
		Attachment at = new BoardService().selectAttachment(bno);
		
		if(listDetail.isEmpty()) {
			System.out.println("listDetail 잘안됨");
		} else {
			System.out.println("listDetail 잘됨");
			request.setAttribute("listDetail", listDetail);
			request.setAttribute("at", at);
		}
		request.getRequestDispatcher("views/board/boardDetailPage.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
