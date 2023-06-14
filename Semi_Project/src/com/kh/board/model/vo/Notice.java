package com.kh.board.model.vo;

import java.sql.Date;

public class Notice {
	
	private int noticeNo;//NOTICE_NO	NUMBER
	private String noticeTitle;//TITLE	VARCHAR2(100 BYTE)
	private String noticeContent;//CONTENT	VARCHAR2(2000 BYTE)
	private Date noticeWriteDate;//WRITE_DATE	DATE
	private Date noticeCreateDate;//CREATE_DATE	DATE
	private String noticeDeleteYN;//DELETE_YN	CHAR(3 BYTE)
	private int noticeCategoryNo;//CS_CATEGORY_NO	NUMBER
	private int noticeCount;//COUNT	NUMBER
	
	public Notice() {
		super();
	}
	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeWriteDate, Date noticeCreateDate,
			String noticeDeleteYN, int noticeCategoryNo, int noticeCount) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriteDate = noticeWriteDate;
		this.noticeCreateDate = noticeCreateDate;
		this.noticeDeleteYN = noticeDeleteYN;
		this.noticeCategoryNo = noticeCategoryNo;
		this.noticeCount = noticeCount;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getNoticeWriteDate() {
		return noticeWriteDate;
	}
	public void setNoticeWriteDate(Date noticeWriteDate) {
		this.noticeWriteDate = noticeWriteDate;
	}
	public Date getNoticeCreateDate() {
		return noticeCreateDate;
	}
	public void setNoticeCreateDate(Date noticeCreateDate) {
		this.noticeCreateDate = noticeCreateDate;
	}
	public String getNoticeDeleteYN() {
		return noticeDeleteYN;
	}
	public void setNoticeDeleteYN(String noticeDeleteYN) {
		this.noticeDeleteYN = noticeDeleteYN;
	}
	public int getNoticeCategoryNo() {
		return noticeCategoryNo;
	}
	public void setNoticeCategoryNo(int noticeCategoryNo) {
		this.noticeCategoryNo = noticeCategoryNo;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeWriteDate=" + noticeWriteDate + ", noticeCreateDate=" + noticeCreateDate
				+ ", noticeDeleteYN=" + noticeDeleteYN + ", noticeCategoryNo=" + noticeCategoryNo + ", noticeCount="
				+ noticeCount + "]";
	}
}

