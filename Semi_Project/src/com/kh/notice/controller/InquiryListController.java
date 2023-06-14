package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class InqueryListController
 */
@WebServlet("/inquiryList.no")
public class InquiryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 x
		
		// 값 뽑기
		// 가공 x
		String memNo = request.getParameter("memNo");
		
		// -- 페이징
		// listCount : 총 게시글 수 DELETE_YN = 'N'을 COUNT(*)
		
		int listCount = 0;
		
		
		if(memNo.equals("1")) {
			// 관리자 로그인
			listCount = new NoticeService().selectListCountAdmin();
		} else {
			// 회원 로그인
			listCount = new NoticeService().selectListCount(memNo);
		}
		// currentPage : 현재 페이지
		int currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		 // pageLimit : 페이징바  개수 -  5개씩
		int pageLimit = 5;
		
		// boardLimit : 한 페이지에 보여질 게시글 수 - 10개씩
		int boardLimit = 10;
		
		// maxPage : 총 페이지 개수 (가장 마지막 페이지는?)
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// startPage : 페이지 하단 페이징바의 시작 수
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// endPage : 페이지 하단 페이징바의 끝 수
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 7개의 변수 vo로 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Notice> list= null;
		if(memNo.equals("1")) {
			// 관리자 로그인 시, 전체 조회
			list = new NoticeService().selectInquiryListAdmin(pi);
		} else {
			// 회원 로그인시, 자신이 작성한 1:1내역만 조회
			list = new NoticeService().selectInquiryList(pi, memNo);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("memNo", memNo);
		
		// 응답화면
		RequestDispatcher view = request.getRequestDispatcher("/views/notice/inquiryListView.jsp");
		
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
