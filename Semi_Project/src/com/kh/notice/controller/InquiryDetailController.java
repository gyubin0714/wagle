package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class InquiryDetailController
 */
@WebServlet("/inquiryDetail.no")
public class InquiryDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		int inquiryNo = Integer.parseInt(request.getParameter("ino"));
		
		// 문의내역 select
		Notice n = new NoticeService().selectInquiry(inquiryNo);
		// 첨부파일 select
		Attachment at = new NoticeService().selectInquiryAttachment(inquiryNo);
		
		request.setAttribute("n", n);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/notice/inquiryDetailView.jsp").forward(request, response);
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
