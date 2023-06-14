package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Board;
import com.kh.member.model.service.MemService;

/**
 * Servlet implementation class MyBoardController
 */
@WebServlet("/myBoard.me")
public class MyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내  게시물 페이지 띄워주기
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		ArrayList<Board>list = new MemService().selectMyBoard(memNo);
		
		request.setAttribute("list", list);
		request.setAttribute("memNo", memNo);
	
		
		// 1. RequestDispatcher 객체를 이용하는 방법(forwarding)
		RequestDispatcher view = request.getRequestDispatcher("views/member/myBoard.jsp");
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
