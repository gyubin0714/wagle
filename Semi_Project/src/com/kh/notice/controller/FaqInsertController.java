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
 * Servlet implementation class FaqInsertController
 */
@WebServlet("/faqInsert.no")
public class FaqInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기
		String csCategoryName = request.getParameter("csCategoryName");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 가공
		Notice n = new Notice();
		n.setCsCategoryName(csCategoryName);
		n.setTitle(title);
		n.setContent(content);
		
		// Service
		int result = new NoticeService().insertFaq(n);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/faqListView.no");
		} else {
			
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
