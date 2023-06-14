package com.kh.notice.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.vo.Category;
import com.kh.notice.model.vo.Notice;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		String fileName = NoticeDao.class.getResource("/sql/notice/notice_mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 페이징바 listCount
	public int selectListCount(Connection conn, String memNo) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memNo);
			
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
	}
	
public int selectNoticeListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Notice> selectInquiryList(Connection conn,  PageInfo pi, String memNo) {
		
		ArrayList<Notice> list = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectInquiryList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, memNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				
//				INQUIRY_NO,
//		        TITLE,
//		        CONTENT,
//		        MEM_NAME,
//		        WRITE_DATE
				
				n.setInquiryNo(rset.getInt("INQUIRY_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setWriter(rset.getString("MEM_NAME"));
				n.setWriteDate(rset.getDate("WRITE_DATE"));
				n.setAnswer(rset.getString("ANSWER"));
				
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int selectListCountAdmin(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectListCountAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
	}
	
	public ArrayList<Notice> selectInquiryListAdmin(Connection conn,  PageInfo pi) {
		
		ArrayList<Notice> list = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectInquiryListAdmin");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				
//				INQUIRY_NO,
//		        TITLE,
//		        CONTENT,
//		        MEM_NAME,
//		        WRITE_DATE
				
				n.setInquiryNo(rset.getInt("INQUIRY_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setWriter(rset.getString("MEM_NAME"));
				n.setWriteDate(rset.getDate("WRITE_DATE"));
				n.setAnswer(rset.getString("ANSWER"));
				
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	public ArrayList<Category> selectInquiryCategory(Connection conn) {
		
		ArrayList<Category> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectInquiryCategoryList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Category c = new Category();
				
				c.setCsCategoryNo(rset.getInt("CS_CATEGORY_NO"));
				c.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(c);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertInquiry(Connection conn, Notice n) {
		
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertInquiry");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setString(3, n.getAnswerNotice());
			pstmt.setInt(4, Integer.parseInt(n.getCsCategoryName()));
			pstmt.setInt(5,  Integer.parseInt(n.getWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int insertInquiryAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertInquiryAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Notice selectInquiry(Connection conn, int inquiryNo) {
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectInquiry");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquiryNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice();
				n.setInquiryNo(rset.getInt("INQUIRY_NO"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				n.setAnswerNotice(rset.getString("ANSWER_NOTICE"));
				n.setTitle(rset.getString("TITLE"));
				n.setWriter(rset.getString("MEM_NAME"));
				n.setContent(rset.getString("CONTENT"));
				n.setWriteDate(rset.getDate("WRITE_DATE"));
				n.setAnswer(rset.getString("ANSWER"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
		
	}
	
	public Attachment selectInquiryAttachment(Connection conn, int inquiryNo) {
		
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectInquiryAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquiryNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}
	
	public int updateInquiry(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateInquiry");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(n.getCsCategoryName()));
			pstmt.setString(2, n.getAnswerNotice());
			pstmt.setString(3, n.getTitle());
			pstmt.setString(4, n.getContent());
			pstmt.setInt(5, n.getInquiryNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int updateInquiryAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateInquiryAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;	
		
	}
	
	public int insertNewAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, at.getRefBno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int deleteInquiry(Connection conn, int inquiryNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteInquiry");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquiryNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertInquiryAnswer(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertInquiryAnswer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getAnswer());
			pstmt.setInt(2, n.getInquiryNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return result;
		
		
	}

	public ArrayList<Notice> selectFaqAllList(Connection conn) {
		
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqAllList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
//				   FAQ_NO,
//			       TITLE,
//			       CONTENT,
//			       CS_CATEGORY_NAME
				
				n.setFaqNo(rset.getInt("FAQ_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}
	
	public ArrayList<Notice> selectFaqListCategory(Connection conn, String category) {
		
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqListCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
				
				n.setFaqNo(rset.getInt("FAQ_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}
	
	public ArrayList<Notice> selectFaqListCategory2(Connection conn) {
		
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqListCategory2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
				
				n.setFaqNo(rset.getInt("FAQ_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}

	public ArrayList<Notice> selectFaqListCategory3(Connection conn) {
	
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqListCategory3");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
				
				n.setFaqNo(rset.getInt("FAQ_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}

	public ArrayList<Notice> selectFaqListCategory4(Connection conn) {
	
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqListCategory4");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
				
				n.setFaqNo(rset.getInt("FAQ_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}
	
	public ArrayList<Notice> selectFaqSearchList(Connection conn, String searchWord) {
		
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqSearchList");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, '%' + searchWord + '%');
			pstmt.setString(2, '%' + searchWord + '%');
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Notice n = new Notice();
				
				n.setFaqNo(rset.getInt("FAQ_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(n);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}
	
	// 공지사항
	
public ArrayList<Notice> selectNoticeList(Connection conn, PageInfo pi) {
		
		ArrayList<Notice> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
				
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
				n.setCount(rset.getInt("COUNT")); // 조회수
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				n.setWriteDate(rset.getDate("WRITE_DATE"));
				list.add(n);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}

	public ArrayList<Category> selectNoticeCategory(Connection conn) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Category> list = new ArrayList();
		
		String sql = prop.getProperty("selectNoticeCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Category c = new Category();
				
				c.setCsCategoryNo(rset.getInt("CS_CATEGORY_NO"));
				c.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertNotice(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNotice");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setInt(3, Integer.parseInt(n.getCsCategoryName()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int increaseCount(Connection conn, int noticeNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Notice selectNotice(Connection conn, int noticeNo) {
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				n.setTitle(rset.getString("TITLE"));
				n.setWriteDate(rset.getDate("WRITE_DATE"));
				n.setContent(rset.getString("CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
	}

	public int updateNotice(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setInt(3, Integer.parseInt(n.getCsCategoryName()));
			pstmt.setInt(4, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteNotice(Connection conn, int noticeNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
	// faqDAO
	public ArrayList<Category> selectFaqCategory(Connection conn) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Category> list = new ArrayList();
		
		String sql = prop.getProperty("selectFaqCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Category c = new Category();
				
				c.setCsCategoryNo(rset.getInt("CS_CATEGORY_NO"));
				c.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertFaq(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertFaq");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setInt(3, Integer.parseInt(n.getCsCategoryName()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public Notice selectFaq(Connection conn, int faqNo) {
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, faqNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice();
				n.setFaqNo(rset.getInt("FAQ_NO"));
				n.setCsCategoryName(rset.getString("CS_CATEGORY_NAME"));
				n.setTitle(rset.getString("TITLE"));
				n.setContent(rset.getString("CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
	}

	public int updateFaq(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setInt(3, Integer.parseInt(n.getCsCategoryName()));
			pstmt.setInt(4, n.getFaqNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteFaq(Connection conn, int faqNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteFaq");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, faqNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
	public int insertVoc(Connection conn, Notice n) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertVoc");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getContent());
			pstmt.setString(2, n.getVocMember());
			pstmt.setString(3, n.getVocCategory());
			pstmt.setString(4, n.getWriter());
			pstmt.setString(5, n.getTitle());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
	}
	
	
	public ArrayList<Notice> selectListVoc(Connection conn) {
		ArrayList<Notice> list = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Notice n = null;
		
		String sql = prop.getProperty("selectListVoc");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				n = new Notice();
				
//				VOC_NO,
//			       VOC_CATEGORY,
//			       TITLE,
//			       VOC_ID,
//			       MEM_ID,
//			       CONTENT
				
				n.setVocNo(rset.getInt("VOC_NO"));
				n.setVocCategory(rset.getString("VOC_CATEGORY"));
				n.setTitle(rset.getString("TITLE"));
				n.setVocMember(rset.getString("VOC_ID"));
				n.setWriter(rset.getString("MEM_ID"));
				n.setContent(rset.getString("CONTENT"));
				n.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(n);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;		
	}
	
	public int deleteVoc(Connection conn, int vocNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteVoc");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vocNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Notice> selectCategory(Connection conn, String category) {
		
		ArrayList<Notice> list = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Notice n = null;
		
		String sql = prop.getProperty("selectCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				n = new Notice();
				
				n.setVocNo(rset.getInt("VOC_NO"));
				n.setVocCategory(rset.getString("VOC_CATEGORY"));
				n.setTitle(rset.getString("TITLE"));
				n.setVocMember(rset.getString("VOC_ID"));
				n.setWriter(rset.getString("MEM_ID"));
				n.setContent(rset.getString("CONTENT"));
				n.setDeleteYN(rset.getString("DELETE_YN"));
				
				list.add(n);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
		
	}
	
	
	
	
	
	
	
	

}
