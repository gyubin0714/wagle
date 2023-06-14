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
 * Servlet implementation class MemberIdSearchFormController
 */
@WebServlet("/idResult.me")
public class MemberIdSearchResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIdSearchResultController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST
		
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		String memName = request.getParameter("memName");
		String email = request.getParameter("email");
		
		Member idSearch = new MemService().idSearch(memName, email);
		
	     //  결과에 따른 응답 화면이 새로고침
	     response.setContentType("text/html; charset=UTF-8");
		
		if(idSearch == null) { // 로그인 실패
		
			
			
			request.setAttribute("errorMsg", "존재하지 않는 회원입니다");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);
			
			
		}else {// 회원 찾기 성공
			// 사용자의 정보 넘기기
			
			//HttpSession session = request.getSession();
			//session의 attribute영역에 정보 담기
			HttpSession session = request.getSession();
			session.setAttribute("idSearch", idSearch);
			
			//아이디 찾기 결과 페이지로 이동
			
			//RequestDispatcher view = request.getRequestDispatcher("views/member/idResult.jsp");
			response.sendRedirect("views/member/idResult.jsp");
			
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
