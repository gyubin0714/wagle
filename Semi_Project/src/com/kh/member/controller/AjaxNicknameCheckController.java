package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemService;

/**
 * Servlet implementation class AjaxNicknameCheckController
 */
@WebServlet("/nicknameCheck.me")
public class AjaxNicknameCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxNicknameCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//  GET
		
			//  request로부터 값 뽑기
			String checkNickname = request.getParameter("checkNickname");
			
			//  Service요청
			int count = new MemService().nicknameCheck("checkNickname");
			
			//  결과에 따른 응답 화면이 새로고침
			response.setContentType("text/html; charset=UTF-8");
			
			
			// 중복값이 있을 때 "NNNNN" count == 1
		    // 중복값이 없을 때 "NNNNY" count == 0
			if(count > 0) {
				response.getWriter().print("NNNNN");
			}else {
				response.getWriter().print("NNNNY");
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
