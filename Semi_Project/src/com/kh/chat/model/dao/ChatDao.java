package com.kh.chat.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.chat.model.vo.Chat;
import com.kh.common.JDBCTemplate;

public class ChatDao {
	
	private Properties prop = new Properties();
	
	public ChatDao() {
		String fileName = ChatDao.class.getResource("/sql/chat/chat_mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Chat> chatselectMyList(Connection conn, int userNo){
		
		ArrayList<Chat> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("chatselectMyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, userNo);
			pstmt.setInt(4, userNo);
			pstmt.setInt(5, userNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Chat c = new Chat();
				c.setRoomNo(rset.getInt("ROOM_NO"));
				c.setChatType(rset.getString("CHAT_TYPE"));
				c.setRoomState(rset.getString("ROOM_STATE"));
				c.setProductNo(rset.getInt("PRODUCT_NO"));
				c.setMemNo(rset.getInt("MEM_NO"));
				c.setNickName(rset.getString("NICKNAME"));
				c.setAlarmYN(rset.getString("ALARM"));
				c.setChatTime(rset.getString("CHAT_TIME"));
				
				String chatContent = rset.getString("CHAT_CONTENT");
				
				if(chatContent.contains("<")) {
					c.setChatContent(chatContent.substring(0,chatContent.indexOf("<")));
				}else {
					c.setChatContent(chatContent);
				}
				
				c.setChatNo(rset.getInt("CHAT_NO"));
				
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Chat> chatDetailLog(Connection conn, int roomNo){
		
		ArrayList<Chat> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("chatDetailLog");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Chat c = new Chat();
				c.setChatNo(rset.getInt("CHAT_NO"));
				c.setChatContent(rset.getString("CHAT_CONTENT"));
				c.setChatTime(rset.getString("CHAT_TIME"));
				c.setMemNo(rset.getInt("MEM_NO"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int insertChat(Connection conn, Chat c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertChat");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getChatContent());
			pstmt.setInt(2, c.getRoomNo());
			pstmt.setInt(3, c.getMemNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateAlarm(Connection conn, Chat c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAlarm");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getAlarmYN());
			pstmt.setInt(2, c.getMemNo());
			pstmt.setInt(3, c.getRoomNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int updateRoom(Connection conn, int roomNo, String roomState) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateRoom");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, roomState);
			pstmt.setInt(2, roomNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Chat checkRoomState(Connection conn, Chat oc, int yourMemNo) {
		
		Chat nc = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("checkRoomState");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oc.getMemNo());
			pstmt.setInt(2, yourMemNo);
			pstmt.setString(3, oc.getChatType());
			pstmt.setString(4, oc.getChatType());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				nc = new Chat();
				
				nc.setCount(rset.getInt("COUNT"));
				nc.setRoomState(rset.getString("ROOM_STATE"));
				nc.setRoomNo(rset.getInt("ROOM_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return nc;
	}
	
	public int createProductRoom(Connection conn, int productNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("createProductRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int createNormalRoom(Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("createNormalRoom");
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertRoomMember(Connection conn, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertRoomMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertBlankChat(Connection conn, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBlankChat");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertCompleteChat(Connection conn, String productName) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCompleteChat");
		
		String message = "회원님께서 입찰하신 상품 "+productName+"을(를) 낙찰 받으셨습니다.<br>"
				+ "일주일 이내로 결제 페이지로 이동하여 거래를 완료해주시기 바랍니다.<br>"
				+ "기간이 만료될 시 페이지 이용에 제한이 생길 수 있습니다.<br>"
				+ "<button class='btn btn-sm btn-secondary' id='a_buy_auction'>결제하기</button>";
			
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	public int updateProductRoom(Connection conn, int productNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductRoom");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
