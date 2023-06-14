package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;

@WebServlet("/insertReplyAndReply.bo")
public class AjaxReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxReplyUpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String replyText = request.getParameter("replyText");
		String commentNo = request.getParameter("commentNo");
		System.out.println(commentNo);
		System.out.println(replyText);
		
		Reply r = new Reply();
		r.setContent(replyText);
		r.setCommentNo(Integer.parseInt(commentNo));
		
		int result = new BoardService().updateReply(r);
		
		if(result > 0) {
			System.out.println("댓글 수정 성공");
			
			response.setContentType("html/text; charset=UTF-8");
			
			response.getWriter().print(result);
			
		} else {
			System.out.println("댓글 수정 실패");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
