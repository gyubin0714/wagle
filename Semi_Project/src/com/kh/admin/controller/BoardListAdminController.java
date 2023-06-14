package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.service.NoticeService;

/**
 * Servlet implementation class BoardListAdminController
 */
@WebServlet("/BoardList.ad")
public class BoardListAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩x
		// 페이징해보까. ..
//		String searchField = request.getParameter("searchField");
//		String searchText = request.getParameter("searchText");
//		
//		// listCount : 총 게시글 수
//		int listCount = 0;
//		
//		listCount = new NoticeService().selectBoardListCount(searchField,searchText);
//		
//		System.out.println(listCount);
		
		// countList =>
		
		// searchField => boardNo, mem_Id, mem_Name
		// searchText
		
		// 게시판 조회 List
		ArrayList<Board> list = new AdminService().selectBoardAdmin();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/admin/boardListAdmin.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
