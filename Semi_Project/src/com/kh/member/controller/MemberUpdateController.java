package com.kh.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
import com.kh.member.model.service.MemService;
import com.kh.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class memberUpdateController
 */
@WebServlet("/memberUpdate.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1)POST방식 => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
	    int maxSize = 1024 * 1024 * 10;	
	    
	    HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		String savePath = application.getRealPath("/resources/profile_upfiles/");
		
		MultipartRequest multiRequest =
				  new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		
		// 2)request로 부터 요청 시 전달한 값을 뽑기
		int memNo = Integer.parseInt(multiRequest.getParameter("memNo"));
		String pwdQ = multiRequest.getParameter("pwdQ");
		String pwdA = multiRequest.getParameter("pwdA");
		String address = multiRequest.getParameter("address");
		String[] payAccountArr = multiRequest.getParameterValues("payAccount");
		String memName = multiRequest.getParameter("memName");
		String nickname = multiRequest.getParameter("nickname");
		String memId = multiRequest.getParameter("memId");
		String memPwd = multiRequest.getParameter("memPwd");
		String email = multiRequest.getParameter("email");
		
		String payAccount = "";
		if(payAccountArr != null) {
			payAccount = String.join("-", payAccountArr);
		}
		
	// VO에 담기(데이터 가공)
		Member m = new Member();
		m.setMemId(memId);
		m.setMemPwd(memPwd);
		m.setPwdQ(pwdQ);
		m.setPwdA(pwdA);
		m.setAddress(address);
		m.setEmail(email);
		m.setPayAccount(payAccount);
		m.setNickname(nickname);
		m.setMemName(memName);
		m.setMemNo(memNo);
		
		
		Attachment at  = null;
		
		if(multiRequest.getOriginalFileName("reUpfile") !=null) { 
			
			at = new Attachment();
			
			at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));//원본명
			
			at.setChangeName(multiRequest.getFilesystemName("reUpfile")); //수정명
			
			at.setFilePath("resources/profile_upfiles"); //파일경로
		}
		
		
	
		
		
		// 첨부파일이 있을 경 우  + 원본파일이 있을 경우 => 원본파일번호가 필요함
		if(multiRequest.getParameter("originFileNo")!= null) {
			// 기존파일이 존재했다
			// 기존파일이 가지고 있던 FileNo 를 at에 담을것
			at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
			
			// 기존에 존재하던 첨부파일 삭제
			new File(savePath + multiRequest.getParameter("originFileName")).delete();
		}else {
			// 새로운 첨부파일은 있는데 기존파일은 없을 경우  =>INSERT 
			// + 어떤 게시글의 첨부파일인지  memNo(REF_BNO)
			at.setRefBno(memNo);
		}
		
		// 4) 서비스 요청
		
		int result = new MemService().updateMember(m, at);
		
		// 5) 결과에 따른 응답 뷰 지정
            if(result > 0) { //성공 => 상세보기 페이지로 이동
            	
			request.getSession().setAttribute("alertMsg", "회원정보 수정 성공");
			response.sendRedirect(request.getContextPath() + "/myPage.me");
							
			}else { // 실패 => 에러페이지
			request.setAttribute("errorMsg", "회원정보 수정 실패");
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
