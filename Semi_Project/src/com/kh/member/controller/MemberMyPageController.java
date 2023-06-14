package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemService;
import com.kh.product.model.vo.Product;

/**
 * Servlet implementation class MemberMyPageController
 */
@WebServlet("/myPage.me")
public class MemberMyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberMyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 로그인 성공 / 실패 여부에 따라 마이페이지 데이터 상태가 달라짐
		
		// 접속자의 정보 => session
		// 로그인 전 : loginUser 키값에 해당하는 밸류가 null값
		// 로그인 후 : loginUser 키값에 해당 하는 밸류가 Member주소값 => 포워딩
		
		HttpSession session = request.getSession();
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		ArrayList<Product> list = new MemService().selectMyEnrollProduct(memNo);
		
		request.setAttribute("list", list);
		request.setAttribute("memNo", memNo);
		
		
		if(session.getAttribute("loginUser") == null) { // 로그인이 안된 상태 
			
	    	session.setAttribute("alertMsg", "로그인해주십시오");
		    response.sendRedirect(request.getContextPath());
		    
		}else { // 로그인 성공 상태
			RequestDispatcher view = request.getRequestDispatcher("views/member/myPage.jsp");
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
