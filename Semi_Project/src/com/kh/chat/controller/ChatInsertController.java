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
 * Servlet implementation class ChatInsertController
 */
@WebServlet("/insertChat.ch")
public class ChatInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String content = request.getParameter("content");
		
		content = content.replace("<","&lt;");
		
		Chat c = new Chat();
		c.setRoomNo(roomNo);
		c.setMemNo(memNo);
		c.setChatContent(content);
		
		int result = new ChatService().insertChat(c);
		
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
