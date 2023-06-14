package com.kh.common.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.auction.model.vo.Auction;
import com.kh.product.model.vo.Product;

public class CommonDao {
	
private Properties prop = new Properties();
	
	public CommonDao() {
		String fileName = CommonDao.class.getResource("/sql/common/common_mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Auction> selectNew(Connection conn) {
		
		ArrayList<Auction> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNew");
		
		try {
			pstmt=conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Auction a = new Auction();
				a.setTitleimg(rset.getString("TITLEIMG"));
				a.setProductNo(rset.getInt("PRODUCT_NO"));
				a.setProductName(rset.getString("PRODUCT_NAME"));
				a.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				a.setMethod(rset.getString("METHOD"));
				a.setCreateDt(rset.getDate("CREATE_DT"));
				a.setViewCnt(rset.getInt("VIEW_CNT"));
				a.setCount(rset.getInt("COUNT"));
				a.setEndTime(rset.getString("END_TIME"));
				
	            list.add(a);
	            
	            // 중고거래 -> p  엔드타임빼고다 출력되게
	            // 경매 -> a CREATE_DT 빼고 다 출력
	            
				
				//PRODUCT_NO,PRODUCT_NAME,PRODUCT_PRICE,
				//METHOD,CREATE_DT,VIEW_CNT,NVL(COUNT,0) COUNT,
				//TO_CHAR(END_TIME, 'MM"월"DD"일" HH"시"MI"분"') END_TIME ,
				//FILE_PATH||'/'||CHANGE_NAME TITLEIMG
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
public ArrayList<Auction> selectBest(Connection conn) {
		
		ArrayList<Auction> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBest");
		
		try {
			pstmt=conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Auction a = new Auction();
				a.setTitleimg(rset.getString("TITLEIMG"));
				a.setProductNo(rset.getInt("PRODUCT_NO"));
				a.setProductName(rset.getString("PRODUCT_NAME"));
				a.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				a.setMethod(rset.getString("METHOD"));
				a.setCreateDt(rset.getDate("CREATE_DT"));
				a.setViewCnt(rset.getInt("VIEW_CNT"));
				a.setCount(rset.getInt("COUNT"));
				a.setEndTime(rset.getString("END_TIME"));
				
	            list.add(a);
	            
	            // 중고거래 -> p  엔드타임빼고다 출력되게
	            // 경매 -> a CREATE_DT 빼고 다 출력
	            
				
				//PRODUCT_NO,PRODUCT_NAME,PRODUCT_PRICE,
				//METHOD,CREATE_DT,VIEW_CNT,NVL(COUNT,0) COUNT,
				//TO_CHAR(END_TIME, 'MM"월"DD"일" HH"시"MI"분"') END_TIME ,
				//FILE_PATH||'/'||CHANGE_NAME TITLEIMG
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

public ArrayList<Auction> selectAuction(Connection conn) {
	
	ArrayList<Auction> list = new ArrayList();
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	String sql = prop.getProperty("selectAuction");
	
	try {
		pstmt=conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			Auction a = new Auction();
			a.setTitleimg(rset.getString("TITLEIMG"));
			a.setProductNo(rset.getInt("PRODUCT_NO"));
			a.setProductName(rset.getString("PRODUCT_NAME"));
			a.setProductPrice(rset.getInt("PRODUCT_PRICE"));
			a.setMethod(rset.getString("METHOD"));
			a.setCreateDt(rset.getDate("CREATE_DT"));
			a.setViewCnt(rset.getInt("VIEW_CNT"));
			a.setCount(rset.getInt("COUNT"));
			a.setEndTime(rset.getString("END_TIME"));
			
            list.add(a);
            
            // 중고거래 -> p  엔드타임빼고다 출력되게
            // 경매 -> a CREATE_DT 빼고 다 출력
            
			
			//PRODUCT_NO,PRODUCT_NAME,PRODUCT_PRICE,
			//METHOD,CREATE_DT,VIEW_CNT,NVL(COUNT,0) COUNT,
			//TO_CHAR(END_TIME, 'MM"월"DD"일" HH"시"MI"분"') END_TIME ,
			//FILE_PATH||'/'||CHANGE_NAME TITLEIMG
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rset);
		close(pstmt);
	}
	return list;
}

}
