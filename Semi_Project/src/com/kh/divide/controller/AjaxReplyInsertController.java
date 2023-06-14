package com.kh.divide.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.divide.model.service.DivideService;
import com.kh.divide.model.vo.Divide_Comment;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.di")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				// POST => 인코딩
				request.setCharacterEncoding("UTF-8");
				
				// request로부터 값 뽑기
				int dNo = Integer.parseInt(request.getParameter("dno"));
				String commentContent = request.getParameter("content");
				
				// 로그인한 회원정보
				int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
				
				// VO 또 가공 => Reply
				Divide_Comment d = new Divide_Comment();
				d.setdNo(dNo);
				d.setdContent(commentContent);
				d.setmNo(String.valueOf(memNo));
				
				// Service 요청
				int result = new DivideService().insertReply(d);
				
				// Gson, Json => 넘겨야할 값이 여러개일 때 묶을 때
				// 넘겨야 할 값이 result뿐이므로 그냥 넘김!
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
