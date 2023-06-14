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
 * Servlet implementation class FaqUpdateController
 */
@WebServlet("/faqUpdate.no")
public class FaqUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 값 넘겨받기
		int faqNo = Integer.parseInt(request.getParameter("fno"));
		
		String csCategoryName = request.getParameter("csCategoryName");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice n = new Notice();
		n.setCsCategoryName(csCategoryName);
		n.setTitle(title);
		n.setContent(content);
		n.setFaqNo(faqNo);
		
		int result = new NoticeService().updateFaq(n);
		
		if(result > 0) { // 성공 
			
			response.sendRedirect(request.getContextPath() + "/faqListView.no");
			
		} else { // 실패
			
			request.setAttribute("errorMsg", "수정 실패");
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
