package com.kh.common.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.auction.model.vo.Auction;
import com.kh.common.model.dao.CommonDao;

public class CommonService {

	public ArrayList<Auction> selectNew() {

		Connection conn = getConnection();
		
		ArrayList<Auction> list= new CommonDao().selectNew(conn);
		
		close(conn);
	
		return list;
	}
	
	public ArrayList<Auction> selectBest() {

		Connection conn = getConnection();
		
		ArrayList<Auction> list= new CommonDao().selectBest(conn);
		
		close(conn);
	
		return list;
	}
	
	public ArrayList<Auction> selectAuction() {

		Connection conn = getConnection();
		
		ArrayList<Auction> list= new CommonDao().selectAuction(conn);
		
		close(conn);
	
		return list;
	}

}
