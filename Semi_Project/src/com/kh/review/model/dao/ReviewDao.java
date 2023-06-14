package com.kh.review.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.review.model.vo.Review;

public class ReviewDao {
	
private Properties prop = new Properties();
	
    public ReviewDao() {
		
		String file = ReviewDao.class.getResource("/sql/review/review_mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int insertReview(Connection conn, Review r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getMemNo());
			pstmt.setInt(2, r.getpNo());
			pstmt.setString(3, r.getContent());
			
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
