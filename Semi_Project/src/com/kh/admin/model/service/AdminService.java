package com.kh.admin.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.admin.model.dao.AdminDao;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;
import com.kh.member.model.vo.Member;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.product.model.vo.Product;

public class AdminService {
	
	// 페이징 listCount
	// board
//	public int selectBoardListCount(String searchField, String searchText) {
//		Connection conn = getConnection();
//	
//		int listCount = new NoticeDao().selectBoardListCount(conn, searchField, searchText);
//		
//		close(conn);
//		
//		return listCount;
//	}
	
	
	// MEMBER조회
	public ArrayList<Member> selectMemberAdmin() {
		Connection conn = getConnection();
		
		ArrayList list = new AdminDao().selectMemberAdmin(conn);
		
		close(conn);
		
		return list;
	}
	
	public int deleteMemberAdmin(int memNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().deleteMemberAdmin(conn, memNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int recoverMemberAdmin(int memNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().recoverMemberAdmin(conn, memNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
		
	}
	
	public ArrayList<Member> searchMemberSelect(String searchField, String searchText) {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new AdminDao().searchMemberSelect(conn, searchField, searchText);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Product> selectProductAdmin() {
		Connection conn = getConnection();
		
		ArrayList list = new AdminDao().selectProductAdmin(conn);
		
		close(conn);
		
		return list;
	}
	
	public int deleteProductAdmin(int productNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().deleteProductAdmin(conn, productNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int recoverProductAdmin(int productNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().recoverProductAdmin(conn, productNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public ArrayList<Product> searchProductList(String searchField, String searchText) {
		Connection conn = getConnection();
		
		ArrayList<Product> list = new AdminDao().searchProductList(conn, searchField, searchText);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Board> selectBoardAdmin() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new AdminDao().selectBoardAdmin(conn);
		
		close(conn);
		
		return list;
	}
	
	public int deleteBoard(int boardNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().deleteBoard(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int recoverBoard(int boardNo) {
		Connection conn = getConnection();
		
		int result = new AdminDao().recoverBoard(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<Board> searchBoard(String searchField, String searchText) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new AdminDao().searchBoard(conn, searchField, searchText);
		
		close(conn);
		
		return list;
	}

	
	

}
