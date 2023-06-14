package com.kh.divide.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.divide.model.dao.DivideDao;
import com.kh.divide.model.vo.Divide;
import com.kh.divide.model.vo.Divide_Comment;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductCategory;




public class DivideService {


	public ArrayList<Divide> selectDivideList() {
		
		Connection conn = getConnection();
		
		ArrayList<Divide> list = new DivideDao().selectDivideList(conn);
		
		close(conn);
		//System.out.println(list);
		
		return list;
		
	}

	public int insertDivideBoard(Divide d, ArrayList<Attachment> list) {
		
		Connection conn = getConnection();
		
		
		int result1 = new DivideDao().insertDivideBoard(conn, d);
		
		int result2 = new DivideDao().insertAttachmentList(conn, list);
		
		if((result1 * result2)>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return (result1 * result2);
	}
	
	public int updateDivideBoard(Divide d, ArrayList<Attachment> list) {
	    
		Connection conn = getConnection();
	    
		int result1 = new DivideDao().updateDivideBoard(conn, d);
	    // ATTACHMENT테이블과 관련된 작업
	    int result2 = 1;
	    int result3 = 1;
	    System.out.println(list.size());
	    // 새롭게 첨부파일이 있을 경우
	    if(list.size() > 0) {
	    	// 이미지 수정 => UPDATE
			if(list.get(0).getFileNo() != 0) {
				result2 = new DivideDao().updateAttachment(conn, list);
			}
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getFileNo() != 0) {
					result3 = new DivideDao().updateAttachment(conn, list);
				} else {
					result3 = new DivideDao().insertNewAttachment(conn, list.get(i));
				}
			}
			if((result1 * result2 * result3) > 0) {
		        commit(conn);
		    } else {
		        rollback(conn);
		    }
		} else {
		    if(result1 > 0) {
		        commit(conn);
		    } else {
		        rollback(conn);
		    }

			close(conn);
		}
		return ((result1 * result2 * result3));
	}

	public ArrayList<ProductCategory> selectCategoryList() {
		Connection conn = getConnection();
		
		ArrayList<ProductCategory> list = new DivideDao(). selectCategoryList(conn);
		
		close(conn);
		
		return list;
	}

	public int increaseCount(int dNo) {

		Connection conn = getConnection();
		
		int result = new DivideDao().increaseCount(conn, dNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public Divide selectBoard(int dNo) {
		
		Connection conn = getConnection();
		
		Divide d = new DivideDao().selectBoard(conn, dNo);
		
		close(conn);
		
		return d;
	}
	public ArrayList<Attachment> selectAttachmentList(int dNo) {

		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new DivideDao().selectAttachment(conn, dNo);
		
		close(conn);
		
		return list;

		
		
	}

	public int deleteDivide(int dno) {

		Connection conn = getConnection();
		
		int result = new DivideDao().deleteDivide(conn, dno);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);	
		
		return result;
	}

	public ArrayList<Divide_Comment> selectReplyList(int dno) {

		Connection conn = getConnection();
		
		ArrayList<Divide_Comment> list = new DivideDao().selectReplyList(conn, dno);
		
		close(conn);
		
		return list;
	}

	public int insertReply(Divide_Comment d) {

		Connection conn = getConnection();
		
		int result = new DivideDao().insertReply(conn, d);
		
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int selectListCount() {
		Connection conn = getConnection();
		
		int listCount = new DivideDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Divide> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Divide> list = new DivideDao().selectList(conn,pi);
		
		close(conn);
		
		return list;
	}

	public Divide auctionBoard(int dNo) {
		Connection conn = getConnection();
		
		Divide d = new DivideDao().auctionBoard(conn, dNo);
		
		close(conn);
		
		return d;
	}

	public Product purchaseBoard(int pNo) {
		Connection conn = getConnection();
		
		Product p = new DivideDao().purchaseBoard(conn, pNo);
		
		close(conn);
		
		return p;
	}

}
