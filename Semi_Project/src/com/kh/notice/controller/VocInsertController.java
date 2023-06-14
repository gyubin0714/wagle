package com.kh.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class VocInsertController
 */
@WebServlet("/vocInsert.no")
public class VocInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VocInsertController() {
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
		String vocCategory = request.getParameter("vocCategory"); // 회원신고, 시스템신고
		String vocMember = request.getParameter("vocMember"); // 신고할 회원
		String content = request.getParameter("content"); // 내용
		String title = request.getParameter("title"); // 제목
		String memNo = request.getParameter("memNo"); // 작성자
		
		//System.out.println(vocCategory + vocMember+ content+ memNo);
		
		// 가공
		Notice n = new Notice();
		n.setVocCategory(vocCategory);
		n.setVocMember(vocMember);
		n.setContent(content);
		n.setWriter(memNo);
		n.setTitle(title);
		
		// 서비스로 INSERT
		int result = new NoticeService().insertVoc(n);
		
		// 응답
		if(result > 0) {
			// 성공하면 문의내역창으로 이동
			response.sendRedirect(request.getContextPath() + "/noticeList.no");
			
		} else {
			// 실패
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
