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
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST 방식 -> 인코딩
		request.setCharacterEncoding("UTF-8");
		
		
		// 요청 시 전달값 꺼내서 변수에 기록
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		
		Member loginUser = new MemService().loginMember(memId, memPwd);
		
		if(loginUser == null) {  // 로그인 실패
			request.setAttribute("errorMsg", "로그인에 실패하였습니다. 다시 시도해주세요");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
		
		view.forward(request, response);
		
		
		}else { // 로그인성공 => indext.jsp 데이터 응답
			
			// 사용자의 정보 넘기기
			// 로그인 한 회원의 정보를 로그아웃전까지 계속 가져다 쓸것이기 때문에 session에 담기
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg",  memId + "님 환영합니다!");
			
			// url재요청 방식(sendRedirect방식) :Client에게 url을 재요청하게 함으로써 응답페이지를 받게함
			// response.sendRedirect("/재요청경로");
			// localhost:8001/wagle
			
			
			response.sendRedirect("/wagle");
			
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
