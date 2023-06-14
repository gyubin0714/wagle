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
 * Servlet implementation class FaqUpdateFormController
 */
@WebServlet("/faqUpdateForm.no")
public class FaqUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카테고리 조회
		ArrayList<Category> list = new NoticeService().selectInquiryCategory(); // 이미 있음
	
		// 글번호 fno
		int faqNo = Integer.parseInt(request.getParameter("fno"));
		
		Notice n = new NoticeService().selectFaq(faqNo);
		
		request.setAttribute("list", list);
		request.setAttribute("n", n);
		
		request.getRequestDispatcher("views/notice/faqUpdateView.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
