package com.kh.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.chat.model.sevice.ChatService;

/**
 * Servlet implementation class AjaxChatListUpdateController
 */
@WebServlet("/updateRoom.ch")
public class AjaxChatRoomUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxChatRoomUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
		String roomState = "N";
		
		int result = new ChatService().updateRoom(roomNo,roomState);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
