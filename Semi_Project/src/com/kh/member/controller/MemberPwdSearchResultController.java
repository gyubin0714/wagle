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
 * Servlet implementation class MemberPwdSearchResultController
 */
@WebServlet("/pwdSearchResult.me")
public class MemberPwdSearchResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdSearchResultController() {
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
				
				String memId = request.getParameter("memId");
				String pwdQ = request.getParameter("pwdQ");
				String pwdA = request.getParameter("pwdA");
				
				Member pwdSearch = new MemService().pwdSearch(memId, pwdQ, pwdA);
				
				if(pwdSearch == null) { // 로그인 실패
					request.setAttribute("errorMsg", "존재하지 않는 회원입니다");
					
					RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
					
					view.forward(request, response);
					
				}else {// 회원 찾기 성공
					// 사용자의 정보 넘기기
					
					//HttpSession session = request.getSession();
					//session의 attribute영역에 정보 담기
					HttpSession session = request.getSession();
					session.setAttribute("pwdSearch", pwdSearch);
					
					//아이디 찾기 결과 페이지로 이동
					
					//RequestDispatcher view = request.getRequestDispatcher("views/member/idResult.jsp");
					response.sendRedirect("views/member/pwdResult.jsp");
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
