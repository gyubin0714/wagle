package com.kh.admin.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;
import com.kh.member.model.vo.Member;
import com.kh.product.model.vo.Product;

public class AdminDao {
	
private Properties prop = new Properties();
	
	public AdminDao() {
		
		String fileName = AdminDao.class.getResource("/sql/admin/admin_mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 페이징바 listCount
	// board
	// 페이징바 listCount
//	public int selectBoardListCount(Connection conn, String searchField, String searchText) {
//		
//		int listCount = 0;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		String sql = prop.getProperty("selectBoardListCount");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, searchField);
//			pstmt.setString(2, searchText);
//			
//			rset = pstmt.executeQuery();
//			
//			
//			if(rset.next()) {
//				listCount = rset.getInt("COUNT(*)");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		
//		return listCount;
//		
//	}
	
	// MEMBER조회	
	public ArrayList<Member> selectMemberAdmin(Connection conn) {
		
		ArrayList<Member> list = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Member m = null;
		
		String sql = prop.getProperty("selectMemberAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				
//				 MEM_NO,
//			       MEM_ID,
//			       MEM_NAME,
//			       ENROLL_DATE,
//			       MEM_STATUS
				
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setMemStatus(rset.getString("MEM_STATUS"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	public int deleteMemberAdmin(Connection conn, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMemberAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int recoverMemberAdmin(Connection conn, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("recoverMemberAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public ArrayList<Member> searchMemberSelect(Connection conn, String searchField, String searchText) {
		
		ArrayList<Member> list = new ArrayList();
		Member m = null;
		ResultSet rset = null;
		Statement stmt = null;
		
		String sql = "SELECT "
							+ "MEM_NO,"
							+ "MEM_ID,"
							+ "MEM_NAME,"
							+ "ENROLL_DATE,"
							+ "MEM_STATUS "
						+ "FROM "
							+ "MEMBER "
						+ "WHERE "
							+ searchField + "= '" + searchText + "'" ;
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);

			while(rset.next()) {
				m = new Member();
				
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemId(rset.getString("MEM_ID"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setMemStatus(rset.getString("MEM_STATUS"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}
	
	public ArrayList<Product> selectProductAdmin(Connection conn) {
		
		ArrayList<Product> list = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Product p = null;
		
		String sql = prop.getProperty("selectProductAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				p = new Product();
				
//		           PRODUCT_NO,
//				   AUCTION_NO,
//			       PRODUCT_NAME,
//			       MEM_ID,
//			       MEM_NAME,
//			       PRODUCT_TRADE_STATUS,
//			       CREATE_DT
//			       DELETE_YN
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setAuctionNo(rset.getInt("AUCTION_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setMemId(rset.getString("MEM_ID"));
				p.setMemName(rset.getString("MEM_NAME"));
				p.setProductTradeStatus(rset.getString("PRODUCT_TRADE_STATUS"));
				p.setCreateDt(rset.getDate("CREATE_DT"));
				p.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	public int deleteProductAdmin(Connection conn, int productNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteProductAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
			
	}
	
	public int recoverProductAdmin(Connection conn, int productNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("recoverProductAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public ArrayList<Product> searchProductList(Connection conn, String searchField, String searchText) {
		
		ArrayList<Product> list = new ArrayList();
		ResultSet rset = null;
		Product p = null;
		Statement stmt = null;
		
//        PRODUCT_NO,
//	       PRODUCT_NAME,
//	       MEM_ID,
//	       MEM_NAME,
//	       PRODUCT_TRADE_STATUS,
//	       CREATE_DT,
//	       DELETE_YN
		
		String sql = "SELECT "
							+ "PRODUCT_NO, "
							+ "AUCTION_NO, "
							+ "PRODUCT_NAME, "
							+ "MEM_ID, "
							+ "MEM_NAME, "
							+ "PRODUCT_TRADE_STATUS, "
							+ "CREATE_DT, "
							+ "DELETE_YN "
						+ "FROM "
							+ "PRODUCT "
						+ "JOIN "
							+ "MEMBER USING(MEM_NO) "
						+ "LEFT JOIN "
							+ "AUCTION USING(PRODUCT_NO) "
						+ "WHERE " 
							+ searchField + "= '" + searchText + "'";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				p = new Product();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setAuctionNo(rset.getInt("AUCTION_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setMemId(rset.getString("MEM_ID"));
				p.setMemName(rset.getString("MEM_NAME"));
				p.setProductTradeStatus(rset.getString("PRODUCT_TRADE_STATUS"));
				p.setCreateDt(rset.getDate("CREATE_DT"));
				p.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(p);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
		
	}
	
	public ArrayList<Board> selectBoardAdmin(Connection conn) {
			
		ArrayList<Board> list = new ArrayList();
		Board b = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectBoardAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				b = new Board();
				
//				 BOARD_NO,
//			      BOARD_TITLE,
//			      BOARD_WRITING,
//			      BOARD_DATE,
//			      BOARD_DELETE_YN,
//			      MEM_ID,
//			      MEM_NAME
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardDelete(rset.getString("BOARD_DELETE_YN"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setMemberNo(rset.getString("MEM_NAME"));
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int deleteBoard(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int recoverBoard(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("recoverBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Board> searchBoard(Connection conn, String searchField, String searchText) {
		
		ArrayList<Board> list = new ArrayList();
		ResultSet rset = null;
		Board b = null;
		Statement stmt = null;
		
//		BOARD_NO,
//	      BOARD_TITLE,
//	      BOARD_WRITING,
//	      BOARD_DATE,
//	      BOARD_DELETE_YN,
//	      MEM_ID,
//	      MEM_NAME
		
		String sql = "SELECT "
							+ "BOARD_NO, "
							+ "BOARD_TITLE, "
							+ "BOARD_WRITING, "
							+ "BOARD_DATE, "
							+ "BOARD_DELETE_YN, "
							+ "MEM_ID, "
							+ "MEM_NAME "
						+ "FROM "
							+ "BOARD "
						+ "JOIN "
							+ "MEMBER USING(MEM_NO) "
						+ "WHERE " 
							+ searchField + "= '" + searchText + "'";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriting(rset.getString("BOARD_WRITING"));
				b.setBoardDate(rset.getDate("BOARD_DATE"));
				b.setBoardDelete(rset.getString("BOARD_DELETE_YN"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setMemberNo(rset.getString("MEM_NAME"));
				
				list.add(b);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
		
	}



}
