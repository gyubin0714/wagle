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
 * Servlet implementation class InquiryInsertController
 */
@WebServlet("/inquiryInsert.no")
public class InquiryInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// post 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// multi/form-date방식으로 전송 + 첨부파일
		
		if(ServletFileUpload.isMultipartContent(request)) {
			// 잘 전송된 경우
			
			int maxSize = 1024 * 1024 * 10;
			
			// 첨부파일을 저장할 경로 /resources/inquiry_upfiles/
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/inquiry_upfiles/");
			
			// MultipartRequest객체 생성 
			MultipartRequest multiRequest =
					new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 값 뽑기
			String csCategoryName= multiRequest.getParameter("csCategoryName");
			String[] answerNotice = multiRequest.getParameterValues("answerNotice");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String memNo = multiRequest.getParameter("memNo");
			
			// VO객체 가공
			// BOARD INSERT
			Notice n = new Notice();
			n.setCsCategoryName(csCategoryName);
			n.setAnswerNotice(String.join(",", answerNotice));
			n.setTitle(title);
			n.setContent(content);
			n.setWriter(memNo);
			
			// ATTACHMENT INSERT
			Attachment at = null;
			// 첨부파일 존재하지  않으면 null
			
			// 첨부파일이 존재하면 "파일명"
			if(multiRequest.getOriginalFileName("upfile") != null) {
				// 첨부파일이 있다면
				at = new Attachment();
				
				// 파일명
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				
				// 수정된 파일명
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				
				// 파일 경로
				at.setFilePath("resources/inquiry_upfiles");
			}
			// Service -> INSERT
			int result = new NoticeService().insertInquiry(n, at);
			
			// 응답
			if(result > 0) {
				// 성공하면 문의내역창으로 이동
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/inquiryList.no?cpage=1&memNo=" + memNo);
			} else {
				// 실패
				// Attachement에 파일이 있다면 delete()
				if(at != null) {
					new File(savePath + at.getChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "문의 작성 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
 				
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
