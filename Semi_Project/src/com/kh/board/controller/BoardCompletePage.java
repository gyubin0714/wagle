package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/complete.bo")
public class BoardCompletePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public BoardCompletePage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/board_upfiles/");
			
			MultipartRequest multiRequest = 
					new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			Member loginUser = (Member)session.getAttribute("loginUser");
			
			int memNo = loginUser.getMemNo();
			
			String Cno = multiRequest.getParameter("Cno");
			String title = multiRequest.getParameter("title");
			String boardText = multiRequest.getParameter("boardText");
			Board b = new Board();
			b.setCategoryNo(Cno);
			b.setBoardTitle(title);
			b.setBoardWriting(boardText);
			b.setMemberNo(String.valueOf(memNo));
			
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				at = new Attachment();
				
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				
				at.setFilePath("resources/board_upfiles");
			}
			
			int result = new BoardService().insertBoard(b, at);
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath()+"/board.bo");
			} else {
				System.out.println("게시글 등록 실패!");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
