package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemService;

/**
 * Servlet implementation class DeleteFollowingController
 */
@WebServlet("/deletefollowing.me")
public class DeleteFollowingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFollowingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 팔로우 취소를 누르면 팔로우 리스트가 사라지는 
		 request.setCharacterEncoding("UTF-8");
			
	     //session영역에 담겨 있는 로그인 정보를 가져옴
		HttpSession session = request.getSession();
			
			
		int memNo =  Integer.parseInt(request.getParameter("memNo"));
		System.out.println(memNo);
		int following = Integer.parseInt(request.getParameter("following"));
		System.out.println(following);
		
	
		
		//request.setAttribute("memNo", memNo);
		//request.setAttribute("productNo", productNo);
		int result = new MemService().deleteFollowing(memNo, following);
		
		if(result >0) {
			
			session.removeAttribute("following");
			request.getRequestDispatcher("following.me?memNo=" + memNo).forward(request, response);
			
		}
		
				

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
