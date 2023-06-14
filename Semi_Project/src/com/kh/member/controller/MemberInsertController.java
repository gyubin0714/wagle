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
import com.kh.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		 
	    // 프로필 업로드
	    // "첨부파일" => multipart/ form-data => 조건제시 => 서버로 파일을 올려주자
	 		if(ServletFileUpload.isMultipartContent(request)) {
	 		// 잘 전송된 경우
	 			
	 		// 1. MultipartRequest객체 생성
				// 객체 생성 전
				// 1_1. 전송 용량 제한(10Mbyte)
				int maxSize = 1024 * 1024 * 10;
				
				//1_2. 저장할  서버의 물리적 경로 제시
				
				//String savePath = request.getServletContext().getRealPath("/resources/profile_upfiles");
				
				
				HttpSession session = request.getSession();
				ServletContext application = session.getServletContext();
				String savePath = application.getRealPath("/resources/profile_upfiles/");
				
				
				// 2. MultipartRequest객체 생성
				MultipartRequest multiRequest =
				  new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		// 2) request객체로부터 요청 시 전달 값 뽑기
		String memId = multiRequest.getParameter("memId");
		String memPwd = multiRequest.getParameter("memPwd");
		String pwdQ = multiRequest.getParameter("pwdQ");// 각 질문에 대한 번호 지정
		//System.out.println(pwdQ);
	    String pwdA = multiRequest.getParameter("pwdA");
	    //System.out.println(pwdA);
	    String memName = multiRequest.getParameter("memName");
	    //System.out.println(memName);
	    String nickname = multiRequest.getParameter("nickname");
	    String email = multiRequest.getParameter("email");
	    String address = multiRequest.getParameter("address");
	    String[] payAccountArr = multiRequest.getParameterValues("payAccount"); // 은행, 계좌번호 배열
	    
	    
	 
	    
	    
	    // String.join("구분자", 배열명);
	    String payAccount = "";
	    if(payAccountArr != null) {
	    	payAccount = String.join("-", payAccountArr); // [은행-계좌 번호] 의 배열
	    }
	    
	    // 3) Member 객체에 담기(setter메소드이용)
	    Member m = new Member();
	    m.setMemId(memId);
	    m.setMemPwd(memPwd);
	    m.setPwdQ(pwdQ);
	    m.setPwdA(pwdA);
	    m.setMemName(memName);
	    m.setNickname(nickname);
	    m.setEmail(email);
	    m.setAddress(address);
	    m.setPayAccount(String.join("-", payAccount));
	   
			
				

				// Attachment => 메인이미지 required
				// 한개만 올릴수 있음
				
				Attachment at  = null;
				// 키값 :회원 = 1
				
                 // 현재 반복하고 있는 키값으로 파일을 업로드 했는지 파악  => 조건!
				
				if(multiRequest.getOriginalFileName("upfile") !=null) { // 파일이 존재한다.
					
					// 첨부파일이 존재한다면 Attachment객체 생성
					// 필드 ; 원본명, 수정명, 파일경로(메인페이지 -1 /나머지 ,상세 - 2)
					at = new Attachment();
					
					at.setOriginName(multiRequest.getOriginalFileName("upfile")); //원본명
					
					at.setChangeName(multiRequest.getFilesystemName("upfile")); //수정명
					
					at.setFilePath("resources/profile_upfiles"); //파일경로
					}
				
	 		
		// 4) 정보들 넘김 요청 처리
	   
	    if(new MemServiceImpl().insertMember(m, at)> 0) {// 성공 => url재요청방식(sendRedirect)
	    	
			request.getSession().setAttribute("alertMsg", "회원가입에 성공했습니다");
			response.sendRedirect(request.getContextPath() + "/loginForm.me");
			
	    
	    } else { // 실패 => 에러페이지
	    	// 만약 첨부파일 있었는데실패한다면 이미 업로드 된 파일을 굳이 서버에서 보관필요x
	    	
	    	
	    	if(at != null) {
	    		// delete() 호출
	    		new File(savePath + at.getChangeName()).delete();
	    	}
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
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
