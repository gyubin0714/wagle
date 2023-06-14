

package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Category;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateFormController
 */
@WebServlet("/noticeUpdateForm.no")
public class NoticeUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// category 조회
		ArrayList<Category> list = new NoticeService().selectNoticeCategory();
		
		// noticeNo 값 받기
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		
		// service
		Notice n = new NoticeService().selectNotice(noticeNo);
		
		// setAttribute 값 넘겨주기
		request.setAttribute("n", n);
		request.setAttribute("list", list);
		
		// 응답화면
		request.getRequestDispatcher("views/notice/noticeUpdateView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
