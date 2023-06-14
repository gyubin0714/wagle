package com.kh.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.chat.model.sevice.ChatService;
import com.kh.chat.model.vo.Chat;

/**
 * Servlet implementation class ChatListController
 */
@WebServlet("/list.ch")
public class AjaxChatListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxChatListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인한 유저의 memNo
		int userNo = Integer.parseInt(request.getParameter("memNo"));
		
		ArrayList<Chat> myRoomList = new ChatService().chatselectMyList(userNo);
		// 방번호 리스트, 채팅타입, 채팅상태, 상품번호(타입이 'P'일시) , 상대방 번호, 닉네임
		// 최근채팅 번호, 최근채팅내용, 최근채팅시간, 방번호, 알람여부
		
		response.setContentType("application/json; charset=UTF-8");
		
		new Gson().toJson(myRoomList,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
