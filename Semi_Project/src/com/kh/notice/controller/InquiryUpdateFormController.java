package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Category;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class InquiryUpdateFormController
 */
@WebServlet("/inquiryUpdateForm.no")
public class InquiryUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카테고리 조회
		ArrayList<Category> list = new NoticeService().selectInquiryCategory(); // 이미 있음
	
		// 글번호 ino
		int inquiryNo = Integer.parseInt(request.getParameter("ino"));
		// Notice, Attachment에서 글번호로 조회
		Notice n = new NoticeService().selectInquiry(inquiryNo);
		Attachment at = new NoticeService().selectInquiryAttachment(inquiryNo);
		request.setAttribute("list", list);
		request.setAttribute("n", n);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/notice/inquiryUpdateView.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
