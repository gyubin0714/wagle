package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class SearchMemberController
 */
@WebServlet("/searchMember.me")
public class SearchMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
        String nickname = request.getParameter("nickname");
        System.out.println(nickname);
        
       Member memberSearch = new MemService().memberSearch(nickname);
        //System.out.println(memberSearch);
        
        //request.setAttribute("nickname", nickname);
        
        if(memberSearch != null) {
        	
        	
        	
        	HttpSession session = request.getSession();
        
			session.setAttribute("memberSearch", memberSearch);
			
			// following 페이지로 이동
			response.sendRedirect("views/member/following.jsp");
        	
        	
        	
        }else {// 회원이 존재하지 않는다면
        	request.setAttribute("alertMsg", "존재하지 않는 회원입니다");
        	
        	RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);
			
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
