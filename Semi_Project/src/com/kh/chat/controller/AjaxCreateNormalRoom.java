package com.kh.chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.chat.model.sevice.ChatService;
import com.kh.chat.model.vo.Chat;

/**
 * Servlet implementation class AjaxCreateNormalRoom
 */
@WebServlet("/createNormalRoom.ch")
public class AjaxCreateNormalRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxCreateNormalRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int myMemNo = Integer.parseInt(request.getParameter("myMemNo"));
        int yourMemNo = Integer.parseInt(request.getParameter("yourMemNo"));
        
        Chat c = new Chat();
        c.setMemNo(myMemNo);
        c.setChatType("N");
        // 기능 1.	회원번호를 조회해서 같은 값이 있는지 확인 후 채팅방이 비활성화 상태라면 다시 활성화시킴
        // 일반 채팅의 경우 채팅 타입을 넘겨 같은 유저의 채팅방 존재 여부 확인
        Chat nc = new ChatService().checkRoomState(c, yourMemNo);

        String str = "";
        if(nc == null) { //--없다면 (결과가 NULL이면)
        	int result = new ChatService().createNormalRoom(c, yourMemNo);
        	if(result > 0) {
        		str = "채팅 생성에 성공하였습니다.";
        	}
        } else {
	        if(nc.getCount() == 2) { //--이미 상대방과 채팅방이 있을경우( 결과가 2라면 )
	        	if(nc.getRoomState().equals("Y")) { // -> 채팅방이 'Y' 라면
	        		//기존의 채팅방 열어주기 
	        		str = "해당 유저와의 채팅이 존재합니다.";
	        	} else { // -> 채팅방이 'N'이라면 
	        		//'Y'로 바꾸기
	        		String roomState = "Y";
	        		int result = new ChatService().updateRoom(nc.getRoomNo(), roomState);
	        		if(result > 0) {
	        			str = "해당 유저와의 채팅을 복구하였습니다.";
	        		}
	        	}
	        }
        }
        
        response.setContentType("html/text; charset=UTF-8");
        response.getWriter().print(str);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
