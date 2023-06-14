package com.kh.auction.model.vo;

public class AuctionRecode {
	
	private int recodeNo; //RECODE_NO	NUMBER			기록번호
	private int rowNum;		//ROWNUM					정렬번호
	private int auctionNo;	//AUCTION_NO	NUMBER		경매번호
	private int memNo; 		//MEM_NO	NUMBER			회원번호
	private String nickname;//NICKNAME 					회원닉네임
	private int rank;		//RANK						중첩횟수
	
	public AuctionRecode() {
		super();
	}
	public AuctionRecode(int recodeNo, int rowNum, int auctionNo, int memNo, String nickname, int rank) {
		super();
		this.recodeNo = recodeNo;
		this.rowNum = rowNum;
		this.auctionNo = auctionNo;
		this.memNo = memNo;
		this.nickname = nickname;
		this.rank = rank;
	}
	public int getRecodeNo() {
		return recodeNo;
	}
	public void setRecodeNo(int recodeNo) {
		this.recodeNo = recodeNo;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getAuctionNo() {
		return auctionNo;
	}
	public void setAuctionNo(int auctionNo) {
		this.auctionNo = auctionNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "AuctionRecode [recodeNo=" + recodeNo + ", rowNum=" + rowNum + ", auctionNo=" + auctionNo + ", memNo="
				+ memNo + ", nickname=" + nickname + ", rank=" + rank + "]";
	}
}
