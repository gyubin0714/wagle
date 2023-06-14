package com.kh.board.model.vo;

import java.sql.Date;

public class Reply {

	private int commentNo;//	COMMENT_NO	NUMBER
	private String content;//	CONTENT	VARCHAR2(4000 BYTE)
	private int boardNo;//	BOARD_NO	NUMBER
	private int memNo;//	MEM_NO	NUMBER
	private int groupNum;//	GROUP_NUM	NUMBER
	private int commentClass;//	COMMENT_CLASS	NUMBER
	private int boardOrder;//	BOARD_ORDER	NUMBER
	private Date commentDate;//	COMMENT_DATE	DATE
	private String nickName;//
	
	public Reply() {
		super();
	}

	public Reply(int commentNo, String content, int boardNo, int memNo, int groupNum, int commentClass, int boardOrder,
			Date commentDate, String nickName) {
		super();
		this.commentNo = commentNo;
		this.content = content;
		this.boardNo = boardNo;
		this.memNo = memNo;
		this.groupNum = groupNum;
		this.commentClass = commentClass;
		this.boardOrder = boardOrder;
		this.commentDate = commentDate;
		this.nickName = nickName;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public int getCommentClass() {
		return commentClass;
	}

	public void setCommentClass(int commentClass) {
		this.commentClass = commentClass;
	}

	public int getBoardOrder() {
		return boardOrder;
	}

	public void setBoardOrder(int boardOrder) {
		this.boardOrder = boardOrder;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "Reply [commentNo=" + commentNo + ", content=" + content + ", boardNo=" + boardNo + ", memNo=" + memNo
				+ ", groupNum=" + groupNum + ", commentClass=" + commentClass + ", boardOrder=" + boardOrder
				+ ", commentDate=" + commentDate + ", nickName=" + nickName + "]";
	}
	
	
}
