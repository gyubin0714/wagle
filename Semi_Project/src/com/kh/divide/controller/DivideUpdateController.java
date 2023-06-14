package com.kh.divide.controller;

import java.io.File;




import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.divide.model.service.DivideService;
import com.kh.divide.model.vo.Divide;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.di")
public class DivideUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivideUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기전 => 파일이 전송될것인가 파악
		if(ServletFileUpload.isMultipartContent(request)) {
			
			
			// 1. 전송파일 용량제한 int maxSize => 10Mbyte
			int maxSize = 1024 * 1024 *10;
			// 2. 전달된 파일을 저장시킬 물리적인 경로를 알아내기 String savePath
			String savePath = request.getSession().getServletContext().getRealPath("resources/divide_upfiles");
			
			// 전달된 파일명 수정 후 서버에 업로드
			// MultipartRequest 객체를 생성함으로 서버에 파일이 업로드
			MultipartRequest multiRequest
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// UPDATEBOARD
			// 2) 값뽑기 request => multiRequest
			String title = multiRequest.getParameter("title");
			String field = multiRequest.getParameter("field");
			String category = multiRequest.getParameter("category");
			String content = multiRequest.getParameter("content");
			int dno = Integer.parseInt(multiRequest.getParameter("dno"));
			
			// 3) VO 가공 - BOARD관련
			Divide d = new Divide();
			d.setTitle(title);
			d.setField(field);
			d.setCategory(category);
			d.setContent(content);
			d.setdNo(dno);
			
			ArrayList<Attachment> list = new ArrayList();
			
			Attachment at = null;

			
			for(int i = 0; i < 5; i++) {
				
				// 키값만 미리 변수!!
				String key = "refile" + i;
				String originFileNo= "originFileNo" + i;
				String originFileName = "originFileName" + i;
				
				// 현재 반복하고 있는 키값으로 파일을 업로드했는지 파악 => 조건!
				if(multiRequest.getOriginalFileName(key) != null) { // 파일이 존재한다
					
					// 첨부파일이 존재한다면 Attachment객체 생성
					// 필드 : 원본명, 수정명, 파일경로**(1: 대표, 2: 상세)
					at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key)); // 원본명
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/divide_upfiles");
					at.setRefBno(dno);
					
					// 새로운 첨부파일 존재 + 원본파일 있을경우
					if(multiRequest.getParameter(originFileNo) != null) {
						at.setFileNo(Integer.parseInt(multiRequest.getParameter(originFileNo)));
						new File(savePath + multiRequest.getParameter(originFileName)).delete();
					} 
					
					// 파일레벨
					if(i == 0) {
						// 대표이미지
						at.setFileLevel(1);
					} else {
						at.setFileLevel(2);
					}
					list.add(at);
					
				}
				
			}
			
			// 4) 서비스요청
			int result = new DivideService().updateDivideBoard(d,list);
			
			// 5) 결과에 따른 응답 뷰 지정
			if(result > 0) { 
				response.sendRedirect(request.getContextPath()+"/detail.di?dno="+dno);
			} else { // 실패
				// 만약 이미지가 있었는데 실패했다면 이미 업로드한 파일을 굳이 서버에서 보관할 필요 X
				
				if(at != null) {
					// delete() 호출
					new File(savePath + at.getChangeName()).delete();
				}
				
				
				request.setAttribute("errorMsg", "게시글 작성 실패");
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
