package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.board.model.service.BoardService;
import com.kh.member.model.vo.Member;

@WebServlet("/rinsert.bo")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxReplyInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String content = request.getParameter("content");
		System.out.println(bno);
		System.out.println(content);
		
		int result = new BoardService().insertReply(bno, content, memNo);
		
		if(result > 0) {
			System.out.println("댓글 등록 성공");
			
			response.setContentType("application/json; charset=UTF-8");
			
			new Gson().toJson(result, response.getWriter());
		} else {
			System.out.println("댓글 등록 실패");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
