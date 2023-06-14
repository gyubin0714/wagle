package com.kh.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo; //BOARD_NO	NUMBER
	private String boardTitle; //BOARD_TITLE	VARCHAR2(1000 BYTE)
	private String boardWriting; //BOARD_WRITING	VARCHAR2(4000 BYTE)
	private Date boardDate; //BOARD_DATE	DATE
	private int boardHits; //BOARD_HITS	NUMBER
	private String boardDelete; //BOARD_DELETE_YN	CHAR(1 BYTE)
	private String categoryNo; //CATEGORY_NO	NUMBER
	private String memberNo; //MEM_NO/NICKNAME	NUMBER
	private int memNo; // MEM_NO
	public Board() {
		super();
	}
	public Board(int boardNo, String boardTitle, String boardWriting, Date boardDate, int boardHits, String boardDelete,
			String categoryNo, String memberNo, int memNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriting = boardWriting;
		this.boardDate = boardDate;
		this.boardHits = boardHits;
		this.boardDelete = boardDelete;
		this.categoryNo = categoryNo;
		this.memberNo = memberNo;
		this.memNo = memNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriting() {
		return boardWriting;
	}
	public void setBoardWriting(String boardWriting) {
		this.boardWriting = boardWriting;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardHits() {
		return boardHits;
	}
	public void setBoardHits(int boardHits) {
		this.boardHits = boardHits;
	}
	public String getBoardDelete() {
		return boardDelete;
	}
	public void setBoardDelete(String boardDelete) {
		this.boardDelete = boardDelete;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriting=" + boardWriting
				+ ", boardDate=" + boardDate + ", boardHits=" + boardHits + ", boardDelete=" + boardDelete
				+ ", categoryNo=" + categoryNo + ", memberNo=" + memberNo + ", memNo=" + memNo + "]";
	}
	
	
}