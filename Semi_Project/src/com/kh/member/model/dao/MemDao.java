package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Attachment;
import com.kh.member.model.vo.Follow;
import com.kh.member.model.vo.Member;
import com.kh.notice.model.vo.Notice;
import com.kh.product.model.vo.Product;
import com.kh.review.model.vo.Review;

import static com.kh.common.JDBCTemplate.*;

public class MemDao {
	



	private Properties prop = new Properties();
	
    public MemDao() {
		
		String file = MemDao.class.getResource("/sql/member/mem_mapper.xml").getPath();
		//System.out.println(file);
		
		//System.out.println(file);
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Member loginMember(Connection conn, String memId, String memPwd ) {
		//select문 => resultSet객체
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("MEM_NO"),
						       rset.getString("MEM_ID"),
						       rset.getString("MEM_PWD"),
						       rset.getString("PWD_Q"),
						       rset.getString("PWD_A"),
						       rset.getString("MEM_NAME"),
						       rset.getString("NICKNAME"),
						       rset.getString("EMAIL"),
						       rset.getString("ADDRESS"),
						       rset.getString("PAY_ACCOUNT"),
						       rset.getDate("ENROLL_DATE"),
						       rset.getString("MEM_STATUS"));
						       
						       
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
		
	}
	public int insertMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemId());
			pstmt.setString(2, m.getMemPwd());
			pstmt.setString(3, m.getPwdA());
			pstmt.setString(4, m.getMemName());
			pstmt.setString(5, m.getNickname());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getPayAccount());
			pstmt.setString(9, m.getPwdQ());
			
			result = pstmt.executeUpdate();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}
	public int idCheck(Connection conn, String checkId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int nicknameCheck(Connection conn, String checkNickname) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("nicknameCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkNickname);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	public Member idSearch(Connection conn, String memName , String email) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("idSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("MEM_NO"),
					       rset.getString("MEM_ID"),
					       rset.getString("MEM_PWD"),
					       rset.getString("PWD_Q"),
					       rset.getString("PWD_A"),
					       rset.getString("MEM_NAME"),
					       rset.getString("NICKNAME"),
					       rset.getString("EMAIL"),
					       rset.getString("ADDRESS"),
					       rset.getString("PAY_ACCOUNT"),
					       rset.getDate("ENROLL_DATE"),
					       rset.getString("MEM_STATUS"));
					       
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
		
		
	}
	public Member pwdSearch(Connection conn, String memId, String pwdQ, String pwdA ) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("pwdSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, pwdQ);
			pstmt.setString(3, pwdA);
		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("MEM_NO"),
					       rset.getString("MEM_ID"),
					       rset.getString("MEM_PWD"),
					       rset.getString("PWD_Q"),
					       rset.getString("PWD_A"),
					       rset.getString("MEM_NAME"),
					       rset.getString("NICKNAME"),
					       rset.getString("EMAIL"),
					       rset.getString("ADDRESS"),
					       rset.getString("PAY_ACCOUNT"),
					       rset.getDate("ENROLL_DATE"),
					       rset.getString("MEM_STATUS"));
					       
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
		
	}

	public ArrayList<Follow> followList(Connection conn, int memNo){
		ArrayList<Follow> list = new ArrayList<Follow>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("followList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Follow f = new Follow();
				f.setFileNo(rset.getInt("FILE_NO"));
				f.setNickname(rset.getString("NICKNAME"));
				f.setFollowing(rset.getInt("FOLLOWING"));
				
				//f.setNickname(rset.getString("NICKNAME"));
				
				list.add(f);
				//list.add(new Follow(rset.getString("NICKNAME"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
		
	}
	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemId());
			pstmt.setString(2, m.getMemPwd());
			pstmt.setString(3, m.getPwdQ());
			pstmt.setString(4, m.getPwdA());
			pstmt.setString(5, m.getMemName());
			pstmt.setString(6, m.getNickname());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getEmail());
			pstmt.setString(9, m.getPayAccount());
			pstmt.setInt(10, m.getMemNo());
			
			
			result = pstmt.executeUpdate();
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
	public Member selectMember(Connection conn, int memNo) {
		Member m  = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("MEM_NO"),
						       rset.getString("MEM_ID"),
						       rset.getString("MEM_PWD"),
						       rset.getString("PWD_Q"),
						       rset.getString("PWD_A"),
						       rset.getString("MEM_NAME"),
						       rset.getString("NICKNAME"),
						       rset.getString("EMAIL"),
						       rset.getString("ADDRESS"),
						       rset.getString("PAY_ACCOUNT"),
						       rset.getDate("ENROLL_DATE"),
						       rset.getString("MEM_STATUS"),
						       rset.getInt("REF_BNO"));
				
						
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
		
		
	}
	public int insertAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt =  null;
		
		String sql = prop.getProperty("insertAttachment");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
		
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateAttachment(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertNewAttachment(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, at.getFileNo());
			pstmt.setInt(1, at.getRefBno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

public Attachment selectAttachment(Connection conn , int memNo) {
	
	Attachment at = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	String sql = prop.getProperty("selectAttachment");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memNo);
		
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
	}finally {
		close(rset);
		close(pstmt);
	}
	return at;
	
}

public Attachment selectProductAttachment(Connection conn , int productNo) {
	
	Attachment at = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	String sql = prop.getProperty("selectProductAttachment");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, productNo);
		
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
	}finally {
		close(rset);
		close(pstmt);
	}
	return at;
	
}
public ArrayList<Board> selectMyBoard(Connection conn, int memNo){
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	ArrayList<Board> list = new ArrayList<Board>();
	
	String sql = prop.getProperty("selectMyBoard");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memNo);
		
		rset = pstmt.executeQuery();
		while(rset.next()) {
			Board b = new Board();
			b.setBoardTitle(rset.getString("BOARD_TITLE"));
			b.setMemNo(rset.getInt("MEM_NO"));
			b.setBoardDate(rset.getDate("BOARD_DATE"));
			b.setBoardHits(rset.getInt("BOARD_HITS"));
			
			list.add(b);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return list;
	
}
   public void insertReview(Connection conn, int memNo, int productNo) {
	     PreparedStatement pstmt = null;
	     
	     String sql = prop.getProperty("insertReview");
	     
	     try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
	     
	     
   }
   public ArrayList<Product> selectMyInterest(Connection conn, int memNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String sql = prop.getProperty("selectMyInterest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setMethod(rset.getString("METHOD"));
				p.setCreateDt(rset.getDate("CREATE_DT"));
				p.setFileNo(rset.getString("FILE_NO"));
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
		
	}
   public int memberCheck(Connection conn, String nickname) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("memberCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickname);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rset.getString("NICKNAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
   
   public int deleteInterest(Connection conn, int memNo, int productNo ) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteInterest");
		
		try {
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, productNo);
			
		result = pstmt.executeUpdate();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
   
   public ArrayList<Product> selectMyEnrollProduct(Connection conn, int memNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String sql = prop.getProperty("selectMyEnrollProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setMethod(rset.getString("METHOD"));
				p.setCreateDt(rset.getDate("CREATE_DT"));
				p.setFileNo(rset.getString("FILE_NO"));
				p.setProductTradeStatus(rset.getString("PRODUCT_TRADE_STATUS"));
				
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
		
	}
   public ArrayList<Product> selectMyDeal(Connection conn, int memNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String sql = prop.getProperty("selectMyDeal");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setMethod(rset.getString("METHOD")); //거래 방법
				p.setCreateDt(rset.getDate("CREATE_DT")); // 거래 일자(바꾸면 될듯)
				p.setFileNo(rset.getString("FILE_NO")); // 이미지
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
		
	}
   public ArrayList<Product> writableReview(Connection conn, int memNo){
	   PreparedStatement pstmt =  null;
	   ResultSet rset = null;
	   ArrayList<Product> list = new ArrayList<Product>();
	   
	   String sql = prop.getProperty("writableReview");
	   
	   try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memNo);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			Product p = new Product();
			p.setProductNo(rset.getInt("PRODUCT_NO"));
			p.setProductName(rset.getString("PRODUCT_NAME"));
			p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
			p.setMethod(rset.getString("METHOD"));
			p.setCreateDt(rset.getDate("CREATE_DT"));
			p.setFileNo(rset.getString("FILE_NO"));
			
			list.add(p);
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	   return list;
	   
   }
   public Member memberSearch(Connection conn, String nickname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;

		
		String sql = prop.getProperty("memberSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickname);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				 m = new Member(rset.getInt("MEM_NO"),
					       rset.getString("MEM_ID"),
					       rset.getString("MEM_PWD"),
					       rset.getString("PWD_Q"),
					       rset.getString("PWD_A"),
					       rset.getString("MEM_NAME"),
					       rset.getString("NICKNAME"),
					       rset.getString("EMAIL"),
					       rset.getString("ADDRESS"),
					       rset.getString("PAY_ACCOUNT"),
					       rset.getDate("ENROLL_DATE"),
					       rset.getString("MEM_STATUS"));
						     
				
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
		
		
	}
   public Product selectProduct(Connection conn, int productNo) {
	   
	   Product p = null;
	   PreparedStatement pstmt =null;
	   ResultSet rset = null;
	   
	   String sql = prop.getProperty("selectProduct");
	   
	   try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, productNo);
		//System.out.println(productNo);
		rset = pstmt.executeQuery();
		
		if(rset.next()) {
			p = new Product();
			p.setFileNo(rset.getString("FILE_NO"));
			p.setProductName(rset.getString("PRODUCT_NAME"));
			p.setMethod(rset.getString("METHOD"));
			p.setMemNo(rset.getString("MEM_NO"));
			p.setProductNo(rset.getInt("PRODUCT_NO"));

			
		}
				
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	 
	}
	   return p;
	   
	  
   }
   public int insertReview(Connection conn , Review r) {
		int result = 0;
		PreparedStatement pstmt =  null;
		String sql = prop.getProperty("insertReview");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, r.getMemNo());
			pstmt.setInt(2, r.getpNo());
			pstmt.setString(3, r.getContent());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
   public ArrayList<Review> selectReviewList(Connection conn,int memNo){
	   ArrayList<Review> reviewList = new ArrayList<Review>();
	   PreparedStatement pstmt =null;
	   ResultSet rset = null;
	   
	   String sql = prop.getProperty("selectReviewList");
	   
	   try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memNo);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			Review r = new Review();
			r.setFileNo(rset.getInt("FILE_NO"));
			r.setProductName(rset.getString("PRODUCT_NAME"));
			r.setMethod(rset.getString("METHOD"));
		    r.setContent(rset.getString("CONTETNT"));
		    
		    reviewList.add(r);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rset);
		close(pstmt);
	}
	   return reviewList;
	   
	   
	   
   }
   public int deleteFollowing(Connection conn, int memNo, int following) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteFollowing");
		
		try {
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, following);
			
		result = pstmt.executeUpdate();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	
}
