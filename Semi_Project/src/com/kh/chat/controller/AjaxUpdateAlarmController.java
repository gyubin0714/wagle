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
 * Servlet implementation class AjaxUpdateAlarmController
 */
@WebServlet("/updateAlarm.ch")
public class AjaxUpdateAlarmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUpdateAlarmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int roomNo = Integer.parseInt(request.getParameter("roomNo"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String alarm = request.getParameter("alarm");
		
		Chat c = new Chat();
		c.setRoomNo(roomNo);
		c.setMemNo(memNo);
		c.setAlarmYN(alarm);
		
		int result = new ChatService().updateAlarm(c);
		
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
