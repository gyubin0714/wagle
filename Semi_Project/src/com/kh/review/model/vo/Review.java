package com.kh.review.model.vo;

import java.sql.Date;

public class Review {

	private int rNo;//REVIEW_NO	NUMBER
	private int memNo;//MEM_NO	NUMBER
	private int pNo;//PRODUCT_NO	NUMBER
	private String content;//CONTETNT	VARCHAR2(2000 BYTE)
	private Date create_date;//CREATE_DATE	DATE
	private String delete_YN;//DELETE_YN	CHAR(1 BYTE)
	private int fileNo;
	private String productName;
	private String method;
	
	
	
	
	public Review(int memNo, String content, int fileNo, String productName, String method) {
		super();
		this.memNo = memNo;
		this.content = content;
		this.fileNo = fileNo;
		this.productName = productName;
		this.method = method;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Review(int rNo, int memNo, int pNo, String content, Date create_date, String delete_YN, int fileNo,
			String productName, String method) {
		super();
		this.rNo = rNo;
		this.memNo = memNo;
		this.pNo = pNo;
		this.content = content;
		this.create_date = create_date;
		this.delete_YN = delete_YN;
		this.fileNo = fileNo;
		this.productName = productName;
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	public Review() {
		super();
	}

	public Review(int rNo, int memNo, int pNo, String content, Date create_date, String delete_YN) {
		super();
		this.rNo = rNo;
		this.memNo = memNo;
		this.pNo = pNo;
		this.content = content;
		this.create_date = create_date;
		this.delete_YN = delete_YN;
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getDelete_YN() {
		return delete_YN;
	}

	public void setDelete_YN(String delete_YN) {
		this.delete_YN = delete_YN;
	}

	@Override
	public String toString() {
		return "Review [rNo=" + rNo + ", memNo=" + memNo + ", pNo=" + pNo + ", content=" + content + ", create_date="
				+ create_date + ", delete_YN=" + delete_YN + "]";
	}
	
	
	
}
