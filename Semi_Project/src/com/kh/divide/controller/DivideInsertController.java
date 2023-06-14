package com.kh.divide.controller;

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
 * Servlet implementation class DivideInsertController
 */
@WebServlet("/insert.di")
public class DivideInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivideInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			
			int maxSize = 1024 * 1024 * 10;
			
			
			String savePath = request.getServletContext().getRealPath("resources/divide_upfiles/");

						MultipartRequest multiRequest = 
						new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
						
						String title = multiRequest.getParameter("title");
						String field = multiRequest.getParameter("field");
						String category = multiRequest.getParameter("category");
						String divideYN = multiRequest.getParameter("divideYN");
						String content = multiRequest.getParameter("content");
						String memNo = multiRequest.getParameter("memNo");
						
						
						// 3) VO로 가공
						Divide d = new Divide();
						d.setTitle(title);
						d.setField(field);
						d.setCategory(category);
						d.setDivide_YN(divideYN);
						d.setContent(content);
						d.setMem_No(memNo);
						
						// Attachment => 사진게시판 작성폼에 메인이미지 required
						// => 적어도 최소한 게시글 한 개당 한 개의 첨부파일은 존재한다.
						// 여러개의 VO를 묶어서 다를 경우 ArrayList를 쓰면 편하지 않을까??
						ArrayList<Attachment> list = new ArrayList();
						
						
						for(int i = 1; i <= 5; i++) {
							
							// 키값만 미리 변수!!
							String key = "file" + i;
							
							// 현재 반복하고 있는 키값으로 파일을 업로드했는지 파악 => 조건!
							if(multiRequest.getOriginalFileName(key) != null) { // 파일이 존재한다
								
								// 첨부파일이 존재한다면 Attachment객체 생성
								// 필드 : 원본명, 수정명, 파일경로**(1: 대표, 2: 상세)
								Attachment at = new Attachment();
								at.setOriginName(multiRequest.getOriginalFileName(key)); // 원본명
								at.setChangeName(multiRequest.getFilesystemName(key));
								at.setFilePath("resources/divide_upfiles");
								
								// 파일레벨
								if(i == 1) {
									// 대표이미지
									at.setFileLevel(1);
								} else {
									at.setFileLevel(2);
								}
								
								list.add(at);
								
							}
							
						}
						
						// 4) 서비스요청
						int result = new DivideService().insertDivideBoard(d,list);
						
						// 5) 결과에 따른 응답 뷰 지정
						if(result > 0) { 
							request.getSession().setAttribute("alertMsg", "게시글 작성 성공!");
							response.sendRedirect(request.getContextPath()+"/list.di?page=1");
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
