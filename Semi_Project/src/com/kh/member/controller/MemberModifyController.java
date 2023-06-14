package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.member.model.service.MemService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberModifyController
 */
@WebServlet("/memberModify.me")
public class MemberModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 수정 페이지 폼 띄워주기
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		System.out.println(memNo);
		
	    
		// 회원 번호 해당하는 member테이블의 행 조회
		
		Member m = new MemService().selectMember(memNo);
		System.out.println(m);
		
	
		request.setAttribute("memNo", memNo);
		request.setAttribute("m", m);
		
	    RequestDispatcher view = request.getRequestDispatcher("views/member/memberModifyForm.jsp");
		view.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
