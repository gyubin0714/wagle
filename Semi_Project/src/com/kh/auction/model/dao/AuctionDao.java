package com.kh.auction.model.dao;

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
import com.kh.auction.model.vo.AuctionRecode;
import com.kh.common.JDBCTemplate;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductPageInfo;

public class AuctionDao {
	
	private Properties prop = new Properties();

	public AuctionDao() {
		String fileName = AuctionDao.class.getResource("/sql/auction/auction_mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
		
	}
	
	public ArrayList<Auction> selectAuctionList(Connection conn, int startNum) {
		
		ArrayList<Auction> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAuctionList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, startNum + 12);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Auction a = new Auction();
				a.setProductNo(rset.getInt("PRODUCT_NO"));
				a.setProductName(rset.getString("PRODUCT_NAME"));
				a.setPriceNow(rset.getInt("PRICE_NOW"));
				a.setTitleimg(rset.getString("TITLEIMG"));
				a.setAuctionNo(rset.getInt("AUCTION_NO"));
				a.setViewCnt(rset.getInt("VIEW_CNT"));
				a.setCreateDt(rset.getDate("CREATE_DT"));
				
				list.add(a);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int insertAuction(Connection conn, Auction a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAuction");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getPriceMin());
			pstmt.setInt(2, a.getPriceMin());
			pstmt.setString(3,a.getStartTime());
			pstmt.setString(4,a.getStartTime());
			pstmt.setInt(5, Integer.parseInt(a.getEndTime()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Auction selectAuction(Connection conn, int productNo) {
		
		Auction a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAuction");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				a = new Auction();
				a.setProductNo(rset.getInt("PRODUCT_NO"));
				a.setMemNo(String.valueOf(rset.getInt("MEM_NO")));
				a.setNickName(rset.getString("NICKNAME"));
				a.setProductName(rset.getString("PRODUCT_NAME"));
				a.setProductStatus(rset.getString("PRODUCT_STATUS"));
				a.setLocation(rset.getString("LOACATION"));
				a.setProductDesc(rset.getString("PRODUCT_DESC"));
				a.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				a.setProductDelv(rset.getString("PRODUCT_DELV"));
				a.setCreateDt(rset.getDate("CREATE_DT"));
				a.setViewCnt(rset.getInt("VIEW_CNT"));
				a.setCateNo(rset.getInt("CATE_NO"));
				a.setAuctionNo(rset.getInt("AUCTION_NO"));
				a.setPriceMin(rset.getInt("PRICE_MIN"));
				a.setPriceNow(rset.getInt("PRICE_NOW"));
				a.setStartTime(rset.getString("START_TIME"));
				a.setEndTime(rset.getString("END_TIME"));
				a.setCateName(rset.getString("CATE_NAME"));
				a.setProductTradeStatus(rset.getString("PRODUCT_TRADE_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return a;
	}
	
	public int selectCountMember(Connection conn, int auctionNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCountMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, auctionNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Auction selectAuctionModal(Connection conn, int auctionNo) {
		
		Auction a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAuctionModal");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,auctionNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				a = new Auction();
				a.setEndTime(String.valueOf(rset.getInt("END_TIME")));
				a.setPriceNow(rset.getInt("PRICE_NOW"));
				a.setProductNo(rset.getInt("PRODUCT_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return a;
	}
	
	public ArrayList<AuctionRecode> selectRecodeList(Connection conn, int auctionNo){
		
		ArrayList<AuctionRecode> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRecodeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, auctionNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AuctionRecode ar = new AuctionRecode();
                ar.setRecodeNo(rset.getInt("RECODE_NO"));
                ar.setAuctionNo(rset.getInt("AUCTION_NO"));
                ar.setMemNo(rset.getInt("MEM_NO"));
                ar.setNickname(rset.getString("NICKNAME"));
                ar.setRank(rset.getInt("RANK"));
                ar.setRowNum(rset.getInt("ROWNUM"));
                
                list.add(ar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int updatePriceNow(Connection conn, Auction a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePriceNow");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getPriceNow());
			pstmt.setInt(2, a.getAuctionNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertAuctionRecode(Connection conn, Auction a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAuctionRecode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getAuctionNo());
			pstmt.setInt(2, Integer.parseInt(a.getMemNo()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int selectSuccessMember(Connection conn, int auctionNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSuccessMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, auctionNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("MEM_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int checkTransAction(Connection conn, int productNo, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("checkTransAction");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, productNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateTradeStatus(Connection conn, int productNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateTradeStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertTransAction(Connection conn, int productNo, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertTransAction");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			pstmt.setInt(2, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Auction> searchTitle(Connection conn, String searchText, int startnum) {

		ArrayList<Auction> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchTitle");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setInt(2, startnum);
			pstmt.setInt(3, startnum+12);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Auction a = new Auction();
				a.setProductNo(rset.getInt("PRODUCT_NO"));
				a.setProductName(rset.getString("PRODUCT_NAME"));
				a.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				a.setTitleimg(rset.getString("TITLEIMG"));
               	a.setCreateDt(rset.getDate("CREATE_DT"));
               	a.setPriceNow(rset.getInt("PRICE_NOW"));
               	a.setEndTime(rset.getString("END_TIME"));

				searchList.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}


	public ArrayList<Auction> searchMemId(Connection conn, String searchText, int startnum) {

		ArrayList<Auction> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchMemId");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setInt(2, startnum);
			pstmt.setInt(3, startnum+12);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Auction a = new Auction();
				a.setProductNo(rset.getInt("PRODUCT_NO"));
				a.setProductName(rset.getString("PRODUCT_NAME"));
				a.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				a.setTitleimg(rset.getString("TITLEIMG"));
               	a.setCreateDt(rset.getDate("CREATE_DT"));
               	a.setPriceNow(rset.getInt("PRICE_NOW"));
               	a.setEndTime(rset.getString("END_TIME"));

				searchList.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}

	public ArrayList<Auction> searchAll(Connection conn, String searchText, int startnum) {

		ArrayList<Auction> searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchAll");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setInt(3, startnum);
			pstmt.setInt(4, startnum+12);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Auction a = new Auction();
				a.setProductNo(rset.getInt("PRODUCT_NO"));
				a.setProductName(rset.getString("PRODUCT_NAME"));
				a.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				a.setTitleimg(rset.getString("TITLEIMG"));
               	a.setCreateDt(rset.getDate("CREATE_DT"));
               	a.setPriceNow(rset.getInt("PRICE_NOW"));
               	a.setEndTime(rset.getString("END_TIME"));

				searchList.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return searchList;

	}
}
