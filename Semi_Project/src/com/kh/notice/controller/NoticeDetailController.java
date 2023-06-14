package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/noticeDetail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		
		// 조회수 count -> service
		int result = new NoticeService().increaseCount(noticeNo);
		
		if(result > 0) {
			// count 업데이트 성공 하면
			// notice 조회
			Notice n = new NoticeService().selectNotice(noticeNo);
			request.setAttribute("n", n);
			request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		} else { // 실패 => 에러페이지
			request.setAttribute("errorMsg", "공지사항 상세 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
