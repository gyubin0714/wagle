package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.board.model.service.BoardService;

@WebServlet("/rdelete.bo")
public class ReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bno = request.getParameter("bno");
		String cno = request.getParameter("cno");
		
		int result = new BoardService().replyDelete(bno, cno);
		
		if(result > 0) {
			System.out.println("댓글 삭제 성공");
			
			response.setContentType("application/json; charset=UTF-8");
			
			new Gson().toJson(result, response.getWriter());
			
		} else {
			System.out.println("댓글 삭제 실패");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
