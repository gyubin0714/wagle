package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Notice;
import com.kh.board.model.vo.Reply;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;

public class BoardDao {
	
	Properties prop = new Properties();
	
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board_mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Notice> selectNoticeBoard(Connection conn) {
		
		ArrayList<Notice> noticelist = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("TITLE"));
				n.setNoticeContent(rset.getString("CONTENT"));
				n.setNoticeWriteDate(rset.getDate("WRITE_DATE"));
				n.setNoticeCategoryNo(rset.getInt("CS_CATEGORY_NO"));
				n.setNoticeCount(rset.getInt("COUNT"));
				
				noticelist.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return noticelist;
	}
	
	public ArrayList<Board> selectBestBoard(Connection conn){
		
		ArrayList<Board> bestList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBestBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				bestList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return bestList;
	}
	
	public ArrayList<Board> selectAllBoard(Connection conn){
		
		ArrayList<Board> listAll = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAllBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				listAll.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listAll;
	}
	
	public ArrayList<Board> selectFreeBoard(Connection conn){
		
		ArrayList<Board> freeList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFreeBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				freeList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return freeList;
	}
	
	public ArrayList<Board> selectContentBoard(String bno, Connection conn) {
		
		ArrayList<Board> listDetail = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectContentBoard");
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				b.setMemNo(rset.getInt("MEM_NO"));
				
				listDetail.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listDetail;
	}
	
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		
		ArrayList<Board> pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				pageList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pageList;
	}
	
	public int selectBoardListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoardListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Board> selectBoardList(Connection conn, PageInfo pi){
		
		ArrayList<Board> pageBoardList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				pageBoardList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pageBoardList;
	}
	
	public int selectBestBoardListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Board> selectBestBoardList(Connection conn, PageInfo pi){
		
		ArrayList<Board> pageBoardBestList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBestBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				pageBoardBestList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pageBoardBestList;
	}
	
	public int selectProductBoardListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProductBoardListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Board> selectProductBoardList(Connection conn, PageInfo pi){
		
		ArrayList<Board> pageBoardProductList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectProductBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				pageBoardProductList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pageBoardProductList;
	}
	
	public int insertBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriting());
			pstmt.setString(3, b.getCategoryNo());
			pstmt.setString(4, b.getMemberNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Attachment selectAttachment(Connection conn, String bno) {
		
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bno));
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setUpdateDate(rset.getDate("UPDATE_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return at;
	}
	
	public int deleteBoard(Connection conn, String bno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertCount(Connection conn, String bno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(bno));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	// 수정 페이지에 게시글번호를 통해 게시글조회 시켜주기
	public ArrayList<Board> updateSelect(Connection conn, String bno) {
		
		ArrayList<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("updateSelect");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(bno));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int updateBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriting());
			pstmt.setString(3, b.getCategoryNo());
			pstmt.setInt(4, b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Reply> selectReplyList(Connection conn, int boardNo) {
		
		ArrayList<Reply> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setCommentNo(rset.getInt("COMMENT_NO"));
				r.setContent(rset.getString("CONTENT"));
				r.setBoardNo(rset.getInt("BOARD_NO"));
				r.setNickName(rset.getString("NICKNAME"));
				r.setGroupNum(rset.getInt("GROUP_NUM"));
				r.setCommentDate(rset.getDate("COMMENT_DATE"));
				r.setMemNo(rset.getInt("MEM_NO"));
				list.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int insertReply(Connection conn, int bno, String content, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, bno);
			pstmt.setInt(3, memNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int replyDelete(String bno, String cno, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("replyDelete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(cno));
			pstmt.setInt(2, Integer.parseInt(bno));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertNoticeCount(Connection conn, String nno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNoticeCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(nno));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int searchTitleSelectCount(Connection conn, String search) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchTitleSelectCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Board> selectSearchBoardList(Connection conn, PageInfo pi, String search){
		
		ArrayList<Board> pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, search);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				pageList.add(b);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pageList;
	}
	
	public int searchWriterSelectCount(Connection conn, String search) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchWriterSelectCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Board> searchWriterBoardList(Connection conn, PageInfo pi, String search){
		
		ArrayList<Board> pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchWriterBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, search);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				pageList.add(b);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pageList;
	}
	
	public int updateReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getContent());
			pstmt.setInt(2, r.getCommentNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Board> searchMainBoard(Connection conn, String query) {
		
		ArrayList<Board> searchMainBoardList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchMainBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardHits(rset.getInt("BOARD_HITS"));
				b.setCategoryNo(rset.getString("BOARD_NAME"));
				b.setMemberNo(rset.getString("NICKNAME"));
				
				searchMainBoardList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return searchMainBoardList;
	}
}










