package com.kh.divide.model.vo;

public class Divide_Comment {

	private int cNo;//COMMENT_NO	NUMBER
	private String dContent;//D_CONTENT	VARCHAR2(4000 BYTE)
	private int dNo;//D_NO	NUMBER
	private String mNo;//MEM_NO	NUMBER
	private String createDate;//CREATE_DATE	DATE
	private String status; //STATUS	VARCHAR2(1 BYTE)
	
	public Divide_Comment() {
		super();
	}
	
	

	public Divide_Comment(int cNo, String mNo, String dContent, String createDate) {
		super();
		this.cNo = cNo;
		this.mNo = mNo;
		this.dContent = dContent;
		this.createDate = createDate;
	}



	public Divide_Comment(int cNo, int dNo, String mNo, String dContent, String createDate, String status) {
		super();
		this.cNo = cNo;
		this.dNo = dNo;
		this.mNo = mNo;
		this.dContent = dContent;
		this.createDate = createDate;
		this.status = status;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public int getdNo() {
		return dNo;
	}

	public void setdNo(int dNo) {
		this.dNo = dNo;
	}

	public String getmNo() {
		return mNo;
	}

	public void setmNo(String mNo) {
		this.mNo = mNo;
	}

	public String getdContent() {
		return dContent;
	}

	public void setdContent(String dContent) {
		this.dContent = dContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Divide_Comment [cNo=" + cNo + ", dNo=" + dNo + ", mNo=" + mNo + ", dContent=" + dContent
				+ ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
	
}
