package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.member.model.service.MemService;
import com.kh.member.model.vo.Follow;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class MemberFollowingController
 */
@WebServlet("/following.me")
public class MemberFollowingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFollowingListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
       int memNo = Integer.parseInt(request.getParameter("memNo"));
       
		
       ArrayList<Follow> list = new MemService().followList(memNo);
       Attachment at = new MemService().selectAttachment(memNo);
		
		request.setAttribute("list", list);
		request.setAttribute("memNo", memNo);
		request.setAttribute("at", at);
		
		 //응답화면
	
		RequestDispatcher view = request.getRequestDispatcher("/views/member/following.jsp");
		
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
