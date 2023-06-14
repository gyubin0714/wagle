package com.kh.divide.model.vo;

import java.sql.Date;

public class Divide {
	
	private int dNo;//DNO	NUMBER
	private String title;//TITLE	VARCHAR2(100 BYTE)
	private String content;//CONTENT	VARCHAR2(4000 BYTE)
	private String delete_YN;//DELETE_YN	CHAR(1 BYTE)
	private String divide_YN;//DIVIDE_YN	CHAR(1 BYTE)
	private Date reg_Date;//REG_DATE	DATE
	private String trade_YN;//TRADE_YN	CHAR(1 BYTE)
	private String field;//FIELD	VARCHAR2(1000 BYTE)
	private String mem_No;//MEM_NO	NUMBER
	private int cate_No;//CATE_NO	NUMBER
	private int d_Count; //D_COUNT	NUMBER
	private String category;
	private String titleImg;
	
	public Divide() {
		super();
	}

	

	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getTitleImg() {
		return titleImg;
	}



	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}



	public Divide(int dNo, String title, String content, String delete_YN, String divide_YN, Date reg_Date,
			String trade_YN, String field, String mem_No, int cate_No, int d_Count, String category, String titleImg) {
		super();
		this.dNo = dNo;
		this.title = title;
		this.content = content;
		this.delete_YN = delete_YN;
		this.divide_YN = divide_YN;
		this.reg_Date = reg_Date;
		this.trade_YN = trade_YN;
		this.field = field;
		this.mem_No = mem_No;
		this.cate_No = cate_No;
		this.d_Count = d_Count;
		this.category = category;
		this.titleImg = titleImg;
	}



	public int getD_Count() {
		return d_Count;
	}



	public void setD_Count(int d_Count) {
		this.d_Count = d_Count;
	}



	public int getdNo() {
		return dNo;
	}

	public void setdNo(int dNo) {
		this.dNo = dNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDelete_YN() {
		return delete_YN;
	}

	public void setDelete_YN(String delete_YN) {
		this.delete_YN = delete_YN;
	}

	public String getDivide_YN() {
		return divide_YN;
	}

	public void setDivide_YN(String divide_YN) {
		this.divide_YN = divide_YN;
	}

	public Date getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(Date reg_Date) {
		this.reg_Date = reg_Date;
	}

	public String getTrade_YN() {
		return trade_YN;
	}

	public void setTrade_YN(String trade_YN) {
		this.trade_YN = trade_YN;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMem_No() {
		return mem_No;
	}

	public void setMem_No(String mem_No) {
		this.mem_No = mem_No;
	}

	public int getCate_No() {
		return cate_No;
	}

	public void setCate_No(int cate_No) {
		this.cate_No = cate_No;
	}



	@Override
	public String toString() {
		return "Divide [dNo=" + dNo + ", title=" + title + ", content=" + content + ", delete_YN=" + delete_YN
				+ ", divide_YN=" + divide_YN + ", reg_Date=" + reg_Date + ", trade_YN=" + trade_YN + ", field=" + field
				+ ", mem_No=" + mem_No + ", cate_No=" + cate_No + ", d_Count=" + d_Count + "]";
	}

	
	
}
