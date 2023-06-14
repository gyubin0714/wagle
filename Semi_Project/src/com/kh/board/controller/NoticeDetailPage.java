package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Notice;
import com.kh.member.model.vo.Member;

@WebServlet("/ndetail.bo")
public class NoticeDetailPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDetailPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nno = request.getParameter("nno");
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null) {
			
			int result = new BoardService().insertNoticeCount(nno);
			
			if(result > 0) {
				System.out.println("조회수 등록 성공!");
			} else {
				System.out.println("조회수 등록 실패~");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
