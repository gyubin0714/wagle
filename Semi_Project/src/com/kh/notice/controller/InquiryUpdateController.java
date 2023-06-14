package com.kh.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class InquiryUpdateController
 */
@WebServlet("/inquiryUpdate.no")
public class InquiryUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post
		request.setCharacterEncoding("UTF-8");
		
		// multipart/form-date로 전송이 잘 됐을 때, 내용이 수정되도록
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 10;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/inquiry_upfiles");
			
			// MultipartRequest객체 생성
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			// 값 뽑기
			String csCategoryName = multiRequest.getParameter("csCategoryName");
			String[] answerNotice = multiRequest.getParameterValues("answerNotice");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			int inquiryNo = Integer.parseInt(multiRequest.getParameter("ino"));
			
			// vo객체 가공
			Notice n = new Notice();
			n.setCsCategoryName(csCategoryName);
			n.setAnswerNotice(String.join(",", answerNotice));
			n.setTitle(title);
			n.setContent(content);
			n.setInquiryNo(inquiryNo);
					
			// Attachment 객체
			// 실제 첨부파일이 있으면 객체 생성, 없으면 null값
			
			Attachment at = null;
			
			// 새로운 첨부파일 추가
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				
				
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				at.setFilePath("resources/inquiry_upfiles");
				
				// 첨부파일이 기존에 존재할 경우 -> 원본파일번호 필요
				if(multiRequest.getParameter("originFileNo") != null) {
					
					// 기존파일이 가지고 있던 FileNo(원본파일번호) -> hidden으로 넘겨줌
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					// 기존에 존재하던 첨부파일 삭제 -> hidden으로 넘겨줌
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
					
				} else {
					
					// 기존 첨부파일 존재x, 새로운 첨부파일 INSERT
					// inquiryNo(REF_BNO)					
					at.setRefBno(inquiryNo);
				}
			}
			
			// 서비스 요청
			
			int result = new NoticeService().updateInquiry(n, at);
			
			if(result > 0) { // 성공 -> 상세보기 페이지
				
				request.getSession().setAttribute("alertMsg", "수정이 완료되었습니다.");
				response.sendRedirect(request.getContextPath() + "/inquiryDetail.no?ino=" + inquiryNo);
				
			} else { // 실패
				
				request.setAttribute("errorMsg", "수정 실패");
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
