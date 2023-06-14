package com.kh.divide.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.divide.model.service.DivideService;
import com.kh.divide.model.vo.Divide;

/**
 * Servlet implementation class DivideListController
 */
@WebServlet("/list.di")
public class DivideListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivideListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int listCount; // 현재 일반게시판의 게시글 총 개수 =>
		int currentPage; // 현재 페이지(사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 최대개수 => 10개로 고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대개수 => 10개로 고정
		
		int maxPage; // 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
	
		// * listCount : 총 게시글의 수
		listCount = new DivideService().selectListCount(); //
		
		// * currentPage : 현재 페이지(==사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("page")); // 1
		
		// * pageList : 페이징바 최대 개수
		pageLimit = 10;
		
		// * boardLimit : 한페이지에 보여질 게시글의 최대 개수
		boardLimit = 8;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		

		
		endPage = startPage + pageLimit -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 3) VO로 가공
		PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
	
		
		// 4) Service
		ArrayList<Divide> list = new DivideService().selectList(pi);
		
		// 5) 응답화면 지정
		request.setAttribute("list", list); // 우리가 실제로 조회한 페이지에 보여질 10개의 게시글
		request.setAttribute("pi", pi); // 페이징바를 만들 때 필요한 객체
		
		// views/board/boardListView.jsp로 응답=> 포워딩
		request.getRequestDispatcher("views/divide/boardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
