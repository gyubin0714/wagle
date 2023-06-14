package com.kh.member.model.service;



import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Attachment;
import com.kh.member.model.dao.MemDao;
import com.kh.member.model.vo.Follow;
import com.kh.member.model.vo.Member;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.product.model.vo.Product;
import com.kh.review.model.vo.Review;

public class MemService {
	
	
	
	
	public Member loginMember(String memId, String memPwd) {
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemDao().loginMember(conn, memId, memPwd);
		
		JDBCTemplate.close(conn);
		
		return m;
		
	}
   public int insertMember(Member m , Attachment at) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result1 = new MemDao().insertMember(conn, m);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new MemDao().insertAttachment(conn, at);
			System.out.println(result2);
		}
		
		if((result1 * result2) > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		
		return (result1 * result2);
   }
	
	
	public int idCheck(String checkId) {
		Connection  conn = JDBCTemplate.getConnection();
		
		int result = new MemDao().idCheck(conn, checkId);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int nicknameCheck(String checkNickname) {
		Connection  conn = JDBCTemplate.getConnection();
		
		int count = new MemDao().nicknameCheck(conn, checkNickname);
		
		JDBCTemplate.close(conn);
		
		return count;
	}
	public Member idSearch(String memName, String email) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemDao().idSearch(conn, memName, email);
		
		JDBCTemplate.close(conn);
		
		return m;
				
	}
	public Member pwdSearch(String memId, String pwdQ , String pwdA) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemDao().pwdSearch(conn, memId, pwdQ , pwdA);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	public ArrayList<Follow> followList(int memNo){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Follow> list = new MemDao().followList(conn, memNo);
		JDBCTemplate.close(conn);
		return list;
		
				
	}
	public int updateMember(Member m, Attachment at) {
			Connection conn = JDBCTemplate.getConnection();
			
			int result1 = new MemDao().updateMember(conn, m);
			
			int result2 = 1;
			
			if(at != null ) {//성공
				
				if(at.getFileNo() != 0 ) {
					result2 = new MemDao().updateAttachment(conn, at);
				}else {
					result2 = new MemDao().insertNewAttachment(conn, at);
				}
			}
			if((result1 * result2) >0) {
				
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return(result1 * result2);
				
	}
	public Member selectMember(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemDao().selectMember(conn, memNo);
		System.out.println(m);
		JDBCTemplate.close(conn);
		return m;
	}
	
	public Attachment selectAttachment(int memNo) {
		Connection conn = getConnection();
		Attachment at = new MemDao().selectAttachment(conn, memNo);
		JDBCTemplate.close(conn);
		return at;
	}
	
	public Attachment selectProductAttachment(int productNo) {
		Connection conn = getConnection();
		Attachment at = new MemDao().selectAttachment(conn, productNo);
		JDBCTemplate.close(conn);
		return at;
	}
	
	public ArrayList<Board> selectMyBoard(int memNo){
		Connection conn = getConnection();
		ArrayList<Board> list = new MemDao().selectMyBoard(conn, memNo);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public ArrayList<Product> selectMyInterest(int memNo){
		Connection conn = getConnection();
		ArrayList<Product> list = new MemDao().selectMyInterest(conn, memNo);
		JDBCTemplate.close(conn);
		return list;
		
	}
	
	public int deleteInterest(int memNo, int productNo) {
		Connection conn = getConnection();
		
		int result = new MemDao().deleteInterest(conn, memNo, productNo);
	
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public ArrayList<Product> selectMyEnrollProduct(int memNo) {
		Connection conn = getConnection();
		ArrayList<Product> list = new MemDao().selectMyEnrollProduct(conn, memNo);
		JDBCTemplate.close(conn);
		return list;
	}
	public ArrayList<Product> selectMyDeal(int memNo){
		Connection conn = getConnection();
		ArrayList<Product> list = new MemDao().selectMyDeal(conn, memNo);
		JDBCTemplate.close(conn);
		return list;
		
	}
	public ArrayList<Product> writableReview(int memNo){
		Connection conn = getConnection();
		ArrayList<Product> list = new MemDao().writableReview(conn, memNo);
		JDBCTemplate.close(conn);
		return list;
	}

	public Member memberSearch(String nickname) {
		
		Connection  conn = JDBCTemplate.getConnection();
		
		Member m = new MemDao().memberSearch(conn, nickname);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	public Product selectProduct(int productNo) {
		Connection conn = getConnection();
		Product p = new MemDao().selectProduct(conn,productNo);
		close(conn);
		return p;
	}
	public int insertReview(Review r) {
		Connection conn = getConnection();
		int result = new MemDao().insertReview(conn, r);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	public ArrayList<Review> selectReviewList(int memNo){
		Connection conn = getConnection();
		ArrayList<Review> reviewList = new MemDao().selectReviewList(conn, memNo);
		JDBCTemplate.close(conn);
		return reviewList;
		
		
	}
	public int deleteFollowing(int memNo, int following) {
		Connection conn = getConnection();
		
		int result = new MemDao().deleteFollowing(conn, memNo, following);
	
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	
}
