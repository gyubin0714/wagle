package com.kh.auction.model.service;


import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.auction.model.dao.AuctionDao;
import com.kh.auction.model.vo.Auction;
import com.kh.auction.model.vo.AuctionRecode;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.Attachment;
import com.kh.product.model.dao.ProductDao;
import com.kh.product.model.vo.Product;

public class AuctionService {
	
	public int selectListCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new AuctionDao().selectListCount(conn);
		
		if(listCount > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return listCount;
	}
	
	public ArrayList<Auction> selectAuctionList(int startNum) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Auction> list = new AuctionDao().selectAuctionList(conn, startNum);

		JDBCTemplate.close(conn);

		return list;
	}
	
	public int insertAuction(Product p, Auction a, ArrayList<Attachment> list) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result1 = new ProductDao().insertProduct(conn, p);
				
		int result2 = new AuctionDao().insertAuction(conn, a);
		
		int result3 = new ProductDao().insertAttachment(conn, list);

		if((result1 * result2 * result3) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return (result1 * result2 * result3);
	}
	
	public Auction selectAuction(int productNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Auction a = new AuctionDao().selectAuction(conn, productNo);
		
		JDBCTemplate.close(conn);
		
		return a;
	}
	
	public int selectCountMember(int auctionNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new AuctionDao().selectCountMember(conn, auctionNo);
		
		JDBCTemplate.close(conn);
		
		return count;
	}
	
	public Auction selectAuctionModal(int auctionNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Auction a = new AuctionDao().selectAuctionModal(conn, auctionNo);
		
		JDBCTemplate.close(conn);
		
		return a;
	}
	
	public ArrayList<AuctionRecode> selectRecodeList(int auctionNo){
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<AuctionRecode> list = new AuctionDao().selectRecodeList(conn, auctionNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updatePriceNow(Auction a) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// auction테이블 update
		int result1 = new AuctionDao().updatePriceNow(conn, a);
		
		// auction_recode 테이블 insert
		int result2 = new AuctionDao().insertAuctionRecode(conn, a);
		
		if((result1 * result2) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return (result1 * result2);
	}
	
	
	public int selectSuccessMember(int auctionNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int memNo = new AuctionDao().selectSuccessMember(conn, auctionNo);
		
		JDBCTemplate.close(conn);
		
		return memNo;
	}
	
	public int checkTransAction(int productNo, int memNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int check = new AuctionDao().checkTransAction(conn, productNo, memNo);
		
		JDBCTemplate.close(conn);
		
		return check;
	}
	
	public int updateTradeStatus(int productNo, int memNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// 판매중 -> 판매 완료
		int result1 = new AuctionDao().updateTradeStatus(conn, productNo);
		
		// 결제 내역 추가
		int result2 = new AuctionDao().insertTransAction(conn, productNo, memNo);
		
		if((result1 * result2) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return (result1 * result2);
	}
	
	public ArrayList<Auction> searchAuction(String searchSelect, String searchText, int startNum) {
		
		Connection conn = getConnection();
		ArrayList<Auction> searchList = new ArrayList();

		if(searchSelect.equals("title")) {
			searchList = new AuctionDao().searchTitle(conn, searchText, startNum);
		} else if(searchSelect.equals("memId")) {
			searchList = new AuctionDao().searchMemId(conn, searchText, startNum);
		} else {
			searchList = new AuctionDao().searchAll(conn, searchText, startNum);
		}

		close(conn);
		
		return searchList;
	}
	
	
	
}
