package com.kh.notice.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Category;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	// 1:1문의
	
	// 페이징 , listCount
	public int selectListCount(String memNo) {
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCount(conn, memNo);
		
		close(conn);
		
		return listCount;
	}
	
	// 공지사항 페이징 count
	public int selectNoticeListCount() {
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectNoticeListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Notice> selectInquiryList(PageInfo pi, String memNo) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectInquiryList(conn, pi, memNo);
		
		close(conn);
		
		return list;
		
	}
	
	// 관리자 로그인시 페이징
	public int selectListCountAdmin() {
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCountAdmin(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Notice> selectInquiryListAdmin(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectInquiryListAdmin(conn, pi);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Category> selectInquiryCategory() {
		Connection conn = getConnection();
		
		ArrayList<Category> list = new NoticeDao().selectInquiryCategory(conn);
		
		close(conn);
		
		return list;
	}
	
	public int insertInquiry(Notice n, Attachment at) {
		Connection conn = getConnection();
		// NOTICE 테이블에 INSERT
		int resultN = new NoticeDao().insertInquiry(conn, n); 
		
		// ATTACHMENT테이블에 INSERT
		int resultAt = 1;
		if(at != null) {
			resultAt = new NoticeDao().insertInquiryAttachment(conn, at);
		}
		
		if((resultN * resultAt) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return (resultN * resultAt);
	}
	
	public Notice selectInquiry(int inquiryNo) {
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectInquiry(conn, inquiryNo);
		
		close(conn);
		
		return n;
	}
	
	public Attachment selectInquiryAttachment(int inquiryNo) {
		Connection conn = getConnection();
		
		Attachment at = new NoticeDao().selectInquiryAttachment(conn, inquiryNo);
		
		close(conn);
		
		return at;
	}
	
	public int updateInquiry(Notice n, Attachment at) {
		Connection conn = getConnection();
		
		int resultN = new NoticeDao().updateInquiry(conn, n);
		
		int resultAt = 1;
		
		if(at!= null) {
			if(at.getFileNo() != 0) {
				// 기존에 첨부파일이 있는 경우 => 새로운 첨부파일로 update
				resultAt = new NoticeDao().updateInquiryAttachment(conn, at);
			} else {
				// 기존에 첨부파일이 없는 경우 => 새로운 첨부파일 insert
				resultAt = new NoticeDao().insertNewAttachment(conn, at);
			}
		}
		
		if((resultN * resultAt) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return (resultN * resultAt);
		
	}
	
	public int deleteInquiry(int inquiryNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteInquiry(conn, inquiryNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int insertInquiryAnswer(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertInquiryAnswer(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// FAQ 자주 찾는 질문
	
	public ArrayList<Notice> selectFaqAllList() {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectFaqAllList(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Notice> selectFaqListCategory(String category) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectFaqListCategory(conn, category);
		
		close(conn);
		
		return list;
	}

	
	public ArrayList<Notice> selectFaqSearchList(String searchWord) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectFaqSearchList(conn, searchWord);
		
		close(conn);
		
		return list;
		
	}
	
	// 공지사항
	
	public ArrayList<Notice> selectNoticeList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Category> selectNoticeCategory() {
		Connection conn = getConnection();
		
		ArrayList<Category> list = new NoticeDao().selectNoticeCategory(conn);
		
		close(conn);
		
		return list;
	}
	
	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int increaseCount(int noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);
		
		close(conn);
		
		return n;
	}
	
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	// faq
	
	public ArrayList<Category> selectFaqCategory() {
		Connection conn = getConnection();
		
		ArrayList<Category> list = new NoticeDao().selectFaqCategory(conn);
		
		close(conn);
		
		return list;
	}
	
	public int insertFaq(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertFaq(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public Notice selectFaq(int faqNo) {
		Connection conn = getConnection();
		Notice n = new NoticeDao().selectFaq(conn, faqNo);
		close(conn);
		
		return n;
	}
	
	public int updateFaq(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDao().updateFaq(conn, n);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int deleteFaq(int faqNo) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteFaq(conn, faqNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int insertVoc(Notice n) {
		
		Connection conn = getConnection();

		// NOTICE 테이블 INSERT
		int result = new NoticeDao().insertVoc(conn, n); 
		
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
		
	}
	
	public ArrayList<Notice> selectListVoc() {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectListVoc(conn);
		
		close(conn);
		
		return list;
	}
	
	public int deleteVoc(int vocNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteVoc(conn, vocNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<Notice> selectCategory(String category) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectCategory(conn, category);
		
		close(conn);
		
		return list;
	}
	 
	


}
