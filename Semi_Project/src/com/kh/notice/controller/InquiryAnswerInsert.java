package com.kh.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class InquiryAnswerInsert
 */
@WebServlet("/answerInsert.no")
public class InquiryAnswerInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryAnswerInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		// 값뽑기
		int inquiryNo = Integer.parseInt(request.getParameter("ino"));
		String answer = request.getParameter("answer");
		
		// 객체가공
		Notice n = new Notice();
		n.setInquiryNo(inquiryNo);
		n.setAnswer(answer);
		
		int result = new NoticeService().insertInquiryAnswer(n);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "답변 작성이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/inquiryDetail.no?ino=" + inquiryNo);
		} else {
			// 실패
						request.setAttribute("errorMsg", "답변 작성 실패");
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
