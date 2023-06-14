package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Notice;
import com.kh.board.model.vo.Reply;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;

public class BoardService {
	
	public ArrayList<Notice> selectNoticeBoard() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Notice> noticelist = new BoardDao().selectNoticeBoard(conn);
		
		JDBCTemplate.close(conn);
		
		return noticelist;
	}
	
	public ArrayList<Board> selectBestBoard(){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> bestList = new BoardDao().selectBestBoard(conn);
		
		JDBCTemplate.close(conn);
		
		return bestList;
	}
	
	public ArrayList<Board> selectAllBoard(){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> listAll = new BoardDao().selectAllBoard(conn);
		
		JDBCTemplate.close(conn);
		
		return listAll;
	}
	
	public ArrayList<Board> selectFreeBoard(){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> freeList = new BoardDao().selectFreeBoard(conn);
		
		JDBCTemplate.close(conn);
		
		return freeList;
	}
	
	public ArrayList<Board> selectContentBoard(String bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> listDetail = new BoardDao().selectContentBoard(bno, conn);
		
		JDBCTemplate.close(conn);
		
		return listDetail;
	}
	
	public Attachment selectAttachment(String bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn, bno);
		
		JDBCTemplate.close(conn);
		
		return at;
	}
	
	// (전체게시판)페이징 처리 ListCount 조회할 글 총 갯수 가져오기
	public int selectListCount() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> pageList = new BoardDao().selectList(conn, pi);
		
		JDBCTemplate.close(conn);
		
		return pageList;
	}
	// (자유게시판) 및 추가될 게시판의 ListCount 조회할 글 총 갯수 가져오기
	public int selectBoardListCount() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectBoardListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectBoardList(PageInfo pi){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> pageBoardList = new BoardDao().selectBoardList(conn, pi);
		
		JDBCTemplate.close(conn);
		
		return pageBoardList;
	}
	
	public int selectBestBoardListCount(){
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectBestBoardListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectBestBoardList(PageInfo pi){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> pageBoardBestList = new BoardDao().selectBestBoardList(conn, pi);
		
		JDBCTemplate.close(conn);
		
		return pageBoardBestList;
	}
	
	public int selectProductBoardListCount(){
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectBestBoardListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectProductBoardList(PageInfo pi){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> pageBoardProductList = new BoardDao().selectProductBoardList(conn, pi);
		
		JDBCTemplate.close(conn);
		
		return pageBoardProductList;
	}
	
	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result1 = new BoardDao().insertBoard(conn, b);
		
		int result2 = 1;
		if(at != null) {
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		
		if((result1 * result2) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return(result1 * result2);
	}
	
	public int deleteBoard(String bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().deleteBoard(conn, bno);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int insertCount(String bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertCount(conn, bno);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Board> updateSelect(String bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().updateSelect(conn, bno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updateBoard(Board b) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().updateBoard(conn, b);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Reply> selectReplyList(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int insertReply(int bno, String content, int memNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertReply(conn, bno, content, memNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int replyDelete(String bno, String cno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().replyDelete(bno, cno, conn);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int insertNoticeCount(String nno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertNoticeCount(conn, nno);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int searchTitleSelectCount(String search) { // 페이징 갯수 불러오기 기준 > search 검색어
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().searchTitleSelectCount(conn, search);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectSearchBoardList(PageInfo pi, String search) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> pageList = new BoardDao().selectSearchBoardList(conn, pi, search);
		
		JDBCTemplate.close(conn);
		
		return pageList;
	}
	
	public int searchWriterSelectCount(String search) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().searchWriterSelectCount(conn, search);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> searchWriterBoardList(PageInfo pi, String search) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> pageList = new BoardDao().searchWriterBoardList(conn, pi, search);
		
		JDBCTemplate.close(conn);
		
		return pageList;
	}
	
	public int updateReply(Reply r) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().updateReply(conn, r);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	public ArrayList<Board> searchMainBoard(String query) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> searchMainBoardList = new BoardDao().searchMainBoard(conn, query);
		
		JDBCTemplate.close(conn);
		
		return searchMainBoardList;
	}
}




