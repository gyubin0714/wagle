package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemService;
import com.kh.notice.model.service.NoticeService;



/**
 * Servlet implementation class MyInterestDeleteController
 */
@WebServlet("/interestDelete.me")
public class MyInterestDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInterestDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
       
		   request.setCharacterEncoding("UTF-8");
			
	        //session영역에 담겨 있는 로그인 정보를 가져옴
			HttpSession session = request.getSession();
			
			
		int memNo =  Integer.parseInt(request.getParameter("memNo"));
		
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		
		//request.setAttribute("memNo", memNo);
		//request.setAttribute("productNo", productNo);
		int result = new MemService().deleteInterest(memNo, productNo);
		
		if(result >0) {
			
			session.removeAttribute("productNo");
			request.getRequestDispatcher("interest.me?memNo=" + memNo).forward(request, response);
			
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
