package com.kh.chat.model.sevice;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.chat.model.dao.ChatDao;
import com.kh.chat.model.vo.Chat;
import com.kh.common.JDBCTemplate;

public class ChatService {

	public ArrayList<Chat> chatselectMyList(int userNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Chat> list = new ChatDao().chatselectMyList(conn, userNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<Chat> chatDetailLog(int roomNo){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Chat> list = new ChatDao().chatDetailLog(conn, roomNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int insertChat(Chat c) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ChatDao().insertChat(conn, c);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int updateAlarm(Chat c) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ChatDao().updateAlarm(conn, c);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int updateRoom(int roomNo, String roomState) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ChatDao().updateRoom(conn, roomNo, roomState);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public Chat checkRoomState(Chat c , int yourMemNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Chat nc = new ChatDao().checkRoomState(conn, c , yourMemNo);
		
		JDBCTemplate.close(conn);
		
		return nc;
	}
	
	public int createProductRoom(Chat c, int yourMemNo) {
		
		Connection conn = JDBCTemplate.getConnection();
    	//기능2. 채팅방 생성
		int result1 = new ChatDao().createProductRoom(conn, c.getProductNo());
    	
    	//기능3. 채팅방 멤버 넣기
		// 나 추가
		int result2 = new ChatDao().insertRoomMember(conn, c.getMemNo());
		// 상대방 추가
		int result3 = new ChatDao().insertRoomMember(conn, yourMemNo);
		// 내가 상대방한테 빈공백 보내기
		int result4 = new ChatDao().insertBlankChat(conn, c.getMemNo());
    
		int result = result1 * result2 * result3 * result4;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int createNormalRoom(Chat c, int yourMemNo) {
		
		Connection conn = JDBCTemplate.getConnection();
    	//기능2. 채팅방 생성
		int result1 = new ChatDao().createNormalRoom(conn);
    	
    	//기능3. 채팅방 멤버 넣기
		// 나 추가
		int result2 = new ChatDao().insertRoomMember(conn, c.getMemNo());
		// 상대방 추가
		int result3 = new ChatDao().insertRoomMember(conn, yourMemNo);
		// 내가 상대방한테 빈공백 보내기
		int result4 = new ChatDao().insertBlankChat(conn, c.getMemNo());
    
		int result = result1 * result2 * result3 * result4;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int completeAuction(int productNo, int memNo, String productName) {
		
		
		Connection conn = JDBCTemplate.getConnection();
    	//기능2. 채팅방 생성
		int result1 = new ChatDao().createProductRoom(conn, productNo);
    	
    	//기능3. 채팅방 멤버 넣기
		// 나 추가
		int result2 = new ChatDao().insertRoomMember(conn, 1);
		// 상대방 추가
		int result3 = new ChatDao().insertRoomMember(conn, memNo);
		// 내가 상대방한테 낙찰메세지 보내기
		int result4 = new ChatDao().insertCompleteChat(conn, productName);
		
		int result = result1 * result2 * result3 * result4;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	public int updateProductRoom(int productNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ChatDao().updateProductRoom(conn, productNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
}
